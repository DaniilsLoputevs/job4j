package daniils;

public class Algorithms {
    /**
     * Sum all elements in {@param arr}.
     *
     * @param arr        -
     * @param startIndex -
     * @return - sum of all elements.
     */
    public static int sum(int[] arr, int startIndex) {
        var temp = arr[startIndex++];
        if (startIndex < arr.length) {
            temp += sum(arr, startIndex);
        }
        return temp;
    }

    /**
     * The main "Quick sort" Algorithm. Sort {@param arr}.
     *
     * @param arr  Array to be sorted.
     * @param low  Starting index.
     * @param high Ending index.
     */
    public static void qSort(int[] arr, int low, int high) {
        if (low < high) {
            /* pi is partitioning index, arr[pi] is
              now at right place */
            int pi = qSortInside(arr, low, high);

            // Recursively sort elements before
            // partition and after partition
            qSort(arr, low, pi - 1);
            qSort(arr, pi + 1, high);
        }
    }

    /**
     * This function takes last element as pivot,
     * places the pivot element at its correct
     * position in sorted array, and places all
     * smaller (smaller than pivot) to left of
     * pivot and all greater elements to right
     * of pivot
     */
    private static int qSortInside(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = (low - 1); // index of smaller element
        for (int j = low; j < high; j++) {
            // If current element is smaller than the pivot
            if (arr[j] < pivot) {
                i++;
                // swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        // swap arr[i+1] and arr[high] (or pivot)
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    /**
     * Compare by elements and their indexes.
     *
     * @param arr1 - first arr.
     * @param arr2 - second arr.
     * @return - true -> if all cell is arrays is identical.
     */
    public static boolean compare(int[] arr1, int[] arr2) {
        boolean result = true;
        if (arr1.length == arr2.length) {
            for (int i = 0; i < arr1.length; i++) {
                if (arr1[i] != arr2[i]) {
                    result = false;
                    break;
                }
            }
        } else {
            result = false;
        }
        return result;
    }

    public static <T> boolean compare(T[] arr1, T[] arr2) {
        boolean result = true;
        if (arr1.length == arr2.length) {
            for (int i = 0; i < arr1.length; i++) {
                if (!arr1[i].equals(arr2[i])) {
                    result = false;
                    break;
                }
            }
        } else {
            result = false;
        }
        return result;
    }
}