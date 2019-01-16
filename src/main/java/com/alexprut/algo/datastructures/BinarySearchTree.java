package com.alexprut.algo.datastructures;

public class BinarySearchTree {

  private BinaryNode root;
  private int size;

  public BinarySearchTree() {
    size = 0;
  }

  /** Time complexity: O(logn) if the tree is balanced, O(n) in the worst case */
  public void insert(int value) {
    BinaryNode node = new BinaryNode(value);

    if (root == null) {
      root = node;
    } else {
      insert(node);
    }
    size++;
  }

  /** Time complexity: O(logn) if the tree is balanced, O(n) in the worst case */
  public BinaryNode search(int value) {
    return search(root, value);
  }

  /** Time complexity: O(logn) if the tree is balanced, O(n) in the worst case */
  public void delete(BinaryNode node) {
    if (node.left == null) {
      transplant(node, node.right);
    } else if (node.right == null) {
      transplant(node, node.left);
    } else {
      BinaryNode y = minimum(node.right);
      if (y.parent() != node) {
        transplant(y, y.right);
        y.right = node.right;
        y.right.parent = y;
      }
      transplant(node, y);
      y.left = node.left;
      y.left.parent = y;
    }
    size--;
  }

  /**
   * Replaces the subtree rooted at node `u` with the subtree rooted at node `v`
   *
   * Time complexity: O(1)
   */
  private void transplant(BinaryNode u, BinaryNode v) {
    if (u.parent == null) {
      root = v;
    } else if (u == u.parent.left) {
      u.parent.left = v;
    } else {
      u.parent.right = v;
    }
    if (v != null) {
      v.parent = u.parent;
    }
  }

  /**
   * Time complexity: O(logn) if the tree is balanced, O(n) in the worst case
   */
  protected BinaryNode successor(BinaryNode x) {
    if (x.right != null) {
      return minimum(x);
    }
    BinaryNode y = x.parent;
    while (y != null && x == y.right) {
      x = y;
      y = y.parent;
    }
    return y;
  }

  // TODO predecessor
  // TODO inOrderVisit
  // TODO preOrderVisit
  // TODO postOrderVisit

  /** Time complexity: O(logn) if the tree is balanced, O(n) in the worst case TODO interactive */
  private BinaryNode search(BinaryNode node, int value) {
    if (node == null || node.value == value) {
      return node;
    }
    if (node.value < value) {
      return search(node.right, value);
    }
    return search(node.left, value);
  }

  /** Time complexity: O(logn) if the tree is balanced, O(n) in the worst case */
  public boolean contains(int value) {
    return search(root, value) != null;
  }

  /** Time complexity: Θ(1) */
  public int size() {
    return this.size;
  }

  /** Time complexity: O(logn) if the tree is balanced, O(n) in the worst case */
  private void insert(BinaryNode node) {
    BinaryNode y = null;
    BinaryNode x = root;
    while (x != null) {
      y = x;
      if (node.value() < x.value()) {
        x = x.left();
      } else {
        x = x.right();
      }
    }
    node.setParent(y);
    if (y == null) {
      root = node;
    } else if (node.value() < y.value()) {
      y.left = node;
    } else {
      y.right = node;
    }
  }

  /** Time complexity: O(logn) if the tree is balanced, O(n) in the worst case */
  public BinaryNode minimum() {
    return minimum(root);
  }

  /** Time complexity: O(logn) if the tree is balanced, O(n) in the worst case */
  public BinaryNode maximum() {
    return maximum(root);
  }

  /** Time complexity: O(logn) if the tree is balanced, O(n) in the worst case TODO interactive */
  private BinaryNode minimum(BinaryNode node) {
    if (node == null || node.left == null) {
      return node;
    }
    return minimum(node.left);
  }

  /** Time complexity: O(logn) if the tree is balanced, O(n) in the worst case TODO interactive */
  private BinaryNode maximum(BinaryNode node) {
    if (node == null || node.right == null) {
      return node;
    }
    return maximum(node.right);
  }

  public static class BinaryNode {

    private int value;
    private BinaryNode parent;
    private BinaryNode left;
    private BinaryNode right;

    BinaryNode(int value) {
      this.value = value;
    }

    public int value() {
      return this.value;
    }

    public BinaryNode parent() {
      return this.parent;
    }

    public BinaryNode left() {
      return this.left;
    }

    public BinaryNode right() {
      return this.right;
    }

    public void setParent(BinaryNode parent) {
      this.parent = parent;
    }

    public void setLeft(BinaryNode left) {
      this.left = left;
    }

    public void setRight(BinaryNode right) {
      this.right = right;
    }

    public boolean equals(BinaryNode b) {
      return this.value == b.value();
    }
  }
}
