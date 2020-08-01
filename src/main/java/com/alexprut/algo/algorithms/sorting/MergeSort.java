package com.alexprut.algo.algorithms.sorting;

public class MergeSort {

  /**
   * Merge sort is a sorting divide-and-conquer algorithms that is not in place.
   *
   * <p>Example: given the elements [5, 2, 1, 9, 3, 3, 0] the sorted elements would be [0, 1, 2, 3,
   * 3, 5, 9]
   *
   * <p>Time complexity: Θ(nlogn)
   *
   * <p>Space complexity; O(n) total with O(n) auxiliary
   *
   * @see <a
   *     href="https://en.wikipedia.org/wiki/Merge_sort">https://en.wikipedia.org/wiki/Merge_sort</a>
   * @param arr the elements to sort
   * @param p the starting index of the sort
   * @param r the ending index of the sort
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
   * Helper method used in {@link #mergeSort(int[], int, int)}. Merges two lists of elements in
   * order.
   *
   * <p>Time complexity: Θ(n)
   *
   * <p>Space complexity: O(n) total with O(n) auxiliary space
   *
   * @param arr the elements to sort
   * @param p the start index
   * @param q the middle index
   * @param r the end index
   */
  private static void merge(int[] arr, int p, int q, int r) {
    // p <= q < r
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
