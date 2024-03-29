package com.alexprut.algo.algorithms.sorting;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BubbleSortTest {

  @Test
  public void shouldSort() {
    int[] toSort = new int[] {5, 2, 1, 9, 0, 33, 3, 3, 0};
    int[] expected = new int[] {0, 0, 1, 2, 3, 3, 5, 9, 33};

    Assertions.assertArrayEquals(expected, BubbleSort.bubbleSort(toSort));
  }

  @Test
  public void shouldSortInverseSorted() {
    int[] toSort = new int[] {33, 9, 5, 3, 3, 2, 1, 0, 0};
    int[] expected = new int[] {0, 0, 1, 2, 3, 3, 5, 9, 33};

    Assertions.assertArrayEquals(expected, BubbleSort.bubbleSort(toSort));
  }

  @Test
  public void shouldSortSorted() {
    int[] expected = new int[] {0, 0, 1, 2, 3, 3, 5, 9, 33};

    Assertions.assertArrayEquals(expected, BubbleSort.bubbleSort(expected));
  }

  @Test
  public void shouldHandleEdgeCases() {
    Assertions.assertArrayEquals(new int[] {}, BubbleSort.bubbleSort(new int[] {}));
    Assertions.assertArrayEquals(new int[] {1}, BubbleSort.bubbleSort(new int[] {1}));
  }
}
