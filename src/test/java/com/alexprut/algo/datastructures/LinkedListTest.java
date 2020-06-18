package com.alexprut.algo.datastructures;

import static junit.framework.TestCase.fail;

import org.junit.Assert;
import org.junit.Test;

public class LinkedListTest {

  @Test
  public void shouldInsertAndRemoveFront() {
    LinkedList<Integer> linkedList = new LinkedList<>();
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
  }
}
