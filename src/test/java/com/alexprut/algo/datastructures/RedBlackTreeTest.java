package com.alexprut.algo.datastructures;

import com.alexprut.algo.datastructures.RedBlackTree.Node;
import org.junit.Assert;
import org.junit.Test;

public class RedBlackTreeTest {

  @Test
  public void shouldInsertAndSearch() {
    RedBlackTree<Integer> tree = new RedBlackTree<>();
    for (int i = 0; i < 100; i++) {
      tree.insert(i);
      Assert.assertTrue(tree.search(i));
    }
  }

  @Test
  public void shouldInsertAndDeleteAndSearch() {
    RedBlackTree<Integer> tree = new RedBlackTree<>();
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
    RedBlackTree<Integer> tree = new RedBlackTree<>();
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
    RedBlackTree<Integer> tree = new RedBlackTree<>();
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

    RedBlackTree<Integer> tree = new RedBlackTree<>();
    Node<Integer> x = new Node<>(11);
    Node<Integer> y = new Node<>(2, true);
    Node<Integer> z = new Node<>(1);
    Node<Integer> w = new Node<>(7);
    Node<Integer> u = new Node<>(5, true);
    Node<Integer> a = new Node<>(4, true);
    Node<Integer> b = new Node<>(8, true);
    Node<Integer> c = new Node<>(14);
    Node<Integer> d = new Node<>(15, true);
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

    Assert.assertNotNull(tree.root());
    Assert.assertNull(tree.root().parent());
    Assert.assertEquals(Integer.valueOf(15), tree.maximum().value());
    Assert.assertEquals(Integer.valueOf(1), tree.minimum().value());
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

    Assert.assertFalse(w.isRed());
    Assert.assertTrue(y.isRed());
    Assert.assertFalse(z.isRed());
    Assert.assertFalse(u.isRed());
    Assert.assertTrue(a.isRed());
    Assert.assertTrue(x.isRed());
    Assert.assertFalse(b.isRed());
    Assert.assertFalse(c.isRed());
    Assert.assertTrue(d.isRed());
  }

  @Test
  public void shouldFindSuccessorAndDelete() {
    RedBlackTree<Integer> tree = new RedBlackTree<>();
    tree.insert(5);
    tree.insert(6);
    tree.insert(7);
    tree.insert(8);
    tree.insert(9);
    tree.insert(1);
    tree.insert(2);
    tree.insert(3);
    tree.insert(4);
    tree.insert(0);

    Assert.assertEquals(1, (int) tree.successor(tree.minimum()).value());
    Assert.assertEquals(3, (int) tree.successor(tree.search(tree.root, 2)).value());
    Assert.assertEquals(6, (int) tree.successor(tree.search(tree.root, 5)).value());
    Assert.assertEquals(8, (int) tree.successor(tree.search(tree.root, 7)).value());
    Assert.assertNull(null, tree.successor(tree.maximum()));

    tree.delete(4);
    tree.delete(6);
    tree.delete(1);
    tree.delete(2);
    tree.delete(3);
    tree.delete(5);
    tree.delete(7);
    tree.delete(8);
    tree.delete(9);
    tree.delete(0);

    Assert.assertEquals(0, tree.size());
  }
}
