package com.alexprut.algo.algorithms.sorting;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class InsertionSortTest {

  @Test
  public void shouldSort() {
    int[] toSort = new int[] {5, 2, 1, 9, 0, 33, 3, 3, 0};
    int[] expected = new int[] {0, 0, 1, 2, 3, 3, 5, 9, 33};

    Assertions.assertArrayEquals(expected, InsertionSort.insertionSort(toSort));
  }

  @Test
  public void shouldSortInverseSorted() {
    int[] toSort = new int[] {33, 9, 5, 3, 3, 2, 1, 0, 0};
    int[] expected = new int[] {0, 0, 1, 2, 3, 3, 5, 9, 33};

    Assertions.assertArrayEquals(expected, InsertionSort.insertionSort(toSort));
  }

  @Test
  public void shouldSortSorted() {
    int[] expected = new int[] {0, 0, 1, 2, 3, 3, 5, 9, 33};

    Assertions.assertArrayEquals(expected, InsertionSort.insertionSort(expected));
  }

  @Test
  public void shouldHandleEdgeCases() {
    Assertions.assertArrayEquals(new int[] {}, InsertionSort.insertionSort(new int[] {}));
    Assertions.assertArrayEquals(new int[] {1}, InsertionSort.insertionSort(new int[] {1}));
  }
}
