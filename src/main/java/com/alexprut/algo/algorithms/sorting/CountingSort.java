package com.alexprut.algo.algorithms.sorting;

import com.alexprut.algo.Utils;

/**
 * Assumes that each of the n input elements is an integer
 * in the range 0 to k, for some integer k
 *
 * Time complexity:
 * Θ(n)
 */
public class CountingSort {

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
}
