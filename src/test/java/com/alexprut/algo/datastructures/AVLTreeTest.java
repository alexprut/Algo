package com.alexprut.algo.datastructures;

import com.alexprut.algo.datastructures.AVLTree.Node;
import org.junit.Assert;
import org.junit.Test;

public class AVLTreeTest {

  @Test
  public void shouldInsertAndSearch() {
    AVLTree<Integer> tree = new AVLTree<>();
    for (int i = 0; i < 100; i++) {
      tree.insert(i);
      Assert.assertTrue(tree.search(i));
    }

    Assert.assertNotNull(tree.root());
    Assert.assertNull(tree.root().parent());
    Assert.assertEquals(new Integer(99), tree.maximum().value());
  }

  @Test
  public void shouldInsertAndDeleteAndSearch() {
    AVLTree<Integer> tree = new AVLTree<>();
    for (int i = 0; i < 100; i++) {
      tree.insert(i);
    }

    for (int i = 0; i < 100; i++) {
      tree.delete(i);
      Assert.assertFalse(tree.search(i));
    }

    Assert.assertEquals(0, tree.size());
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

    Assert.assertEquals(y, tree.root);
    Assert.assertEquals(x, y.left());
    Assert.assertEquals(z, y.right());
    Assert.assertEquals(w, x.left());
    Assert.assertEquals(u, x.right());
    Assert.assertEquals(null, z.left());
    Assert.assertEquals(null, z.right());
    Assert.assertEquals(null, u.left());
    Assert.assertEquals(null, u.right());
    Assert.assertEquals(null, w.left());
    Assert.assertEquals(null, w.right());
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

    Assert.assertEquals(x, tree.root);
    Assert.assertEquals(w, x.left());
    Assert.assertEquals(y, x.right());
    Assert.assertEquals(u, y.left());
    Assert.assertEquals(z, y.right());
    Assert.assertEquals(null, w.left());
    Assert.assertEquals(null, w.right());
    Assert.assertEquals(null, u.left());
    Assert.assertEquals(null, u.right());
    Assert.assertEquals(null, z.left());
    Assert.assertEquals(null, z.right());
  }
}
