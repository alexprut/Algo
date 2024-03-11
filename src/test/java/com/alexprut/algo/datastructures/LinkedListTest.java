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

  @Test
  public void shouldInsertAndSearch() {
    LinkedList<Integer> list = new LinkedList<>();
    list.insertFront(1);
    list.insertFront(2);
    list.insertFront(3);

    Assertions.assertEquals(3, list.size());
    Assertions.assertTrue(list.search(1));
    Assertions.assertTrue(list.search(2));
    Assertions.assertTrue(list.search(3));
    Assertions.assertFalse(list.search(4));
    Assertions.assertFalse(list.search(5));
  }

  @Test
  public void shouldInsertAndRemove() {
    DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
    list.insertFront(1);
    list.insertBack(2);
    list.insertBack(3);
    list.insertBack(4);

    Assertions.assertEquals(4, list.size());
    Assertions.assertFalse(list.remove(5));
    Assertions.assertTrue(list.remove(1));
    Assertions.assertEquals(3, list.size());
    Assertions.assertTrue(list.remove(4));
    Assertions.assertEquals(2, list.size());
    Assertions.assertTrue(list.remove(2));
    Assertions.assertTrue(list.remove(3));
    Assertions.assertEquals(0, list.size());
  }
}
