package com.alexprut.algo.datastructures;

import static junit.framework.TestCase.fail;

import org.junit.Assert;
import org.junit.Test;

public class DoubleLinkedListTest {

  @Test
  public void shouldInsertAndRemoveFront() {
    DoubleLinkedList<Integer> linkedList = new DoubleLinkedList<>();
    Assert.assertNull(linkedList.head());
    Assert.assertEquals(0, linkedList.size());
    linkedList.insertFront(5);
    Assert.assertEquals(5, (int) linkedList.head().getValue());
    Assert.assertEquals(1, linkedList.size());
    try {
      Assert.assertEquals(5, (int) linkedList.removeFront());
    } catch (Exception e) {
      fail("Should not thrown an exception");
    }
    Assert.assertEquals(0, linkedList.size());
    Assert.assertNull(linkedList.head());
    linkedList.insertBack(1);
    linkedList.insertBack(2);
    Assert.assertEquals(2, linkedList.size());
    Assert.assertEquals(2, (int) linkedList.tail().getValue());
    try {
      Assert.assertEquals(2, (int) linkedList.removeBack());
    } catch (Exception e) {
      fail("Should not thrown an exception");
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
      Assert.assertEquals(5, (int) list.removeBack());
    } catch (Exception e) {
      fail("Should not thrown an exception");
    }

    try {
      Assert.assertEquals(1, (int) list.removeFront());
    } catch (Exception e) {
      fail("Should not thrown an exception");
    }

    Assert.assertEquals(3, list.size());
    Assert.assertEquals(2, (int) list.head().getValue());
    Assert.assertEquals(3, (int) list.head().getNext().getValue());
    Assert.assertNull(list.head().getPrev());
  }
}
