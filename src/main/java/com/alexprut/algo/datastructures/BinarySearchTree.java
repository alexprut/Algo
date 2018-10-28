package com.alexprut.algo.datastructures;

public class BinarySearchTree {

  private BinaryNode root;
  private int size;

  BinarySearchTree() {
    size = 0;
  }

  /** Time complexity: O(logn) if the tree is balanced, O(n) in the worst case */
  public void insert(int value) {
    BinaryNode node = new BinaryNode(value);

    if (root == null) {
      root = node;
    } else {
      insert(root, node);
    }
    size++;
  }

  /** Time complexity: O(logn) if the tree is balanced, O(n) in the worst case */
  public BinaryNode search(int value) {
    return search(root, value);
  }

  /** Time complexity: O(logn) if the tree is balanced, O(n) in the worst case */
  public void remove(BinaryNode node) {
    // TODO
  }

  // TODO successor
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

  public int size() {
    return this.size;
  }

  /** Time complexity: O(logn) if the tree is balanced, O(n) in the worst case */
  private void insert(BinaryNode parent, BinaryNode node) {
    if (parent.value() < node.value()) {
      if (parent.right == null) {
        parent.right = node;
      } else {
        insert(parent.right, node);
      }
    } else {
      if (parent.left == null) {
        parent.left = node;
      } else {
        insert(parent.left, node);
      }
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
    private BinaryNode left;
    private BinaryNode right;

    BinaryNode(int value) {
      this.value = value;
    }

    public int value() {
      return this.value;
    }

    public BinaryNode left() {
      return this.left;
    }

    public BinaryNode right() {
      return this.right;
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
