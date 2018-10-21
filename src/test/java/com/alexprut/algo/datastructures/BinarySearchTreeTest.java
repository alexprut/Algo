package com.alexprut.algo.datastructures;

import org.junit.Assert;
import org.junit.Test;

public class BinarySearchTreeTest {

  @Test
  public void shouldInsert() {
    BinarySearchTree bst = new BinarySearchTree();
    Assert.assertEquals(0, bst.size());

    bst.insert(1);
    Assert.assertEquals(1, bst.size());

    bst.insert(1);
    Assert.assertEquals(2, bst.size());

    bst.insert(2);
    Assert.assertEquals(3, bst.size());
  }
}
