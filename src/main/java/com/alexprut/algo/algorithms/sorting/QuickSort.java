package com.alexprut.algo.algorithms.sorting;

import com.alexprut.algo.Utils;

/**
 * Time complexity:
 * Worst case is O(n^2), when the array is already sorted
 * Best case is O(nlogn)
 */
public class QuickSort {

  public static int[] quickSort(int[] arr, int start, int end) {
    if (start < end) {
      int pivot = partition(arr, start, end);
      quickSort(arr, start, pivot - 1);
      quickSort(arr, pivot + 1, end);
    }

    return arr;
  }

  private static int partition(int[] arr, int start, int end) { // asd
    int x = arr[end];
    int i = start - 1;
    for (int j = start; j < end; j++) {
      if (arr[j] < x) {
        i++;
        Utils.swap(arr, i, j);
      }
    }
    Utils.swap(arr, i + 1, end);
    return i + 1;
  }
}