package com.alexprut.algo.datastructures;

import com.alexprut.algo.datastructures.RedBlackTree.Node;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RedBlackTreeTest {

  @Test
  public void shouldInsertAndSearch() {
    RedBlackTree<Integer> tree = new RedBlackTree<>();
    for (int i = 0; i < 100; i++) {
      tree.insert(i);
      Assertions.assertTrue(tree.search(i));
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

    Assertions.assertNotNull(tree.root());
    Assertions.assertNull(tree.root().parent());
    Assertions.assertEquals(Integer.valueOf(15), tree.maximum().value());
    Assertions.assertEquals(Integer.valueOf(1), tree.minimum().value());
    Assertions.assertEquals(y, w.left());
    Assertions.assertEquals(x, w.right());
    Assertions.assertEquals(z, y.left());
    Assertions.assertEquals(u, y.right());
    Assertions.assertEquals(a, u.left());
    Assertions.assertEquals(b, x.left());
    Assertions.assertEquals(c, x.right());
    Assertions.assertEquals(d, c.right());
    Assertions.assertNull(z.left());
    Assertions.assertNull(z.right());
    Assertions.assertNull(a.left());
    Assertions.assertNull(a.right());
    Assertions.assertNull(b.left());
    Assertions.assertNull(b.right());
    Assertions.assertNull(d.left());
    Assertions.assertNull(d.right());
    Assertions.assertNull(u.right());
    Assertions.assertNull(c.left());

    Assertions.assertFalse(w.isRed());
    Assertions.assertTrue(y.isRed());
    Assertions.assertFalse(z.isRed());
    Assertions.assertFalse(u.isRed());
    Assertions.assertTrue(a.isRed());
    Assertions.assertTrue(x.isRed());
    Assertions.assertFalse(b.isRed());
    Assertions.assertFalse(c.isRed());
    Assertions.assertTrue(d.isRed());
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

    Assertions.assertEquals(1, (int) tree.successor(tree.minimum()).value());
    Assertions.assertEquals(3, (int) tree.successor(tree.search(tree.root, 2)).value());
    Assertions.assertEquals(6, (int) tree.successor(tree.search(tree.root, 5)).value());
    Assertions.assertEquals(8, (int) tree.successor(tree.search(tree.root, 7)).value());
    Assertions.assertNull(tree.successor(tree.maximum()));

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

    Assertions.assertEquals(0, tree.size());
  }

  @Test
  public void shouldFindPredecessorAndDelete() {
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

    Assertions.assertNull(tree.predecessor(tree.search(tree.root, 0)));
    Assertions.assertEquals(0, (int) tree.predecessor(tree.search(tree.root, 1)).value());
    Assertions.assertEquals(1, (int) tree.predecessor(tree.search(tree.root, 2)).value());
    Assertions.assertEquals(2, (int) tree.predecessor(tree.search(tree.root, 3)).value());
    Assertions.assertEquals(3, (int) tree.predecessor(tree.search(tree.root, 4)).value());
    Assertions.assertEquals(4, (int) tree.predecessor(tree.search(tree.root, 5)).value());
    Assertions.assertEquals(5, (int) tree.predecessor(tree.search(tree.root, 6)).value());

    tree.delete(4);
    tree.delete(6);
    tree.delete(3);
    tree.delete(5);
    tree.delete(7);
    Assertions.assertEquals(2, (int) tree.predecessor(tree.search(tree.root, 8)).value());
  }
}
