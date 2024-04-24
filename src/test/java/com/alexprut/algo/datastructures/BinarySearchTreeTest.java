package com.alexprut.algo.datastructures;

import java.util.ArrayList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BinarySearchTreeTest {

  @Test
  public void shouldInsertAndRetrieveAndSearch() {
    BinarySearchTree<Integer> bst = new BinarySearchTree<>();
    Assertions.assertEquals(0, bst.size());

    bst.insert(1);
    Assertions.assertEquals(1, bst.size());
    Assertions.assertTrue(bst.contains(1));
    Assertions.assertFalse(bst.contains(2));
    Assertions.assertEquals(Integer.valueOf(1), bst.search(1).value());

    bst.insert(1);
    Assertions.assertEquals(2, bst.size());
    Assertions.assertTrue(bst.contains(1));
    Assertions.assertEquals(Integer.valueOf(1), bst.search(1).value());

    bst.insert(2);
    Assertions.assertEquals(3, bst.size());
    Assertions.assertTrue(bst.contains(2));
    Assertions.assertEquals(Integer.valueOf(2), bst.search(2).value());

    Assertions.assertEquals(Integer.valueOf(2), bst.maximum().value());
    Assertions.assertEquals(Integer.valueOf(1), bst.minimum().value());
  }

  @Test
  public void shouldInsertAndRetrieveAndSearchAndDelete() {
    BinarySearchTree<Integer> bst = new BinarySearchTree<>();
    for (int i = 0; i < 1000; i++) {
      bst.insert(i);
    }

    for (int i = 0; i < 1000; i++) {
      Assertions.assertEquals(Integer.valueOf(i), bst.search(i).value());
    }

    for (int i = 0; i < 1000; i++) {
      bst.delete(bst.search(i));
      Assertions.assertNull(bst.search(i));
    }

    Assertions.assertEquals(0, bst.size());
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

    Assertions.assertEquals(expected, bst.preOrderVisit());
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

    Assertions.assertEquals(expected, bst.inOrderVisit());
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

    Assertions.assertEquals(expected, bst.postOrderVisit());
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

    Assertions.assertEquals(5, (int) bst.successor(bst.search(4)).value());
    Assertions.assertEquals(2, (int) bst.successor(bst.search(1)).value());
    Assertions.assertEquals(7, (int) bst.successor(bst.search(6)).value());
    Assertions.assertNull(bst.successor(bst.search(7)));

    bst.delete(bst.search(6));
    bst.delete(bst.search(3));
    bst.delete(bst.search(2));
    bst.delete(bst.search(4));
    bst.delete(bst.search(1));
    bst.delete(bst.search(5));
    bst.delete(bst.search(7));

    Assertions.assertEquals(0, bst.size());
  }

  @Test
  public void shouldGetPredecessorAndDelete() {
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

    Assertions.assertNull(bst.predecessor(bst.search(1)));
    Assertions.assertEquals(1, (int) bst.predecessor(bst.search(2)).value());
    Assertions.assertEquals(2, (int) bst.predecessor(bst.search(3)).value());
    Assertions.assertEquals(3, (int) bst.predecessor(bst.search(4)).value());
    Assertions.assertEquals(4, (int) bst.predecessor(bst.search(5)).value());
    Assertions.assertEquals(5, (int) bst.predecessor(bst.search(6)).value());
    Assertions.assertEquals(6, (int) bst.predecessor(bst.search(7)).value());

    bst.delete(bst.search(6));
    bst.delete(bst.search(3));
    bst.delete(bst.search(2));
    bst.delete(bst.search(4));
    Assertions.assertEquals(5, (int) bst.predecessor(bst.search(7)).value());
  }
}
