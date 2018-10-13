package com.alexprut.algo.algorithms.search;

import org.junit.Assert;
import org.junit.Test;

public class BinarySearchTest {

  @Test
  public void shouldFindIndex() {
    int[] a = new int[] {1,2,3,4,5,6};
    Assert.assertEquals(BinarySearch.binarySearch(a, 1), 0);
    Assert.assertEquals(BinarySearch.binarySearch(a, 2), 1);
    Assert.assertEquals(BinarySearch.binarySearch(a, 4), 3);
    Assert.assertEquals(BinarySearch.binarySearch(a, -1), -1);
    Assert.assertEquals(BinarySearch.binarySearch(a, 7), -1);
    Assert.assertEquals(BinarySearch.binarySearch(new int[] {}, 7), -1);
  }
}
