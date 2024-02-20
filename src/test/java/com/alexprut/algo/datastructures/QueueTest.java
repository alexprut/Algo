package com.alexprut.algo.datastructures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class QueueTest {

  @Test
  public void shouldSupportBasicOperations() {
    Queue<Integer> queue = new Queue<>();
    Assertions.assertEquals(0, queue.size());
    Assertions.assertTrue(queue.empty());

    queue.enqueue(5);
    Assertions.assertEquals(1, queue.size());

    queue.enqueue(4);
    int value;
    try {
      value = queue.dequeue();
    } catch (Exception e) {
      value = -1;
    }
    Assertions.assertEquals(5, value);
    Assertions.assertEquals(1, queue.size());
  }
}
