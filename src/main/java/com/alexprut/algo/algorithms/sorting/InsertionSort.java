package com.alexprut.algo.algorithms.sorting;

public class InsertionSort {

  /**
   * O(n^2) in the worst case, when the array is sorted in reverse order
   * O(n) best case, when the array is already sorted
   * In-place algorithm, it rearranges the numbers within the given array,
   * with at most a constant number of them stored outside the array at any time
   */
  public static int[] insertionSort(int[] arr) {
    for (int i = 1; i < arr.length; i++) {
      int j = i;
      while (j > 0 && arr[j - 1] > arr[j]) {
        int tmp = arr[j - 1];
        arr[j - 1] = arr[j];
        arr[j] = tmp;
        j--;
      }
    }
    return arr;
  }
}
