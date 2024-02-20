package com.alexprut.algo.datastructures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MinHeapTest {

  @Test
  public void shouldSort() {
    int[] toSort = new int[] {5, 2, 1, 9, 0, 33, 3, 3, 0};
    int[] expected = new int[] {0, 0, 1, 2, 3, 3, 5, 9, 33};

    BinaryHeap heap = new MinHeap(toSort);
    heap.heapsort();
    Assertions.assertArrayEquals(expected, heap.elements());
  }

  @Test
  public void shouldSortInverseSorted() {
    int[] toSort = new int[] {33, 9, 5, 3, 3, 2, 1, 0, 0};
    int[] expected = new int[] {0, 0, 1, 2, 3, 3, 5, 9, 33};

    BinaryHeap heap = new MinHeap(toSort);
    heap.heapsort();
    Assertions.assertArrayEquals(expected, heap.elements());
  }

  @Test
  public void shouldSortSorted() {
    int[] expected = new int[] {0, 0, 1, 2, 3, 3, 5, 9, 33};

    BinaryHeap heap = new MinHeap(expected);
    heap.heapsort();
    Assertions.assertArrayEquals(expected, heap.elements());
  }

  @Test
  public void shouldInsertExtractGetMinDecreaseKey() {
    int[] expected = new int[] {0, 0, 33};

    MinHeap heap = new MinHeap();
    heap.insert(33);
    heap.insert(0);
    heap.insert(0);
    heap.heapsort();

    Assertions.assertArrayEquals(expected, heap.elements());
    Assertions.assertEquals(0, heap.min());
    Assertions.assertEquals(3, heap.size());

    try {
      Assertions.assertEquals(0, heap.extractMin());
    } catch (Exception e) {
    }

    Assertions.assertEquals(2, heap.size());
    Assertions.assertEquals(0, heap.min());
  }

  @Test
  public void shouldHandleEdgeCases() {
    BinaryHeap heap = new MinHeap(new int[] {});
    heap.heapsort();
    Assertions.assertArrayEquals(new int[] {}, heap.elements());

    heap = new MinHeap(new int[] {1});
    heap.heapsort();
    Assertions.assertArrayEquals(new int[] {1}, heap.elements());
  }
}
