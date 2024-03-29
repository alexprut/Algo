package com.alexprut.algo.algorithms.sorting;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CountingSortTest {

  @Test
  public void shouldSort() {
    int[] toSort = new int[] {5, 2, 1, 9, 0, 33, 3, 3, 0};
    int[] expected = new int[] {0, 0, 1, 2, 3, 3, 5, 9, 33};

    Assertions.assertArrayEquals(expected, CountingSort.countingSort(toSort));
  }

  @Test
  public void shouldSortInverseSorted() {
    int[] toSort = new int[] {33, 9, 5, 3, 3, 2, 1, 0, 0};
    int[] expected = new int[] {0, 0, 1, 2, 3, 3, 5, 9, 33};

    Assertions.assertArrayEquals(expected, CountingSort.countingSort(toSort));
  }

  @Test
  public void shouldSortSorted() {
    int[] expected = new int[] {0, 0, 1, 2, 3, 3, 5, 9, 33};

    Assertions.assertArrayEquals(expected, CountingSort.countingSort(expected));
  }

  @Test
  public void shouldHandleEdgeCases() {
    Assertions.assertArrayEquals(new int[] {}, CountingSort.countingSort(new int[] {}));
    Assertions.assertArrayEquals(new int[] {1}, CountingSort.countingSort(new int[] {1}));
  }

  @Test
  public void shouldSortOnDigitIndex() {
    Assertions.assertArrayEquals(
        new int[] {221, 123, 134, 387, 37, 7, 468, 68, 769},
        CountingSort.countingSort(new int[] {387, 468, 134, 123, 68, 221, 769, 37, 7}, 1));
    Assertions.assertArrayEquals(
        new int[] {221, 123, 134, 387, 37, 7, 468, 68, 769},
        CountingSort.countingSort(new int[] {221, 123, 134, 387, 37, 7, 468, 68, 769}, 1));
    Assertions.assertArrayEquals(
        new int[] {7, 221, 123, 134, 37, 468, 68, 769, 387},
        CountingSort.countingSort(new int[] {221, 123, 134, 387, 37, 7, 468, 68, 769}, 2));
    Assertions.assertArrayEquals(
        new int[] {7, 37, 68, 123, 134, 221, 387, 468, 769},
        CountingSort.countingSort(new int[] {7, 221, 123, 134, 37, 468, 68, 769, 387}, 3));
    Assertions.assertArrayEquals(
        new int[] {7, 37, 68, 123, 134, 221, 387, 468, 769},
        CountingSort.countingSort(new int[] {7, 37, 68, 123, 134, 221, 387, 468, 769}, 4));
  }
}
