package com.alexprut.algo.algorithms.sorting;

import org.junit.Assert;
import org.junit.Test;

public class RadixSortTest {

  @Test
  public void shouldSort() {
    int[] toSort = new int[] {5, 2, 1, 9, 0, 33, 3, 3, 0};
    int[] expected = new int[] {0, 0, 1, 2, 3, 3, 5, 9, 33};

    Assert.assertArrayEquals(expected, RadixSort.radixSort(toSort, 2));
  }

  @Test
  public void shouldSortInverseSorted() {
    int[] toSort = new int[] {33, 9, 5, 3, 3, 2, 1, 0, 0};
    int[] expected = new int[] {0, 0, 1, 2, 3, 3, 5, 9, 33};

    Assert.assertArrayEquals(expected, RadixSort.radixSort(toSort, 2));
  }

  @Test
  public void shouldSortSorted() {
    int[] expected = new int[] {0, 0, 1, 2, 3, 3, 5, 9, 33};

    Assert.assertArrayEquals(expected, RadixSort.radixSort(expected, 2));
  }

  @Test
  public void shouldHandleEdgeCases() {
    Assert.assertArrayEquals(new int[] {}, RadixSort.radixSort(new int[] {}, 1));
    Assert.assertArrayEquals(new int[] {1}, RadixSort.radixSort(new int[] {1}, 1));
  }
}
