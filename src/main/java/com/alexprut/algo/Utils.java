package com.alexprut.algo;

public class Utils {

  /**
   * Swaps the first element with the second.
   *
   * <p>Time complexity: O(1)
   *
   * <p>Space complexity: O(n) total and O(1) auxiliary
   *
   * @param arr array of elements
   * @param i first element
   * @param j second element
   */
  public static void swap(int[] arr, int i, int j) {
    int tmp = arr[i];
    arr[i] = arr[j];
    arr[j] = tmp;
  }

  /**
   * Reverse the elements order.
   *
   * <p>Time complexity: O(n)
   *
   * <p>Space complexity: O(n) total and O(1) auxiliary
   *
   * @param arr the elements to reverse
   * @param start the starting point to start the reversion
   * @param end the ending point to end the reversion
   */
  public static void reverse(int[] arr, int start, int end) {
    for (int i = start; i < (start + end) / 2; i++) {
      Utils.swap(arr, i, end - i);
    }
  }

  /**
   * Finds and returns the maximum element.
   *
   * <p>Time complexity: O(n)
   *
   * <p>Space complexity: O(n) total and O(1) auxiliary
   *
   * @param arr the array of elements
   * @return the maximum element
   * @throws Exception if the array is empty
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
   * Finds and returns the minimum element.
   *
   * <p>Time complexity: O(n)
   *
   * <p>Space complexity: O(n) total and O(1) auxiliary
   *
   * @param arr the array of elements
   * @return the minimum element
   * @throws Exception if the array is empty
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
   * Checks if a string is a palindrome. A palindrome is a word which reads the same backward as
   * forward.
   *
   * <p>Example: the words "madam" and "kayak" are palindromes.
   *
   * <p>Time complexity: O(n)
   *
   * <p>Space complexity: O(n) total and O(1) auxiliary
   *
   * @param s the string to check
   * @return true if the word is a palindrome
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
   * <p>Example: given [50, 3, 10, 7, 40, 80] the longest increasing subsequence is [3, 7, 40, 80].
   *
   * <p>Time complexity: O(n^2) TODO implement a O(nlogn) algorithm
   *
   * <p>Space complexity: O(n)
   *
   * @see <a
   *     href="https://en.wikipedia.org/wiki/Longest_increasing_subsequence">https://en.wikipedia.org/wiki/Longest_increasing_subsequence</a>
   * @param arr sequence of elements
   * @return the longest increasing subsequence
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

  /**
   * Computes the length of the longest common subsequence. LCS is the problem of finding the
   * longest subsequence common to two sequences. The sequence does not need to be contiguous.
   *
   * <p>Example: given "aaa" and "abcad" the length of the longest common subsequence is 2, i.e.
   * "aa" and "aa".
   *
   * <p>Time complexity: O(n^2)
   *
   * <p>Space complexity: O(n^2)
   *
   * @see <a
   *     href="https://en.wikipedia.org/wiki/Longest_common_subsequence_problem">https://en.wikipedia.org/wiki/Longest_common_subsequence_problem</a>
   * @param a the first word
   * @param b the second work
   * @return the longest common subsequence
   */
  public static int longestCommonSubsequence(String a, String b) {
    int[][] dp = new int[a.length() + 1][b.length() + 1];
    for (int i = 1; i <= a.length(); i++) {
      for (int j = 1; j <= b.length(); j++) {
        if (a.charAt(i - 1) == b.charAt(j - 1)) {
          dp[i][j] = dp[i - 1][j - 1] + 1;
        } else {
          dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
        }
      }
    }
    return dp[dp.length - 1][dp[0].length - 1];
  }

  /**
   * Given a number and an index, return the digit at the specified index.
   *
   * <p>Example: if number is 918, the digit at index 1 is `8`, the digit at index 2 is `1`, the
   * digit at index 3 is `9`.
   *
   * <p>Time complexity: O(1)
   *
   * <p>Space complexity: O(1)
   *
   * @param number the number
   * @param digitIndex the index
   * @return the digit at the specified index
   */
  public static int getDigitAtIndex(int number, int digitIndex) {
    return number / (int) Math.pow(10, digitIndex - 1) % 10;
  }
}
