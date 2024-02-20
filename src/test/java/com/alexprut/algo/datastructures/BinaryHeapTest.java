package com.alexprut.algo.datastructures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BinaryHeapTest {

  @Test
  public void calculateLeft() {
    Assertions.assertEquals(3, BinaryHeap.left(1));
    Assertions.assertEquals(5, BinaryHeap.left(2));
    Assertions.assertEquals(7, BinaryHeap.left(3));
  }

  @Test
  public void calculateRight() {
    Assertions.assertEquals(4, BinaryHeap.right(1));
    Assertions.assertEquals(6, BinaryHeap.right(2));
    Assertions.assertEquals(8, BinaryHeap.right(3));
  }

  @Test
  public void calculateParent() {
    Assertions.assertEquals(0, BinaryHeap.parent(1));
    Assertions.assertEquals(0, BinaryHeap.parent(2));
    Assertions.assertEquals(1, BinaryHeap.parent(3));
    Assertions.assertEquals(4, BinaryHeap.parent(10));
  }
}
