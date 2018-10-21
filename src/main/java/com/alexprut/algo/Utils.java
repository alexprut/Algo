package com.alexprut.algo;

public class Utils {

  public static void swap(int[] arr, int i, int j) {
    int tmp = arr[i];
    arr[i] = arr[j];
    arr[j] = tmp;
  }

  /**
   * Time complexity: O(n)
   */
  public static int max(int[] arr) throws Exception {
    if (arr.length == 0) {
      throw new Exception("Array is empty");
    }

    int max = Integer.MIN_VALUE;
    for (int e : arr) {
      max = Math.max(max, e);
    }

    return max;
  }

  /**
   * Time complexity: O(n)
   */
  public static int min(int[] arr) throws Exception {
    if (arr.length == 0) {
      throw new Exception("Array is empty");
    }

    int min = Integer.MAX_VALUE;
    for (int e : arr) {
      min = Math.min(min, e);
    }

    return min;
  }
}