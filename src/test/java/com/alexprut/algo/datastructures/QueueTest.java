package com.alexprut.algo.datastructures;

import org.junit.Assert;
import org.junit.Test;

public class QueueTest {

  @Test
  public void shouldSupportBasicOperations() {
    Queue<Integer> queue = new Queue<>();
    Assert.assertEquals(0, queue.size());
    Assert.assertTrue(queue.empty());

    queue.enqueue(5);
    Assert.assertEquals(1, queue.size());

    queue.enqueue(4);
    int value;
    try {
      value = queue.dequeue();
    } catch (Exception e) {
      value = -1;
    }
    Assert.assertEquals(5, value);
    Assert.assertEquals(1, queue.size());
  }
}
