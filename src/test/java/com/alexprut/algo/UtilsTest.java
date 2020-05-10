package com.alexprut.algo;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;

import org.junit.Assert;
import org.junit.Test;

public class UtilsTest {

  @Test
  public void shouldFindMax() {
    int[] arr = new int[] {1, 2, 0, 9, 5};
    try {
      Assert.assertEquals(9, Utils.max(arr));
    } catch (Exception e) {
      fail("Should not thrown an exception");
    }
  }

  @Test
  public void shouldFindMin() {
    int[] arr = new int[] {1, 2, 0, 9, 5};
    try {
      Assert.assertEquals(0, Utils.min(arr));
    } catch (Exception e) {
      fail("Should not thrown an exception");
    }
  }

  @Test
  public void shouldSwap() {
    int[] arr = new int[] {1, 2, 0, 9, 5};
    Utils.swap(arr, 0, 2);
    Assert.assertArrayEquals(new int[] {0, 2, 1, 9, 5}, arr);
  }

  @Test
  public void shouldReverse() {
    int[] arr = new int[] {1, 2, 3, 4, 5};
    Utils.reverse(arr, 0, arr.length - 1);
    Assert.assertArrayEquals(new int[] {5, 4, 3, 2, 1}, arr);
  }

  @Test
  public void shouldCheckPalindrome() {
    Assert.assertTrue(Utils.isPalindrome(""));
    Assert.assertTrue(Utils.isPalindrome("s"));
    Assert.assertTrue(Utils.isPalindrome("asa"));
    Assert.assertTrue(Utils.isPalindrome("assa"));

    Assert.assertFalse(Utils.isPalindrome("as"));
    Assert.assertFalse(Utils.isPalindrome("asas"));
  }

  @Test
  public void shouldFindLongestIncreasingSubsequence() {
    Assert.assertArrayEquals(
        new int[] {3, 7, 40, 80},
        Utils.longestIncreasingSubsequence(new int[] {50, 3, 10, 7, 40, 80}));
    Assert.assertArrayEquals(
        new int[] {1, 2}, Utils.longestIncreasingSubsequence(new int[] {1, 2, 1}));
    Assert.assertArrayEquals(
        new int[] {3}, Utils.longestIncreasingSubsequence(new int[] {3, 2, 1}));
    Assert.assertArrayEquals(
        new int[] {1, 2, 3}, Utils.longestIncreasingSubsequence(new int[] {1, 2, 3}));
    Assert.assertArrayEquals(new int[] {}, Utils.longestIncreasingSubsequence(new int[] {}));
  }

  @Test
  public void shouldFindLongestCommonSubsequence() {
    assertEquals(0, Utils.longestCommonSubsequence("", ""));
    assertEquals(0, Utils.longestCommonSubsequence("xyz", "abcad"));
    assertEquals(1, Utils.longestCommonSubsequence("aaa", "abcd"));
    assertEquals(2, Utils.longestCommonSubsequence("aaa", "abcad"));
  }

  @Test
  public void shoouldGetDigitAtIndex() {
    int number = 32981;
    Assert.assertEquals(1, Utils.getDigitAtIndex(number, 1));
    Assert.assertEquals(8, Utils.getDigitAtIndex(number, 2));
    Assert.assertEquals(9, Utils.getDigitAtIndex(number, 3));
    Assert.assertEquals(2, Utils.getDigitAtIndex(number, 4));
    Assert.assertEquals(3, Utils.getDigitAtIndex(number, 5));
    Assert.assertEquals(0, Utils.getDigitAtIndex(number, 6));
    Assert.assertEquals(0, Utils.getDigitAtIndex(number, 7));

    Assert.assertEquals(9, Utils.getDigitAtIndex(9, 1));
    Assert.assertEquals(0, Utils.getDigitAtIndex(9, 2));
  }
}
