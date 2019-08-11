package com.github.gabrielbb.stanford.algorithms.course1;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class QuickSort {

    private int comparisons = 0;
    private final Pivot pivot;

    public static enum Pivot {
        LEFT, MEDIAN, RIGHT
    }

    public QuickSort() {
        this.pivot = Pivot.LEFT;
    }

    public QuickSort(Pivot pivot) {
        this.pivot = pivot;
    }

    private int getMedianPivot(int[] arr, int low, int high) {
        int length = (high - low) + 1;

        if (length <= 2)
            return low;

        int middle = low + (length % 2 == 0 ? (length / 2) - 1 : length / 2);

        int[] medianArr = new int[] { arr[low], arr[middle], arr[high] };
        Arrays.sort(medianArr);

        return medianArr[1] == arr[low] ? low : (medianArr[1] == arr[high] ? high : middle);
    }

    /*
     * This function takes last element as pivot, places the pivot element at its
     * correct position in sorted array, and places all smaller (smaller than pivot)
     * to left of pivot and all greater elements to right of pivot
     */
    private int partition(int arr[], int low, int high) {

        comparisons += (high - low);

        if (pivot != Pivot.LEFT) {
            int pivotIndex = pivot == Pivot.MEDIAN ? getMedianPivot(arr, low, high) : high;

            int temp = arr[pivotIndex];
            arr[pivotIndex] = arr[low];
            arr[low] = temp;
        }

        int pivot = arr[low];
        int i = (low + 1); // index of smaller element
        for (int j = low + 1; j <= high; j++) {

            if (arr[j] < pivot) {
                // swap arr[i] and arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;

                i++;
            }
        }

        // swap arr[i+1] and arr[high] (or pivot)
        int temp = arr[i - 1];
        arr[i - 1] = arr[low];
        arr[low] = temp;

        return i - 1;
    }

    /*
     * The main function that implements QuickSort() arr[] --> Array to be sorted,
     * low --> Starting index, high --> Ending index
     */
    public int sort(int arr[], int low, int high) {
        if (low < high) {
            /*
             * pi is partitioning index, arr[pi] is now at right place
             */
            int pi = partition(arr, low, high);

            // Recursively sort elements before
            // partition and after partition
            sort(arr, low, pi - 1);
            sort(arr, pi + 1, high);
        }

        return comparisons;
    }

    // Driver program
    public static void main(String args[]) throws IOException, URISyntaxException {
        int[] arr = Files
                .readAllLines(
                        Paths.get(QuickSort.class.getClassLoader().getResource("course1/QuickSort_Test_Case.txt").toURI()))
                .stream().mapToInt(s -> Integer.parseInt(s)).toArray();

        System.out.println("Comparisons with LEFT Pivot: " + new QuickSort(Pivot.LEFT).sort(arr.clone(), 0, arr.length - 1));
        System.out
                .println("Comparisons with MEDIAN Pivot: " + new QuickSort(Pivot.MEDIAN).sort(arr.clone(), 0, arr.length - 1));
        System.out.println("Comparisons with RIGHT Pivot: " + new QuickSort(Pivot.RIGHT).sort(arr.clone(), 0, arr.length - 1));
    }
}