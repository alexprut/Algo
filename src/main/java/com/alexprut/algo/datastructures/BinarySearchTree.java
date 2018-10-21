package com.alexprut.algo.datastructures;

public class BinarySearchTree {

  private BinaryNode root;
  private int size;

  BinarySearchTree() {
    size = 0;
  }

  public void insert(int value) {
    BinaryNode node = new BinaryNode(value);

    if (root == null) {
      root = node;
    } else {
      insert(root, node);
    }
    size++;
  }

  public int size() {
    return this.size;
  }

  private void insert(BinaryNode parent, BinaryNode node) {
    if (parent.value() <= node.value()) {
      if (parent.left == null) {
        parent.left = node;
      } else {
        insert(parent.left, node);
      }
    } else {
      if (parent.right == null) {
        parent.right = node;
      } else {
        insert(parent.right, node);
      }
    }
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
