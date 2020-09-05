package com.alexprut.algo.datastructures;

import org.junit.Assert;
import org.junit.Test;

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

    Assert.assertEquals(elements.length, heap.size());
    Assert.assertEquals(8, heap.min());
    Assert.assertEquals(71, heap.max());
  }

  @Test
  public void shouldInsertSearchAndRemove() {
    MinMaxHeap heap = new MinMaxHeap();

    heap.insert(13);
    Assert.assertEquals(1, heap.size());
    Assert.assertEquals(13, heap.min());
    Assert.assertEquals(13, heap.max());

    heap.insert(21);
    Assert.assertEquals(2, heap.size());
    Assert.assertEquals(13, heap.min());
    Assert.assertEquals(21, heap.max());

    heap.insert(31);
    Assert.assertEquals(3, heap.size());
    Assert.assertEquals(13, heap.min());
    Assert.assertEquals(31, heap.max());

    heap.insert(51);
    Assert.assertEquals(4, heap.size());
    Assert.assertEquals(13, heap.min());
    Assert.assertEquals(51, heap.max());

    heap.insert(51);
    Assert.assertEquals(5, heap.size());
    Assert.assertEquals(13, heap.min());
    Assert.assertEquals(51, heap.max());

    heap.insert(8);
    Assert.assertEquals(6, heap.size());
    Assert.assertEquals(8, heap.min());
    Assert.assertEquals(51, heap.max());

    int min = heap.extractMin();
    Assert.assertEquals(5, heap.size());
    Assert.assertEquals(8, min);
    Assert.assertEquals(13, heap.min());
    Assert.assertEquals(51, heap.max());

    int max = heap.extractMax();
    Assert.assertEquals(4, heap.size());
    Assert.assertEquals(51, max);
    Assert.assertEquals(13, heap.min());
    Assert.assertEquals(51, heap.max());

    heap.extractMin();
    heap.extractMax();
    heap.extractMin();
    heap.extractMax();
    Assert.assertEquals(0, heap.size());

    heap.insert(13);
    Assert.assertEquals(1, heap.size());
    Assert.assertEquals(13, heap.min());
    Assert.assertEquals(13, heap.max());
  }
}
