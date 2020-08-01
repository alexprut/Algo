package com.alexprut.algo.algorithms.sorting;

import org.junit.Assert;
import org.junit.Test;

public class QuickSortTest {

  @Test
  public void shouldSort() {
    int[] toSort = new int[] {5, 2, 1, 9, 0, 33, 3, 0, 3};
    int[] expected = new int[] {0, 0, 1, 2, 3, 3, 5, 9, 33};

    QuickSort.quickSort(toSort, 0, toSort.length - 1);
    Assert.assertArrayEquals(expected, toSort);
  }

  @Test
  public void shouldSortInverseSorted() {
    int[] toSort = new int[] {33, 9, 5, 3, 3, 2, 1, 0, 0};
    int[] expected = new int[] {0, 0, 1, 2, 3, 3, 5, 9, 33};

    QuickSort.quickSort(toSort, 0, toSort.length - 1);
    Assert.assertArrayEquals(expected, toSort);
  }

  @Test
  public void shouldSortSorted() {
    int[] expected = new int[] {0, 0, 1, 2, 3, 3, 5, 9, 33};

    QuickSort.quickSort(expected, 0, expected.length - 1);
    Assert.assertArrayEquals(expected, expected);
  }

  @Test
  public void shouldHandleEdgeCases() {
    int[] toSort = new int[] {};
    QuickSort.quickSort(toSort, 0, toSort.length - 1);
    Assert.assertArrayEquals(new int[] {}, toSort);

    toSort = new int[] {1};
    QuickSort.quickSort(toSort, 0, toSort.length - 1);
    Assert.assertArrayEquals(new int[] {1}, toSort);
  }
}
