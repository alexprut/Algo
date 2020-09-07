package com.alexprut.algo.datastructures;

import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Test;

public class BinarySearchTreeTest {

  @Test
  public void shouldInsertAndRetrieveAndSearch() {
    BinarySearchTree<Integer> bst = new BinarySearchTree<>();
    Assert.assertEquals(0, bst.size());

    bst.insert(1);
    Assert.assertEquals(1, bst.size());
    Assert.assertTrue(bst.contains(1));
    Assert.assertFalse(bst.contains(2));
    Assert.assertEquals(new Integer(1), bst.search(1).value());

    bst.insert(1);
    Assert.assertEquals(2, bst.size());
    Assert.assertTrue(bst.contains(1));
    Assert.assertEquals(new Integer(1), bst.search(1).value());

    bst.insert(2);
    Assert.assertEquals(3, bst.size());
    Assert.assertTrue(bst.contains(2));
    Assert.assertEquals(new Integer(2), bst.search(2).value());

    Assert.assertEquals(new Integer(2), bst.maximum().value());
    Assert.assertEquals(new Integer(1), bst.minimum().value());
  }

  @Test
  public void shouldInsertAndRetrieveAndSearchAndDelete() {
    BinarySearchTree<Integer> bst = new BinarySearchTree<>();
    for (int i = 0; i < 1000; i++) {
      bst.insert(i);
    }

    for (int i = 0; i < 1000; i++) {
      Assert.assertEquals(new Integer(i), bst.search(i).value());
    }

    for (int i = 0; i < 1000; i++) {
      bst.delete(bst.search(i));
      Assert.assertNull(bst.search(i));
    }

    Assert.assertEquals(0, bst.size());
  }

  @Test
  public void shouldPreOrderVisit() {
    /*
             4
          /    \
         2      6
        / \    /
       1   3  5
    */
    BinarySearchTree<Integer> bst = new BinarySearchTree<>();
    bst.insert(4);
    bst.insert(2);
    bst.insert(1);
    bst.insert(3);
    bst.insert(6);
    bst.insert(5);

    ArrayList<Integer> expected = new ArrayList<>();
    expected.add(4);
    expected.add(2);
    expected.add(1);
    expected.add(3);
    expected.add(6);
    expected.add(5);

    Assert.assertEquals(expected, bst.preOrderVisit());
  }

  @Test
  public void shouldInOrderVisit() {
    /*
             4
          /    \
         2      6
        / \    / \
       1   3  5   7
    */
    BinarySearchTree<Integer> bst = new BinarySearchTree<>();
    bst.insert(4);
    bst.insert(2);
    bst.insert(1);
    bst.insert(3);
    bst.insert(6);
    bst.insert(5);
    bst.insert(7);

    ArrayList<Integer> expected = new ArrayList<>();
    expected.add(1);
    expected.add(2);
    expected.add(3);
    expected.add(4);
    expected.add(5);
    expected.add(6);
    expected.add(7);

    Assert.assertEquals(expected, bst.inOrderVisit());
  }

  @Test
  public void shouldPostOrderVisit() {
    /*
             4
          /    \
         2      6
        / \    / \
       1   3  5   7
    */
    BinarySearchTree<Integer> bst = new BinarySearchTree<>();
    bst.insert(4);
    bst.insert(2);
    bst.insert(1);
    bst.insert(3);
    bst.insert(6);
    bst.insert(5);
    bst.insert(7);

    ArrayList<Integer> expected = new ArrayList<>();
    expected.add(1);
    expected.add(3);
    expected.add(2);
    expected.add(5);
    expected.add(7);
    expected.add(6);
    expected.add(4);

    Assert.assertEquals(expected, bst.postOrderVisit());
  }

  @Test
  public void shouldGetSuccessorAndDelete() {
    /*
             4
          /    \
         2      6
        / \    / \
       1   3  5   7
    */
    BinarySearchTree<Integer> bst = new BinarySearchTree<>();
    bst.insert(4);
    bst.insert(2);
    bst.insert(1);
    bst.insert(3);
    bst.insert(6);
    bst.insert(5);
    bst.insert(7);

    Assert.assertEquals(5, (int) bst.successor(bst.search(4)).value());
    Assert.assertEquals(2, (int) bst.successor(bst.search(1)).value());
    Assert.assertEquals(7, (int) bst.successor(bst.search(6)).value());
    Assert.assertEquals(null, bst.successor(bst.search(7)));

    bst.delete(bst.search(6));
    bst.delete(bst.search(3));
    bst.delete(bst.search(2));
    bst.delete(bst.search(4));
    bst.delete(bst.search(1));
    bst.delete(bst.search(5));
    bst.delete(bst.search(7));

    Assert.assertEquals(0, bst.size());
  }
}
