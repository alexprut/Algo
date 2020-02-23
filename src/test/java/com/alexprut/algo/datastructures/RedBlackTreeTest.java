package com.alexprut.algo.datastructures;

import com.alexprut.algo.datastructures.RedBlackTree.Node;
import org.junit.Assert;
import org.junit.Test;

public class RedBlackTreeTest {

  @Test
  public void shouldInsertAndSearch() {
    RedBlackTree tree = new RedBlackTree();
    for (int i = 0; i < 100; i++) {
      tree.insert(i);
      Assert.assertTrue(tree.search(i));
    }
  }

  @Test
  public void shouldInsertAndDeleteAndSearch() {
    RedBlackTree tree = new RedBlackTree();
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
    RedBlackTree tree = new RedBlackTree();
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
    Assert.assertNull(z.left());
    Assert.assertNull(z.right());
    Assert.assertNull(u.left());
    Assert.assertNull(u.right());
    Assert.assertNull(w.left());
    Assert.assertNull(w.right());
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
    RedBlackTree tree = new RedBlackTree();
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
    Assert.assertNull(w.left());
    Assert.assertNull(w.right());
    Assert.assertNull(u.left());
    Assert.assertNull(u.right());
    Assert.assertNull(z.left());
    Assert.assertNull(z.right());
  }

  @Test
  public void shouldTestInsertFixup() {
    /*
      B = Black
      R = Red
                                11,B
                               /    \
                            2,R      14,B
                           /   \         \
                        1,B     7,B       15,R
                               /   \
                            5,R     8,R
                           /
                        4,R

                                 | Case 1
                                 ˅

                                11,B
                               /    \
                            2,R      14,B
                           /   \         \
                        1,B     7,R       15,R
                               /   \
                            5,B     8,B
                           /
                        4,R

                                 | Case 2
                                 ˅

                                11,B
                               /    \
                            7,R      14,B
                           /   \         \
                        2,R     8,B       15,R
                       /   \
                   1,B     5,B
                          /
                       4,R

                                 | Case 3
                                 ˅

                                7,B
                            /        \
                         2,R          11,R
                        /   \        /    \
                     1,B     5,B  8,B      14,B
                            /                  \
                         4,R                    15,R
    */

    RedBlackTree tree = new RedBlackTree();
    Node x = new Node(11);
    Node y = new Node(2, true);
    Node z = new Node(1);
    Node w = new Node(7);
    Node u = new Node(5, true);
    Node a = new Node(4, true);
    Node b = new Node(8, true);
    Node c = new Node(14);
    Node d = new Node(15, true);
    tree.root = x;

    x.setLeft(y);
    x.setRight(c);
    y.setLeft(z);
    y.setRight(w);
    w.setLeft(u);
    w.setRight(b);
    u.setLeft(a);
    c.setRight(d);

    tree.insertFixup(a);

    Assert.assertEquals(y, w.left());
    Assert.assertEquals(x, w.right());
    Assert.assertEquals(z, y.left());
    Assert.assertEquals(u, y.right());
    Assert.assertEquals(a, u.left());
    Assert.assertEquals(b, x.left());
    Assert.assertEquals(c, x.right());
    Assert.assertEquals(d, c.right());
    Assert.assertNull(z.left());
    Assert.assertNull(z.right());
    Assert.assertNull(a.left());
    Assert.assertNull(a.right());
    Assert.assertNull(b.left());
    Assert.assertNull(b.right());
    Assert.assertNull(d.left());
    Assert.assertNull(d.right());
    Assert.assertNull(u.right());
    Assert.assertNull(c.left());

    Assert.assertEquals(false, w.isRed());
    Assert.assertEquals(true, y.isRed());
    Assert.assertEquals(false, z.isRed());
    Assert.assertEquals(false, u.isRed());
    Assert.assertEquals(true, a.isRed());
    Assert.assertEquals(true, x.isRed());
    Assert.assertEquals(false, b.isRed());
    Assert.assertEquals(false, c.isRed());
    Assert.assertEquals(true, d.isRed());
  }
}
