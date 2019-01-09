package com.alexprut.algo.datastructures;

import org.junit.Assert;
import org.junit.Test;

public class BinarySearchTreeTest {

  @Test
  public void shouldInsertAndRetrieveAndSearch() {
    BinarySearchTree bst = new BinarySearchTree();
    Assert.assertEquals(0, bst.size());

    bst.insert(1);
    Assert.assertEquals(1, bst.size());
    Assert.assertTrue(bst.contains(1));
    Assert.assertFalse(bst.contains(2));
    Assert.assertEquals(1, bst.search(1).value());

    bst.insert(1);
    Assert.assertEquals(2, bst.size());
    Assert.assertTrue(bst.contains(1));
    Assert.assertEquals(1, bst.search(1).value());

    bst.insert(2);
    Assert.assertEquals(3, bst.size());
    Assert.assertTrue(bst.contains(2));
    Assert.assertEquals(2, bst.search(2).value());

    Assert.assertEquals(2, bst.maximum().value());
    Assert.assertEquals(1, bst.minimum().value());
  }

  @Test
  public void shouldInsertAndRetrieveAndSearchAndDelete() {
    BinarySearchTree bst = new BinarySearchTree();
    for (int i = 0; i < 1000; i++) {
      bst.insert(i);
    }

    for (int i = 0; i < 1000; i++) {
      Assert.assertEquals(i, bst.search(i).value());
    }

    for (int i = 0; i < 1000; i++) {
      bst.delete(bst.search(i));
      Assert.assertEquals(null, bst.search(i));
    }

    Assert.assertEquals(0, bst.size());
  }
}
