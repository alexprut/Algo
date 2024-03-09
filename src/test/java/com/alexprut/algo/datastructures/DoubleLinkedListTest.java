package com.alexprut.algo.datastructures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DoubleLinkedListTest {

  @Test
  public void shouldInsertAndRemoveFront() {
    DoubleLinkedList<Integer> linkedList = new DoubleLinkedList<>();
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
    linkedList.insertBack(1);
    linkedList.insertBack(2);
    Assertions.assertEquals(2, linkedList.size());
    Assertions.assertEquals(2, (int) linkedList.tail().getValue());
    try {
      Assertions.assertEquals(2, (int) linkedList.removeBack());
    } catch (Exception e) {
      Assertions.fail("Should not thrown an exception");
    }
  }

  @Test
  public void shouldGetNextPrevAndHead() {
    DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
    list.insertFront(1);
    list.insertBack(2);
    list.insertBack(3);
    list.insertBack(4);
    list.insertBack(5);

    try {
      Assertions.assertEquals(5, (int) list.removeBack());
    } catch (Exception e) {
      Assertions.fail("Should not thrown an exception");
    }

    try {
      Assertions.assertEquals(1, (int) list.removeFront());
    } catch (Exception e) {
      Assertions.fail("Should not thrown an exception");
    }

    Assertions.assertEquals(3, list.size());
    Assertions.assertEquals(2, (int) list.head().getValue());
    Assertions.assertEquals(3, (int) list.head().getNext().getValue());
    Assertions.assertNull(list.head().getPrev());
  }

  @Test
  public void shouldInsertAndSearch() {
    DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
    list.insertFront(1);
    list.insertBack(2);
    Assertions.assertNull(list.head().getPrev());
    Assertions.assertNull(list.tail().getNext());
    list.insertBack(3);

    Assertions.assertEquals(3, list.size());
    Assertions.assertTrue(list.search(1));
    Assertions.assertTrue(list.search(2));
    Assertions.assertTrue(list.search(3));
    Assertions.assertFalse(list.search(4));
    Assertions.assertFalse(list.search(5));
  }
}
