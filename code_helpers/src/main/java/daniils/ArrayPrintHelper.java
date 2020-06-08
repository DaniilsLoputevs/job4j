package daniils;

public class ArrayPrintHelper {
    public static <T> void print(T[] arr) {
        for (T var : arr) {
            System.out.println(var + " ");
        }
        System.out.println();
    }
    public static void print(int[] arr) {
        for (int var : arr) {
            System.out.println(var + " ");
        }
        System.out.println();
    }
    public static void print(char[] arr) {
        for (char var : arr) {
            System.out.println(var + " ");
        }
        System.out.println();
    }
    public static void print(long[] arr) {
        for (long var : arr) {
            System.out.println(var + " ");
        }
        System.out.println();
    }
    public static void print(double[] arr) {
        for (double var : arr) {
            System.out.println(var + " ");
        }
        System.out.println();
    }
    public static void print(float[] arr) {
        for (float var : arr) {
            System.out.println(var + " ");
        }
        System.out.println();
    }

    public static <T> void print(T[][] doubleArr) {
        for (T[] arr : doubleArr) {
            for (T var : arr) {
                System.out.println(var + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    public static void print(int[][] doubleArr) {
        for (int[] arr : doubleArr) {
            for (int var : arr) {
                System.out.print(var + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    public static void print(char[][] doubleArr) {
        for (char[] arr : doubleArr) {
            for (char var : arr) {
                System.out.print(var + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    public static void print(long[][] doubleArr) {
        for (long[] arr : doubleArr) {
            for (long var : arr) {
                System.out.print(var + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    public static void print(double[][] doubleArr) {
        for (double[] arr : doubleArr) {
            for (double var : arr) {
                System.out.print(var + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    public static void print(float[][] doubleArr) {
        for (float[] arr : doubleArr) {
            for (float var : arr) {
                System.out.print(var + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}