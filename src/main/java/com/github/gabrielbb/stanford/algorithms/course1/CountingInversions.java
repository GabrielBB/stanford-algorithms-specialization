package com.github.gabrielbb.stanford.algorithms.course1;

public class CountingInversions {

    // Complete the countInversions function below.
    public static long countInversions(int[] arr) {
        return mergeSort(arr, 0, arr.length - 1);
    }

    private static long mergeSort(int[] array, int left, int right) {

        if (left >= right)
            return 0;

        long swaps = 0;

        // Keep dividing array in halves 0(log n)
        int middle = (left + right) / 2;
        swaps += mergeSort(array, left, middle);
        swaps += mergeSort(array, middle + 1, right);

        // Time to sort both halves 0(n)
        int[] temp = new int[(right - left) + 1];
        int l = left;
        int r = middle + 1;
        int k = 0;

        while (k < temp.length) {
            if (r > right || (l <= middle && array[l] <= array[r])) {
                temp[k++] = array[l++];
            } else {
                temp[k++] = array[r++];
                swaps += (middle + 1) - l;
            }
        }

        // Copying sorted temp to original array
        while (k > 0) {
            array[right--] = temp[--k];
        }

        return swaps;
    }
}
