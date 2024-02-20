package com.alexprut.algo.datastructures;

import com.alexprut.algo.datastructures.FibonacciHeap.Node;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FibonacciHeapTest {

  @Test
  public void shouldInsertAndGetMinimum() {
    FibonacciHeap<Integer> heap = new FibonacciHeap<>();
    heap.insert(1);
    Assertions.assertEquals(1, heap.size);
    Assertions.assertEquals(Integer.valueOf(1), heap.minimum());
  }

  @Test
  public void shouldInsertAndExtractMinimum() {
    FibonacciHeap<Integer> heap = new FibonacciHeap<>();
    heap.insert(1);
    Assertions.assertEquals(Integer.valueOf(1), heap.extractMin().key);
    Assertions.assertEquals(0, heap.size());
  }

  @Test
  public void shouldDecreaseKey() {
    FibonacciHeap<Integer> heap = new FibonacciHeap<>();
    Node<Integer> x = heap.insert(2);
    heap.decreaseKey(x, 1);
    Assertions.assertEquals(Integer.valueOf(1), x.key);
  }

  @Test
  public void shouldDeleteNode() {
    FibonacciHeap<Integer> heap = new FibonacciHeap<>();
    Node<Integer> x = heap.insert(2);
    heap.delete(x);
    Assertions.assertEquals(0, heap.size);
    Assertions.assertNull(heap.min);
  }

  @Test
  public void shouldDoMultipleOperations() {
    List<Node<Integer>> nodes = new ArrayList<>();
    FibonacciHeap<Integer> heap = new FibonacciHeap<>();
    for (int i = 0; i < 6; i++) {
      nodes.add(heap.insert(i));
    }
    for (Node<Integer> node : nodes) {
      heap.decreaseKey(node, node.key - 1);
    }
    Assertions.assertEquals(Integer.valueOf(-1), heap.minimum());

    heap.delete(nodes.get(0));
    heap.delete(nodes.get(1));
    heap.delete(nodes.get(4));
    heap.delete(nodes.get(5));

    Assertions.assertEquals(Integer.valueOf(1), heap.minimum());
    Assertions.assertEquals(2, heap.size);
  }

  @Test
  public void shouldDoMultipleRandomOperations() {
    List<Node<Integer>> nodes = new ArrayList<>();
    FibonacciHeap<Integer> heap = new FibonacciHeap<>();

    for (int i = 0; i < 100; i++) {
      nodes.add(heap.insert((int) (i * Math.random())));
    }
    for (int i = 0; i < nodes.size() * Math.random(); i++) {
      heap.decreaseKey(nodes.get(i), nodes.get(i).key - 1);
    }
    for (int i = 0; i < nodes.size() * Math.random(); i++) {
      heap.delete(nodes.get(i));
    }
  }
}
