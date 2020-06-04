package daniils;

public class ArrayHelper {
    public static <T> void printArray(T[] arr) {
        for (T var : arr) {
            System.out.println(var + " ");
        }
        System.out.println();
    }
    public static void printArray(int[] arr) {
        for (int var : arr) {
            System.out.println(var + " ");
        }
        System.out.println();
    }
    public static void printArray(char[] arr) {
        for (char var : arr) {
            System.out.println(var + " ");
        }
        System.out.println();
    }
    public static void printArray(long[] arr) {
        for (long var : arr) {
            System.out.println(var + " ");
        }
        System.out.println();
    }
    public static void printArray(double[] arr) {
        for (double var : arr) {
            System.out.println(var + " ");
        }
        System.out.println();
    }
    public static void printArray(float[] arr) {
        for (float var : arr) {
            System.out.println(var + " ");
        }
        System.out.println();
    }

    public static <T> void printArray(T[][] doubleArr) {
        for (T[] arr : doubleArr) {
            for (T var : arr) {
                System.out.println(var + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    public static void printArray(int[][] doubleArr) {
        for (int[] arr : doubleArr) {
            for (int var : arr) {
                System.out.print(var + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    public static void printArray(char[][] doubleArr) {
        for (char[] arr : doubleArr) {
            for (char var : arr) {
                System.out.print(var + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    public static void printArray(long[][] doubleArr) {
        for (long[] arr : doubleArr) {
            for (long var : arr) {
                System.out.print(var + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    public static void printArray(double[][] doubleArr) {
        for (double[] arr : doubleArr) {
            for (double var : arr) {
                System.out.print(var + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    public static void printArray(float[][] doubleArr) {
        for (float[] arr : doubleArr) {
            for (float var : arr) {
                System.out.print(var + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}