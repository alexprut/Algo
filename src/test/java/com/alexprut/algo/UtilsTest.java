package com.alexprut.algo;

import static junit.framework.TestCase.fail;

import org.junit.Assert;
import org.junit.Test;

public class UtilsTest {

  @Test
  public void shouldFindMax() {
    int[] arr = new int[]{1, 2, 0, 9, 5};
    try {
      Assert.assertEquals(9, Utils.max(arr));
    } catch (Exception e) {
      fail("Should not thrown an exception");
    }
  }

  @Test
  public void shouldFindMin() {
    int[] arr = new int[]{1, 2, 0, 9, 5};
    try {
      Assert.assertEquals(0, Utils.min(arr));
    } catch (Exception e) {
      fail("Should not thrown an exception");
    }
  }

  @Test
  public void shouldSwap() {
    int[] arr = new int[]{1, 2, 0, 9, 5};
    Utils.swap(arr, 0, 2);
    Assert.assertArrayEquals(new int[]{0, 2, 1, 9, 5}, arr);
  }

  @Test
  public void shouldReverse() {
    int[] arr = new int[]{1, 2, 3, 4, 5};
    Utils.reverse(arr, 0, arr.length - 1);
    Assert.assertArrayEquals(new int[]{5, 4, 3, 2, 1}, arr);
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
}
