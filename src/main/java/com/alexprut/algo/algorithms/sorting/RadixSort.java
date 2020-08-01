package com.alexprut.algo.algorithms.sorting;

public class RadixSort {

  /**
   * Given an array of n integers, sort the elements in ascending order. The algorithm uses {@link
   * CountingSort}, the sorting is stable. Radix sort solves the problem of sorting
   * counterintuitively, by sorting on the least significant digit first. The process continues
   * until the elements have been sorted on all 'd' digits.
   *
   * <p>Example: given the elements [5, 2, 1, 9, 3, 3, 0] the sorted elements would be [0, 1, 2, 3,
   * 3, 5, 9]
   *
   * <p>Time complexity: Θ(d(k + n)), best case complexity Θ(n)
   *
   * <p>Space complexity: Θ(d)
   *
   * @see <a
   *     href="https://en.wikipedia.org/wiki/Radix_sort">https://en.wikipedia.org/wiki/Radix_sort</a>
   * @param arr the elements to sort
   * @param maxDigits the number of digits to sort
   * @return the sorted elements
   */
  public static int[] radixSort(int[] arr, int maxDigits) {
    int[] res = arr;
    for (int i = 1; i <= maxDigits; i++) {
      res = CountingSort.countingSort(res, i);
    }
    return res;
  }
}
