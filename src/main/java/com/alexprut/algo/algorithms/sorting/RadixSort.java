package com.alexprut.algo.algorithms.sorting;

public class RadixSort {

  /**
   * Given an array of n integers, sort the elements in ascending order . The sorting in stable.
   *
   * <p>Time complexity: Θ(d(k + n)), best case complexity Θ(n)
   *
   * <p>Space complexity: Θ(d)
   *
   * @param arr
   * @param maxDigits
   * @return
   */
  public static int[] radixSort(int[] arr, int maxDigits) {
    int[] res = arr;
    for (int i = 1; i <= maxDigits; i++) {
      res = CountingSort.countingSort(res, i);
    }
    return res;
  }
}
