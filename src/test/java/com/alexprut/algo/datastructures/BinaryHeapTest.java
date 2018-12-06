package com.alexprut.algo.datastructures;

import org.junit.Assert;
import org.junit.Test;

public class BinaryHeapTest {

  @Test
  public void calculateLeft() {
    Assert.assertEquals(3, BinaryHeap.left(1));
    Assert.assertEquals(5, BinaryHeap.left(2));
    Assert.assertEquals(7, BinaryHeap.left(3));
  }

  @Test
  public void calculateRight() {
    Assert.assertEquals(4, BinaryHeap.right(1));
    Assert.assertEquals(6, BinaryHeap.right(2));
    Assert.assertEquals(8, BinaryHeap.right(3));
  }

  @Test
  public void calculateParent() {
    Assert.assertEquals(0, BinaryHeap.parent(1));
    Assert.assertEquals(0, BinaryHeap.parent(2));
    Assert.assertEquals(1, BinaryHeap.parent(3));
    Assert.assertEquals(4, BinaryHeap.parent(10));
  }
}
