package com.alexprut.algo.algorithms.sorting;

import com.alexprut.algo.Utils;

public class QuickSort {

  /**
   * Quick sort is a it's a sorting and divide-and-conquer algorithm. Quicksort is often the best
   * practical choice for sorting because it is remarkably efficient on the average.
   *
   * <p>Example: given the elements [5, 2, 1, 9, 3, 3, 0] the sorted elements would be [0, 1, 2, 3,
   * 3, 5, 9]
   *
   * <p>Time complexity: O(nlogn) at average, O(n^2) in the worst case (when the elements are
   * already sorted in reverse order)
   *
   * <p>Space complexity: O(n)
   *
   * @see <a
   *     href="https://en.wikipedia.org/wiki/Quicksort">https://en.wikipedia.org/wiki/Quicksort</a>
   * @param arr the elements to sort
   * @param start the starting index of the sort
   * @param end the ending index of the sort
   * @return the sorted elements
   */
  public static int[] quickSort(int[] arr, int start, int end) {
    if (start < end) {
      int pivot = partition(arr, start, end);
      quickSort(arr, start, pivot - 1);
      quickSort(arr, pivot + 1, end);
    }

    return arr;
  }

  /**
   * Helper method used in {@link #quickSort(int[], int, int)}. Partition (rearrange) the elements
   * A[p...r] into two (possibly empty) sub-arrays A[p...q-1] and A[q+1...r] such that each element
   * of A[p...q-1] is less than or equal to A[q], which is, in turn, less than or equal to each
   * element of A[q+1...r].
   *
   * <p>Example: given the elements [5, 2, 1, 9, 0, 33, 3, 0, 3] with the call partition(element, 0,
   * 8) the elements after the call will be partitioned in [2, 1, 0, 0, 3, 33, 3, 9, 5] and the
   * pivot would be at index 4.
   *
   * <pre>
   * [2, 1, 0, 0, 3, 33, 3, 9, 5]
   *              ^
   *            pivot
   *
   * [2, 1, 0, 0 &lt;= 3 =&gt; 33, 3, 9, 5]
   * </pre>
   *
   * <p>Time complexity: O(n)
   *
   * <p>Space complexity: O(n)
   *
   * @param arr the elements to partition
   * @param start the starting index of the partition
   * @param end the ending index of the partition
   * @return the partitioned elements
   */
  protected static int partition(int[] arr, int start, int end) {
    int x = arr[end]; // Pivot element
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
