package com.alexprut.algo.datastructures;

import org.junit.Assert;
import org.junit.Test;

public class MinHeapTest {

  @Test
  public void shouldSort() {
    int[] toSort = new int[] {5, 2, 1, 9, 0, 33, 3, 3, 0};
    int[] expected = new int[] {0, 0, 1, 2, 3, 3, 5, 9, 33};

    BinaryHeap heap = new MinHeap(toSort);
    heap.heapsort();
    Assert.assertArrayEquals(expected, heap.elements());
  }

  @Test
  public void shouldSortInverseSorted() {
    int[] toSort = new int[] {33, 9, 5, 3, 3, 2, 1, 0, 0};
    int[] expected = new int[] {0, 0, 1, 2, 3, 3, 5, 9, 33};

    BinaryHeap heap = new MinHeap(toSort);
    heap.heapsort();
    Assert.assertArrayEquals(expected, heap.elements());
  }

  @Test
  public void shouldSortSorted() {
    int[] expected = new int[] {0, 0, 1, 2, 3, 3, 5, 9, 33};

    BinaryHeap heap = new MinHeap(expected);
    heap.heapsort();
    Assert.assertArrayEquals(expected, heap.elements());
  }

  @Test
  public void shouldHandleEdgeCases() {
    BinaryHeap heap = new MinHeap(new int[] {});
    heap.heapsort();
    Assert.assertArrayEquals(new int[] {}, heap.elements());

    heap = new MinHeap(new int[] {1});
    heap.heapsort();
    Assert.assertArrayEquals(new int[] {1}, heap.elements());
  }
}
