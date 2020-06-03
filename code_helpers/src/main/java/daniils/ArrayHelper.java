package daniils;

public class ArrayHelper {
//    public static <T> void printArray (T[][] arr) {
//        for (T[] ts : arr) {
//            for (T t : ts) {
//                System.out.println(t);
//            }
//        }
//    }
    public static <T> void printArray (char[][] arr) {
        for (char[] ts : arr) {
            for (char t : ts) {
                System.out.print(t);
            }
            System.out.println();
        }
        System.out.println();
    }
}
