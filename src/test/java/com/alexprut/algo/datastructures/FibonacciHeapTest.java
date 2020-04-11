package com.alexprut.algo.datastructures;

import com.alexprut.algo.datastructures.FibonacciHeap.Node;
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

  @Test
  public void shouldDecreaseKey() {
    FibonacciHeap<Integer> heap = new FibonacciHeap<>();
    Node<Integer> x = heap.insert(2);
    heap.decreaseKey(x, 1);
    Assert.assertEquals(new Integer(1), x.key);
  }

  @Test
  public void shouldDeleteNode() {
    FibonacciHeap<Integer> heap = new FibonacciHeap<>();
    Node<Integer> x = heap.insert(2);
    heap.delete(x);
    Assert.assertEquals(0, heap.size);
    Assert.assertEquals(null, heap.min);
  }
}
