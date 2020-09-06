package com.alexprut.algo.algorithms.sorting;

import com.alexprut.algo.Utils;

public class CountingSort {

  private CountingSort() {}

  /**
   * Counting sort is stable and linear time sorting algorithm. It assumes that each of the n input
   * elements is an integer in the range 0 to k, for some integer k.
   *
   * <p>Example: given the elements [5, 2, 1, 9, 3, 3, 0] the sorted elements would be [0, 1, 2, 3,
   * 3, 5, 9]
   *
   * <p>Time complexity: Θ(n)
   *
   * <p>Space complexity: Θ(n)
   *
   * @see <a
   *     href="https://en.wikipedia.org/wiki/Counting_sort">https://en.wikipedia.org/wiki/Counting_sort</a>
   * @param arr the elements to sort
   * @return the sorted elements
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
   * Similar to {@link #countingSort(int[])}. Given an array of n integers, sort the elements in
   * ascending order based on the specified digit index. The sorting in stable.
   *
   * <p>Example: given the elements [387, 468, 134, 123, 68, 221, 769, 37, 7] the sorted elements at
   * index 1 would be [221, 123, 134, 387, 37, 7, 468, 68, 769]
   *
   * <pre>
   * [221, 123, 134, 387, 37, 7, 468, 68, 769]
   *    ↑    ↑    ↑    ↑   ↑  ↑    ↑   ↑    ↑
   * </pre>
   *
   * <p>Time complexity: Θ(n)
   *
   * <p>Space complexity: Θ(n)
   *
   * @param arr the elements to sort
   * @param digitIndex the digit index to sort the elements with
   * @return the sorted elements
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
