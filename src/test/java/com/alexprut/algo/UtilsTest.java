package com.alexprut.algo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UtilsTest {

  @Test
  public void shouldFindMax() {
    int[] arr = new int[] {1, 2, 0, 9, 5};
    try {
      Assertions.assertEquals(9, Utils.max(arr));
    } catch (Exception e) {
      Assertions.fail("Should not thrown an exception");
    }
  }

  @Test
  public void shouldFindMin() {
    int[] arr = new int[] {1, 2, 0, 9, 5};
    try {
      Assertions.assertEquals(0, Utils.min(arr));
    } catch (Exception e) {
      Assertions.fail("Should not thrown an exception");
    }
  }

  @Test
  public void shouldSwap() {
    int[] arr = new int[] {1, 2, 0, 9, 5};
    Utils.swap(arr, 0, 2);
    Assertions.assertArrayEquals(new int[] {0, 2, 1, 9, 5}, arr);
  }

  @Test
  public void shouldReverse() {
    int[] arr = new int[] {1, 2, 3, 4, 5};
    Utils.reverse(arr, 0, arr.length - 1);
    Assertions.assertArrayEquals(new int[] {5, 4, 3, 2, 1}, arr);
  }

  @Test
  public void shouldCheckPalindrome() {
    Assertions.assertTrue(Utils.isPalindrome(""));
    Assertions.assertTrue(Utils.isPalindrome("s"));
    Assertions.assertTrue(Utils.isPalindrome("asa"));
    Assertions.assertTrue(Utils.isPalindrome("assa"));

    Assertions.assertFalse(Utils.isPalindrome("as"));
    Assertions.assertFalse(Utils.isPalindrome("asas"));
  }

  @Test
  public void shouldFindLongestIncreasingSubsequence() {
    Assertions.assertArrayEquals(
        new int[] {3, 7, 40, 80},
        Utils.longestIncreasingSubsequence(new int[] {50, 3, 10, 7, 40, 80}));
    Assertions.assertArrayEquals(
        new int[] {1, 2}, Utils.longestIncreasingSubsequence(new int[] {1, 2, 1}));
    Assertions.assertArrayEquals(
        new int[] {3}, Utils.longestIncreasingSubsequence(new int[] {3, 2, 1}));
    Assertions.assertArrayEquals(
        new int[] {1, 2, 3}, Utils.longestIncreasingSubsequence(new int[] {1, 2, 3}));
    Assertions.assertArrayEquals(new int[] {}, Utils.longestIncreasingSubsequence(new int[] {}));
  }

  @Test
  public void shouldFindLongestCommonSubsequence() {
    Assertions.assertEquals(0, Utils.longestCommonSubsequence("", ""));
    Assertions.assertEquals(0, Utils.longestCommonSubsequence("xyz", "abcad"));
    Assertions.assertEquals(1, Utils.longestCommonSubsequence("aaa", "abcd"));
    Assertions.assertEquals(2, Utils.longestCommonSubsequence("aaa", "abcad"));
  }

  @Test
  public void shouldGetDigitAtIndex() {
    int number = 32981;
    Assertions.assertEquals(1, Utils.getDigitAtIndex(number, 1));
    Assertions.assertEquals(8, Utils.getDigitAtIndex(number, 2));
    Assertions.assertEquals(9, Utils.getDigitAtIndex(number, 3));
    Assertions.assertEquals(2, Utils.getDigitAtIndex(number, 4));
    Assertions.assertEquals(3, Utils.getDigitAtIndex(number, 5));
    Assertions.assertEquals(0, Utils.getDigitAtIndex(number, 6));
    Assertions.assertEquals(0, Utils.getDigitAtIndex(number, 7));

    Assertions.assertEquals(9, Utils.getDigitAtIndex(9, 1));
    Assertions.assertEquals(0, Utils.getDigitAtIndex(9, 2));
  }
}
