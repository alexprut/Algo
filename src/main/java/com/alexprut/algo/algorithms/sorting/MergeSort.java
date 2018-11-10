package com.alexprut.algo.algorithms.sorting;

public class MergeSort {

  /**
   * It is a divide-and-conquer algorithms
   *
   * Time complexity: Θ(nlogn) in the worst and best case
   *
   * Space complexity; O(n) total with O(n) auxiliary the algorithms is not in place
   */
  public static void mergeSort(int[] arr, int p, int r) {
    if (p < r) {
      int q = (p + r) / 2;
      mergeSort(arr, p, q);
      mergeSort(arr, q + 1, r);
      merge(arr, p, q, r);
    }
  }

  /**
   * Time complexity: Θ(n)
   *
   * Space complexity: O(n) total with O(n) auxiliary space
   */
  private static void merge(int[] arr, int p, int q, int r) {
    int[] left = new int[q - p + 2];
    int[] right = new int[r - q + 1];

    left[left.length - 1] = Integer.MAX_VALUE;
    right[right.length - 1] = Integer.MAX_VALUE;

    for (int i = 0; i < left.length - 1; i++) {
      left[i] = arr[i + p];
    }

    for (int i = 0; i < right.length - 1; i++) {
      right[i] = arr[i + q + 1];
    }

    int i = 0, j = 0;
    for (int k = p; k <= r; k++) {
      if (left[i] < right[j]) {
        arr[k] = left[i];
        i++;
      } else {
        arr[k] = right[j];
        j++;
      }
    }
  }
}
