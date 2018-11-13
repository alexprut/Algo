package com.alexprut.algo;

public class Utils {

  /**
   * Time complexity: O(1)
   *
   * Space complexity: O(n) total and O(1) auxiliary
   */
  public static void swap(int[] arr, int i, int j) {
    int tmp = arr[i];
    arr[i] = arr[j];
    arr[j] = tmp;
  }

  /**
   * Time complexity: O(n)
   *
   * Space complexity: O(n) total and O(1) auxiliary
   */
  public static void reverse(int[] arr, int start, int end) {
    for (int i = start; i < (start + end) / 2; i++) {
      Utils.swap(arr, i, end - i);
    }
  }

  /**
   * Time complexity: O(n)
   *
   * Space complexity: O(n) total and O(1) auxiliary
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
   *
   * Space complexity: O(n) total and O(1) auxiliary
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

  /**
   * Time complexity: O(n)
   *
   * Space complexity: O(n) total and O(1) auxiliary
   */
  public static boolean isPalindrome(String s) {
    for (int i = 0; i < s.length() / 2; i++) {
      if (s.charAt(i) != s.charAt(s.length() - i - 1)) {
        return false;
      }
    }

    return true;
  }
}
