package com.alexprut.algo.datastructures;

import static junit.framework.TestCase.fail;

import org.junit.Assert;
import org.junit.Test;

public class DoubleLinkedListTest {

  @Test
  public void shouldInsertAndRemoveFront() {
    DoubleLinkedList<Integer> linkedList = new DoubleLinkedList<>();
    Assert.assertEquals(null, linkedList.head());
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
    Assert.assertEquals(null, linkedList.head());
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
}
