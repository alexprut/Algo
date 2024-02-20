package com.alexprut.algo.datastructures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LinkedListTest {

  @Test
  public void shouldInsertAndRemoveFront() {
    LinkedList<Integer> linkedList = new LinkedList<>();
    Assertions.assertNull(linkedList.head());
    Assertions.assertEquals(0, linkedList.size());
    linkedList.insertFront(5);
    Assertions.assertEquals(5, (int) linkedList.head().getValue());
    Assertions.assertEquals(1, linkedList.size());
    try {
      Assertions.assertEquals(5, (int) linkedList.removeFront());
    } catch (Exception e) {
      Assertions.fail("Should not thrown an exception");
    }
    Assertions.assertEquals(0, linkedList.size());
    Assertions.assertNull(linkedList.head());
  }

  @Test
  public void shouldGetNext() {
    LinkedList<Integer> list = new LinkedList<>();
    list.insertFront(1);
    list.insertFront(2);

    Assertions.assertEquals(Integer.valueOf(2), list.head().getValue());
    Assertions.assertEquals(Integer.valueOf(1), list.head().getNext().getValue());
  }
}
