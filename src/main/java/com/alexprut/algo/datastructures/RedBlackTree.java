package com.alexprut.algo.datastructures;

/**
 * A red-black tree is a binary tree that satisfies the following red-black properties:
 * 1) Every node is either red or black
 * 2) The root is black
 * 3) Every leaf (NULL) is black
 * 4) If a node is red, then both its children are black
 * 5) For each node, all simple paths from the node to descendant leaves contain the same number of black nodes
 */
public class RedBlackTree {

  private Node root;

  RedBlackTree() {
  }

  /**
   * Time complexity: O(logn)
   */
  public void insert(int value) {
    Node x = new Node(value, true);
    Node p = null;
    Node tmp = this.root;

    while (tmp != null) {
      p = tmp;
      if (x.value() < tmp.value) {
        tmp = tmp.left;
      } else {
        tmp = tmp.right;
      }
    }
    x.parent = p;
    if (p == null) {
      root = x;
    } else if (x.value() < p.value()) {
      p.setLeft(x);
    } else {
      p.setRight(x);
    }
    insertFixup(x);
  }

  /**
   * Time complexity: O(logn)
   */
  private void insertFixup(Node x) {
    while (x.parent != null && x.parent.isRed()) {
      if (x.parent == x.parent.parent.left) {
        Node y = x.parent.parent.right;
        if (y.isRed()) {
          // case 1
          x.parent.setBlackColor();
          y.setBlackColor();
          x.parent.parent.setRedColor();
          x = x.parent.parent;
        } else if (x == x.parent.right) {
          // case 2
          x = x.parent;
          leftRotation(x);
        }
        // case 3
        x.parent.setBlackColor();
        x.parent.parent.setRedColor();
        rightRotation(x.parent.parent);
      } else {
        Node y = x.parent.parent.left;
        if (y.isRed()) {
          // case 1
          x.parent.setBlackColor();
          y.setBlackColor();
          x.parent.parent.setRedColor();
          x = x.parent.parent;
        } else if (x == x.parent.left) {
          // case 2
          x = x.parent;
          rightRotation(x);
        }
        // case 3
        x.parent.setBlackColor();
        x.parent.parent.setRedColor();
        leftRotation(x.parent.parent);
      }
    }
    root.setBlackColor();
  }

  /**
   * Time complexity: O(logn)
   */
  public boolean search(int value) {
    Node tmp = root;
    while (tmp != null) {
      if (tmp.value == value) {
        return true;
      }
      tmp = (value < tmp.value) ? tmp.left : tmp.right;
    }

    return false;
  }

  /**
   * Time complexity: O(logn)
   */
  public boolean delete(int value) {
    return true;
  }

  /**
   * Time complexity: Θ(1)
   */
  protected void leftRotation(Node x) {
    Node y = x.right;
    x.right = y.left;
    if (y.left == null) {
      y.left.parent = x;
    }
    y.parent = x.parent;
    if (x.parent == null) {
      root = y;
    } else if (x == x.parent.left) {
      x.parent.left = y;
    } else {
      x.parent.right = y;
    }
    y.left = x;
    x.parent = x;
  }

  /**
   * Time complexity: Θ(1)
   */
  protected void rightRotation(Node x) {
    Node y = x.left;
    x.left = y.right;
    if (y.right == null) {
      y.right.parent = x;
    }
    y.parent = x.parent;
    if (x.parent == null) {
      root = y;
    } else if (x == x.parent.right) {
      x.parent.right = y;
    } else {
      x.parent.left = y;
    }
    y.right = x;
    x.parent = x;
  }

  // TODO implement functions: predecessor, successor, min, max

  protected static class Node {

    private int value;
    private boolean color; // when true then color is Red, otherwise Black
    private Node parent;
    private Node left;
    private Node right;

    Node(int value) {
      this.value = value;
    }

    Node(int value, boolean isRed) {
      this.value = value;
      this.color = isRed;
    }

    public int value() {
      return this.value;
    }

    public Node left() {
      return this.left;
    }

    public Node right() {
      return this.right;
    }

    public boolean isRed() {
      return this.color;
    }

    public void setLeft(Node left) {
      this.left = left;
    }

    public void setRight(Node right) {
      this.right = right;
    }

    public void setRedColor() {
      this.color = true;
    }

    public void setBlackColor() {
      this.color = false;
    }

    public boolean equals(Node b) {
      return this.value == b.value();
    }
  }
}
