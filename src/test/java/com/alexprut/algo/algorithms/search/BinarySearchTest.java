package com.alexprut.algo.algorithms.search;

import org.junit.Assert;
import org.junit.Test;

public class BinarySearchTest {

  @Test
  public void shouldFindIndex() {
    int[] a = new int[] {1, 2, 3, 4, 5, 6};
    Assert.assertEquals(0, BinarySearch.binarySearch(a, 1));
    Assert.assertEquals(1, BinarySearch.binarySearch(a, 2));
    Assert.assertEquals(3, BinarySearch.binarySearch(a, 4));
    Assert.assertEquals(-1, BinarySearch.binarySearch(a, -1));
    Assert.assertEquals(-1, BinarySearch.binarySearch(a, 7));
    Assert.assertEquals(-1, BinarySearch.binarySearch(new int[] {}, 7));
  }

  @Test
  public void shouldFindIndexFirstLowest() {
    int[] a = new int[] {1, 2, 3, 5, 6, 7};
    Assert.assertEquals(1, BinarySearch.binarySearchFirstLowest(a, 2));
    Assert.assertEquals(-1, BinarySearch.binarySearchFirstLowest(a, 0));
    Assert.assertEquals(2, BinarySearch.binarySearchFirstLowest(a, 4));
    Assert.assertEquals(5, BinarySearch.binarySearchFirstLowest(a, 8));
  }

  @Test
  public void shouldFindIndexFirstHighest() {
    int[] a = new int[] {1, 2, 3, 5, 6, 7};
    Assert.assertEquals(1, BinarySearch.binarySearchFirstHighest(a, 2));
    Assert.assertEquals(0, BinarySearch.binarySearchFirstHighest(a, 0));
    Assert.assertEquals(3, BinarySearch.binarySearchFirstHighest(a, 4));
    Assert.assertEquals(6, BinarySearch.binarySearchFirstHighest(a, 8));
  }
}
