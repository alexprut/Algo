package com.alexprut.algo.datastructures;

import org.junit.Assert;
import org.junit.Test;

public class FibonacciHeapTest {

  @Test
  public void shouldInsertAndGetMinimum() {
    FibonacciHeap<Integer> heap = new FibonacciHeap<>();
    heap.insert(1);
    Assert.assertEquals(1, heap.size);
    Assert.assertEquals(new Integer(1), heap.minimum());
  }

  @Test
  public void shouldInsertAndExtractMinimum() {
    FibonacciHeap<Integer> heap = new FibonacciHeap<>();
    heap.insert(1);
    Assert.assertEquals(new Integer(1), heap.extractMin().key);
    Assert.assertEquals(0, heap.size);
  }
}
