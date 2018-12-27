package com.alexprut.algo.datastructures;

import com.alexprut.algo.datastructures.RedBlackTree.Node;
import org.junit.Assert;
import org.junit.Test;

public class RedBlackTreeTest {

  @Test
  public void shouldInsertAndSearch() {
    RedBlackTree tree = new RedBlackTree();
    tree.insert(1);

    Assert.assertEquals(true, tree.search(1));
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

    x.setLeft(w);
    x.setRight(y);
    y.setRight(z);
    y.setLeft(u);
    tree.leftRotation(x);

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
    RedBlackTree tree = new RedBlackTree();
    Node x = new Node(1);
    Node y = new Node(2);
    Node z = new Node(3);
    Node w = new Node(4);
    Node u = new Node(5);

    y.setLeft(x);
    y.setRight(z);
    x.setLeft(w);
    x.setRight(u);
    tree.rightRotation(y);

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
  }
}
