package com.alexprut.algo.algorithms.sorting;

import com.alexprut.algo.Utils;

public class CountingSort {

  /**
   * Given an array of n integers, sort the elements in ascending order. The sorting in stable.
   * Assumes that each of the n input elements is an integer in the range 0 to k, for some integer
   * k.
   *
   * <p>Time complexity: Θ(n)
   *
   * <p>Space complexity: Θ(n)
   *
   * @param arr
   * @return
   */
  public static int[] countingSort(int[] arr) {
    int[] result = new int[arr.length];
    int max;
    try {
      max = Utils.max(arr);
    } catch (Exception e) {
      return arr;
    }
    int[] c = new int[max + 1];
    for (int element : arr) {
      c[element]++;
    }
    for (int i = 1; i < c.length; i++) {
      c[i] += c[i - 1];
    }
    for (int j = arr.length - 1; j >= 0; j--) {
      result[c[arr[j]] - 1] = arr[j];
      c[arr[j]]--;
    }

    return result;
  }

  /**
   * Given an array of n integers, sort the elements in ascending order based on the specified digit
   * index. The sorting in stable.
   *
   * <p>Time complexity: Θ(n)
   *
   * <p>Space complexity: Θ(n)
   *
   * @param arr
   * @param digitIndex
   * @return
   */
  public static int[] countingSort(int[] arr, int digitIndex) {
    int[] result = new int[arr.length];
    int[] c = new int[10];
    for (int element : arr) {
      c[Utils.getDigitAtIndex(element, digitIndex)]++;
    }
    for (int i = 1; i < c.length; i++) {
      c[i] += c[i - 1];
    }
    for (int j = arr.length - 1; j >= 0; j--) {
      result[c[Utils.getDigitAtIndex(arr[j], digitIndex)] - 1] = arr[j];
      c[Utils.getDigitAtIndex(arr[j], digitIndex)]--;
    }

    return result;
  }
}
