package daniils.tryjson;

import com.fasterxml.jackson.databind.ObjectMapper;
import daniils.ArrayPrintHelper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TryJson {
    public static void main(String[] args) throws IOException {
        var sourceUrl = "http://www.mocky.io/v2/5c51b9dd3400003252129fb5";

        var filePath = "C:/Danik/job4j/code_helpers/src/test/java/daniils/try_json/test.json";

        File file = new File(filePath);


        var objectMapper = new ObjectMapper();
        Model[] model = objectMapper.readValue(file, Model[].class);
        System.out.println("Employee Object\n" + model);
        ArrayPrintHelper.print(model);


        String[] arr = new String[]{
                "1", "2", "3", "4", "5", "6", "7",
                "8", "9", "10", "11", "12", "13", "14",
                "15", "16", "17", "18", "19", "20", "21",
                "22", "23", "24", "25", "26", "27", "28",
                "29", "30", "31"
        };


//        System.out.println("arr len: " + arr.length);

        var arrTotalIndexes = arr.length;

        // single thread indexes diapason
        int stid = arr.length / 4;
        int other = arr.length % 4;

//        System.out.println("stid: " + stid);
//        System.out.println("other: " + other);
        List<String> temp;
        List<String> rsl = new ArrayList<>();

        temp = new ThreadSimple().start(0, stid * 1, arr, "Thread 1");
        rsl.addAll(temp);
        temp = new ThreadSimple().start(stid * 1, stid * 2, arr, "Thread 2");
        rsl.addAll(temp);
        temp = new ThreadSimple().start(stid * 2, stid * 3, arr, "Thread 3");
        rsl.addAll(temp);
        temp = new ThreadSimple().start(stid * 3, stid * 4 + other, arr, "Thread 4");
        rsl.addAll(temp);

        rsl.forEach(System.out::println);
        //  0,  1,  2,  3,  4,  5, 6, 7
        //  8,  9, 10, 11, 12, 13, 14
        // 15, 16, 17, 18, 19, 20, 21
        // 22, 23, 24, 25, 26, 27, 28
        // 29, 30

        // T1 = 0 - 250
        // T2 = 250 - 500
        // T3 = 500 - 750
        // T4 = 750 - 1000
    }

    static class ThreadSimple {
        public List<String> start(int startIndex, int finalIndex, String[] arr, String name) {
            var rsl = new ArrayList<String>();
            System.out.println(name + " START");
            for (int i = startIndex; i < finalIndex; i++) {
//                System.out.println(arr[i]);
                rsl.add(arr[i]);
            }
            System.out.println(name + " FINISH");
            return rsl;
        }
    }
}
