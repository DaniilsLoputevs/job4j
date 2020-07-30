package ru.job4j.exam;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import ru.job4j.exam.task.JsonAggregator;
import ru.job4j.exam.task.models.CameraInfo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class JsonAggregatorTest {

    @Test
    public void runTest() {
        try {
            var temp = new JsonAggregator().aggregate("http://www.mocky.io/v2/5c51b9dd3400003252129fb5");

            List<String> rsl = new ArrayList<>();
            var objectMapper = new ObjectMapper();
            for (CameraInfo cameraInfo : temp) {
                rsl.add(objectMapper.writeValueAsString(cameraInfo));
            }
            var exp1 = "{\"id\":1,\"urlType\":\"LIVE\",\"videoUrl\":"
                    + "\"rtsp://127.0.0.1/1\",\"value\":\"fa4b588e-249b-11e9-ab14-d663bd873d93\","
                    + "\"ttl\":120}";
            var exp2 = "{\"id\":1,\"urlType\":\"LIVE\",\"videoUrl\":"
                    + "\"rtsp://127.0.0.1/1\",\"value\":\"fa4b588e-249b-11e9-ab14-d663bd873d93\","
                    + "\"ttl\":120}";
            var exp3 = "{\"id\":1,\"urlType\":\"LIVE\",\"videoUrl\":"
                    + "\"rtsp://127.0.0.1/1\",\"value\":\"fa4b588e-249b-11e9-ab14-d663bd873d93\","
                    + "\"ttl\":120}";
            var exp4 = "{\"id\":1,\"urlType\":\"LIVE\",\"videoUrl\":"
                    + "\"rtsp://127.0.0.1/1\",\"value\":\"fa4b588e-249b-11e9-ab14-d663bd873d93\","
                    + "\"ttl\":120}";

            rsl.forEach(System.out::println);

            assertTrue(rsl.contains(exp1));
            assertTrue(rsl.contains(exp2));
            assertTrue(rsl.contains(exp3));
            assertTrue(rsl.contains(exp4));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}