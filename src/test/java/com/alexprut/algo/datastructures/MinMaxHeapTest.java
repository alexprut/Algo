package com.alexprut.algo.datastructures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MinMaxHeapTest {

  @Test
  public void shouldDoBasicOperations() {
    /*
                  8
             /          \
           71            41
        /     \        /    \
       31     10      11    16
      / \    /  \    /
    46  51  31  21  13
    */
    int[] elements = new int[] {8, 71, 41, 31, 10, 11, 16, 46, 51, 31, 21, 13};
    MinMaxHeap heap = new MinMaxHeap(elements);

    Assertions.assertArrayEquals(elements, heap.elements());
    Assertions.assertEquals(elements.length, heap.size());
    Assertions.assertEquals(8, heap.min());
    Assertions.assertEquals(71, heap.max());
  }

  @Test
  public void shouldInsertSearchAndRemove() {
    MinMaxHeap heap = new MinMaxHeap();

    heap.insert(13);
    Assertions.assertEquals(1, heap.size());
    Assertions.assertEquals(13, heap.min());
    Assertions.assertEquals(13, heap.max());

    heap.insert(21);
    Assertions.assertEquals(2, heap.size());
    Assertions.assertEquals(13, heap.min());
    Assertions.assertEquals(21, heap.max());

    heap.insert(31);
    Assertions.assertEquals(3, heap.size());
    Assertions.assertEquals(13, heap.min());
    Assertions.assertEquals(31, heap.max());

    heap.insert(51);
    Assertions.assertEquals(4, heap.size());
    Assertions.assertEquals(13, heap.min());
    Assertions.assertEquals(51, heap.max());

    heap.insert(51);
    Assertions.assertEquals(5, heap.size());
    Assertions.assertEquals(13, heap.min());
    Assertions.assertEquals(51, heap.max());

    heap.insert(8);
    Assertions.assertEquals(6, heap.size());
    Assertions.assertEquals(8, heap.min());
    Assertions.assertEquals(51, heap.max());

    int min = heap.extractMin();
    Assertions.assertEquals(5, heap.size());
    Assertions.assertEquals(8, min);
    Assertions.assertEquals(13, heap.min());
    Assertions.assertEquals(51, heap.max());

    int max = heap.extractMax();
    Assertions.assertEquals(4, heap.size());
    Assertions.assertEquals(51, max);
    Assertions.assertEquals(13, heap.min());
    Assertions.assertEquals(51, heap.max());

    heap.extractMin();
    heap.extractMax();
    heap.extractMin();
    heap.extractMax();
    Assertions.assertEquals(0, heap.size());

    heap.insert(13);
    Assertions.assertEquals(1, heap.size());
    Assertions.assertEquals(13, heap.min());
    Assertions.assertEquals(13, heap.max());
  }
}
