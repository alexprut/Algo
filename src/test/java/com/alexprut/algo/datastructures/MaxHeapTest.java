package com.alexprut.algo.datastructures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MaxHeapTest {

  @Test
  public void shouldSort() {
    int[] toSort = new int[] {5, 2, 1, 9, 0, 33, 3, 3, 0};
    int[] expected = new int[] {33, 9, 5, 3, 3, 2, 1, 0, 0};

    MaxHeap heap = new MaxHeap(toSort);
    heap.heapsort();
    Assertions.assertArrayEquals(expected, heap.elements());
  }

  @Test
  public void shouldSortInverseSorted() {
    int[] toSort = new int[] {0, 0, 1, 2, 3, 3, 5, 9, 33};
    int[] expected = new int[] {33, 9, 5, 3, 3, 2, 1, 0, 0};

    MaxHeap heap = new MaxHeap(toSort);
    heap.heapsort();
    Assertions.assertArrayEquals(expected, heap.elements());
  }

  @Test
  public void shouldSortSorted() {
    int[] expected = new int[] {33, 9, 5, 3, 3, 2, 1, 0, 0};

    MaxHeap heap = new MaxHeap(expected);
    heap.heapsort();
    Assertions.assertArrayEquals(expected, heap.elements());
  }

  @Test
  public void shouldInsertExtractGetMaxIncreaseKey() {
    int[] expected = new int[] {33, 0, 0};

    MaxHeap heap = new MaxHeap();
    heap.insert(33);
    heap.insert(0);
    heap.insert(0);
    heap.heapsort();

    Assertions.assertArrayEquals(expected, heap.elements());
    Assertions.assertEquals(33, heap.max());
    Assertions.assertEquals(3, heap.size());

    try {
      Assertions.assertEquals(33, heap.extractMax());
      heap.increaseKey(1, 2);
    } catch (Exception e) {
    }

    Assertions.assertEquals(2, heap.size());
    Assertions.assertEquals(2, heap.max());
  }

  @Test
  public void shouldHandleEdgeCases() {
    MaxHeap heap = new MaxHeap(new int[] {});
    heap.heapsort();
    Assertions.assertArrayEquals(new int[] {}, heap.elements());

    heap = new MaxHeap(new int[] {1});
    heap.heapsort();
    Assertions.assertArrayEquals(new int[] {1}, heap.elements());
  }
}
