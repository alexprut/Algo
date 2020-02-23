package com.alexprut.algo.datastructures;

import com.alexprut.algo.datastructures.AVLTree.Node;
import org.junit.Assert;
import org.junit.Test;

public class AVLTreeTest {

  @Test
  public void shouldInsertAndSearch() {
    AVLTree tree = new AVLTree();
    for (int i = 0; i < 100; i++) {
      tree.insert(i);
      Assert.assertTrue(tree.search(i));
    }
  }

  @Test
  public void shouldInsertAndDeleteAndSearch() {
    AVLTree tree = new AVLTree();
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
    AVLTree tree = new AVLTree();
    Node x = new Node(1);
    Node y = new Node(2);
    Node z = new Node(3);
    Node w = new Node(4);
    Node u = new Node(5);
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
    AVLTree tree = new AVLTree();
    Node x = new Node(1);
    Node y = new Node(2);
    Node z = new Node(3);
    Node w = new Node(4);
    Node u = new Node(5);
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
