package com.alexprut.algo.datastructures;

import com.alexprut.algo.datastructures.AVLTree.Node;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AVLTreeTest {

  @Test
  public void shouldInsertAndSearch() {
    AVLTree<Integer> tree = new AVLTree<>();
    for (int i = 0; i < 100; i++) {
      tree.insert(i);
      Assertions.assertTrue(tree.search(i));
    }

    Assertions.assertNotNull(tree.root());
    Assertions.assertNull(tree.root().parent());
    Assertions.assertEquals(Integer.valueOf(0), tree.minimum().value());
    Assertions.assertEquals(Integer.valueOf(99), tree.maximum().value());
  }

  @Test
  public void shouldInsertAndDeleteAndSearch() {
    AVLTree<Integer> tree = new AVLTree<>();
    for (int i = 0; i < 100; i++) {
      tree.insert(i);
    }

    for (int i = 0; i < 100; i++) {
      tree.delete(i);
      Assertions.assertFalse(tree.search(i));
    }

    Assertions.assertEquals(0, tree.size());
  }

  @Test
  public void shouldRotateLeft() {
    /*
         x                           y
        / \     leftRotate(x)       / \
       w   y    ------------>      x   z
          / \                     / \
         u   z                   w   u
    */
    AVLTree<Integer> tree = new AVLTree<>();
    Node<Integer> x = new Node<>(1);
    Node<Integer> y = new Node<>(2);
    Node<Integer> z = new Node<>(3);
    Node<Integer> w = new Node<>(4);
    Node<Integer> u = new Node<>(5);
    tree.root = x;

    x.setLeft(w);
    x.setRight(y);
    y.setRight(z);
    y.setLeft(u);
    tree.leftRotation(x);

    Assertions.assertEquals(y, tree.root);
    Assertions.assertEquals(x, y.left());
    Assertions.assertEquals(z, y.right());
    Assertions.assertEquals(w, x.left());
    Assertions.assertEquals(u, x.right());
    Assertions.assertNull(z.left());
    Assertions.assertNull(z.right());
    Assertions.assertNull(u.left());
    Assertions.assertNull(u.right());
    Assertions.assertNull(w.left());
    Assertions.assertNull(w.right());
  }

  @Test
  public void shouldRotateRight() {
    /*
         x                            y
        / \     rightRotate(y)       / \
       w   y    <-------------      x   z
          / \                      / \
         u   z                    w   u
    */
    AVLTree<Integer> tree = new AVLTree<>();
    Node<Integer> x = new Node<>(1);
    Node<Integer> y = new Node<>(2);
    Node<Integer> z = new Node<>(3);
    Node<Integer> w = new Node<>(4);
    Node<Integer> u = new Node<>(5);
    tree.root = y;

    y.setLeft(x);
    y.setRight(z);
    x.setLeft(w);
    x.setRight(u);
    tree.rightRotation(y);

    Assertions.assertEquals(x, tree.root);
    Assertions.assertEquals(w, x.left());
    Assertions.assertEquals(y, x.right());
    Assertions.assertEquals(u, y.left());
    Assertions.assertEquals(z, y.right());
    Assertions.assertNull(w.left());
    Assertions.assertNull(w.right());
    Assertions.assertNull(u.left());
    Assertions.assertNull(u.right());
    Assertions.assertNull(z.left());
    Assertions.assertNull(z.right());
  }

  @Test
  public void shouldDoMultipleOperations() {
    /*
           34
         /    \
       13      68
      /  \    /  \
     8   27  41  71
    */

    AVLTree<Integer> tree = new AVLTree<>();
    tree.insert(71);
    tree.insert(34);
    tree.insert(8);
    tree.insert(41);
    tree.insert(68);
    tree.insert(13);
    tree.insert(27);

    Assertions.assertEquals(7, tree.size());
    Assertions.assertTrue(tree.search(71));
    Assertions.assertTrue(tree.search(34));
    Assertions.assertTrue(tree.search(8));
    Assertions.assertTrue(tree.search(41));
    Assertions.assertTrue(tree.search(68));
    Assertions.assertTrue(tree.search(13));
    Assertions.assertTrue(tree.search(27));

    Assertions.assertNull(tree.successor(tree.search(tree.root, 71)));
    Assertions.assertEquals(41, (int) tree.successor(tree.search(tree.root, 34)).value());
    Assertions.assertEquals(13, (int) tree.successor(tree.search(tree.root, 8)).value());
    Assertions.assertEquals(68, (int) tree.successor(tree.search(tree.root, 41)).value());
    Assertions.assertEquals(71, (int) tree.successor(tree.search(tree.root, 68)).value());
    Assertions.assertEquals(27, (int) tree.successor(tree.search(tree.root, 13)).value());
    Assertions.assertEquals(34, (int) tree.successor(tree.search(tree.root, 27)).value());

    tree.delete(34);
    tree.delete(13);
    tree.delete(41);
    tree.delete(71);
    tree.delete(27);
    tree.delete(8);
    tree.delete(68);

    Assertions.assertEquals(0, tree.size());
  }
}
