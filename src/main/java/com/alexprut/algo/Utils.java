package com.alexprut.algo;

public class Utils {

  /**
   * Time complexity: O(1)
   *
   * <p>Space complexity: O(n) total and O(1) auxiliary
   */
  public static void swap(int[] arr, int i, int j) {
    int tmp = arr[i];
    arr[i] = arr[j];
    arr[j] = tmp;
  }

  /**
   * Time complexity: O(n)
   *
   * <p>Space complexity: O(n) total and O(1) auxiliary
   */
  public static void reverse(int[] arr, int start, int end) {
    for (int i = start; i < (start + end) / 2; i++) {
      Utils.swap(arr, i, end - i);
    }
  }

  /**
   * Time complexity: O(n)
   *
   * <p>Space complexity: O(n) total and O(1) auxiliary
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
   * <p>Space complexity: O(n) total and O(1) auxiliary
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
   * <p>Space complexity: O(n) total and O(1) auxiliary
   */
  public static boolean isPalindrome(String s) {
    for (int i = 0; i < s.length() / 2; i++) {
      if (s.charAt(i) != s.charAt(s.length() - i - 1)) {
        return false;
      }
    }

    return true;
  }

  /**
   * The longest increasing subsequence problem is to find a subsequence of a given sequence in
   * which the subsequence's elements are in sorted order, lowest to highest, and in which the
   * subsequence is as long as possible. This subsequence is not necessarily contiguous, or unique.
   *
   * <p>Time complexity: O(n^2)
   *
   * <p>Space complexity: O(n)
   *
   * <p>TODO implement a O(nlogn) algorithm
   *
   * @see <a
   *     href="https://en.wikipedia.org/wiki/Longest_increasing_subsequence">https://en.wikipedia.org/wiki/Longest_increasing_subsequence</a>
   */
  public static int[] longestIncreasingSubsequence(int[] arr) {
    int[] prev = new int[arr.length];
    int[] dp = new int[arr.length];
    int maxLength = 0;
    int indexMaxEnd = -1;
    for (int i = 0; i < arr.length; i++) {
      dp[i] = 1;
      prev[i] = -1;
      for (int j = i; j >= 0; j--) {
        if (dp[j] + 1 > dp[i] && arr[j] < arr[i]) {
          dp[i] = dp[j] + 1;
          prev[i] = j;
        }
        if (dp[i] > maxLength) {
          maxLength = dp[i];
          indexMaxEnd = i;
        }
      }
    }
    int[] lis = new int[maxLength];
    for (int i = maxLength - 1; i >= 0; i--) {
      lis[i] = arr[indexMaxEnd];
      indexMaxEnd = prev[indexMaxEnd];
    }
    return lis;
  }
}
