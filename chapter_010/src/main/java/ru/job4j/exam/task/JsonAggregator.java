package ru.job4j.exam.task;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.job4j.exam.task.models.CameraInfo;
import ru.job4j.exam.task.models.CameraShortInfo;
import ru.job4j.exam.task.models.SourceData;
import ru.job4j.exam.task.models.TokenDataUrl;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class JsonAggregator {
    public List<CameraInfo> aggregate(String url) throws IOException {
        final int threadCount = 4;

        var sourceUrl = new URL(url);
        var objectMapper = new ObjectMapper();
        CameraShortInfo[] objArr = objectMapper.readValue(sourceUrl, CameraShortInfo[].class);

         /*  optimization - one thread will be faster that two+ if objects is not so many.
             for test it is > 1       */
        return (objArr.length > 1)
                ? doManyThreadsParse(objArr, threadCount)
                : doSingleThreadsParse(objArr);
    }

    private List<CameraInfo> doSingleThreadsParse(CameraShortInfo[] objArr) {
        List<CameraInfo> rsl = null;
        try {
            var temp = new ProcessingThread(0, objArr.length, objArr, "Single Thread Processing");
            rsl = temp.call();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rsl;
    }

    private List<CameraInfo> doManyThreadsParse(CameraShortInfo[] objArr, int threadCount) {
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);

        /* stid - single thread indexes diapason - how many indexes, one thread will processing. */
        int stid = objArr.length / threadCount;
        int othersIndexes = objArr.length % threadCount;

        List<Future<List<CameraInfo>>> futures = new ArrayList<>();

        for (int i = 1; i <= threadCount; i++) {
            int startIndex;
            int finalIndex;
            String name = "Thread " + i;
            if (i == 1) {
                startIndex = 0;
                finalIndex = stid;
            } else {
                startIndex = stid * i - 1;
                finalIndex = stid * i;
            }
            if (i == threadCount) {
                finalIndex = stid * 4 + othersIndexes;
            }
            futures.add(executor.submit(new ProcessingThread(startIndex, finalIndex, objArr, name)));
        }
        return collectMTPR(futures);
    }

    /**
     * collect MTPR - Multi Thread Parsing Results.
     *
     * @param futures -
     * @return -
     */
    private List<CameraInfo> collectMTPR(List<Future<List<CameraInfo>>> futures) {
        var rsl = new ArrayList<CameraInfo>();
        for (var future : futures) {
            try {
                var temp = future.get();
                if (temp != null) {
                    rsl.addAll(temp);
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }
        return rsl;
    }

    private static class ProcessingThread implements Callable<List<CameraInfo>> {
        private final int startIndex;
        private final int finalIndex;
        private final CameraShortInfo[] arr;

        public ProcessingThread(int startIndex, int finalIndex, CameraShortInfo[] arr, String name) {
            this.startIndex = startIndex;
            this.finalIndex = finalIndex;
            this.arr = arr;
        }

        @Override
        public List<CameraInfo> call() throws Exception {
            var rsl = new ArrayList<CameraInfo>();
            var mapper = new ObjectMapper();

            for (int i = startIndex; i < finalIndex; i++) {
                var sourceData = mapper.readValue(new URL(arr[i].getSourceDataUrl()), SourceData.class);
                var tokenData = mapper.readValue(new URL(arr[i].getTokenDataUrl()), TokenDataUrl.class);

                int id = arr[i].getId();
                String urlType = sourceData.getUrlType();
                String videoUrl = sourceData.getVideoUrl();
                String value = tokenData.getValue();
                int ttl = tokenData.getTtl();

                rsl.add(new CameraInfo(id, urlType, videoUrl, value, ttl));
            }
            return rsl;
        }
    }

}