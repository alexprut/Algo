package com.alexprut.algo.datastructures;

import static java.lang.Math.max;

/** In an AVL tree, the heights of the two child subtrees of any node differ by at most one */
public class AVLTree {

  protected Node root;
  private int size;

  AVLTree() {}

  /** Time complexity: O(logn) */
  public void insert(int value) {
    Node tmp = insert(root, value);
    if (this.root == null) {
      this.root = tmp;
    }
    size++;
  }

  /** Time complexity: O(logn) */
  protected Node insert(Node node, int value) {
    if (node == null) {
      return new Node(value);
    }

    if (value <= node.value) {
      node.left = insert(node.left, value);
    } else {
      node.right = insert(node.right, value);
    }

    node.height = Math.max(height(node.left), height(node.right)) + 1;
    int balance = getBalance(node);

    // Case: Left Left
    if (balance > 1 && value < node.left.value) {
      return rightRotation(node);
    }

    // Case: Right Right
    if (balance < -1 && value > node.right.value) {
      return leftRotation(node);
    }

    // Case: Left Right
    if (balance > 1 && value > node.left.value) {
      node.left = leftRotation(node.left);
      return rightRotation(node);
    }

    // Case: Right Left
    if (balance < -1 && value < node.right.value) {
      node.right = rightRotation(node.right);
      return leftRotation(node);
    }

    return node;
  }

  /** Time complexity: O(logn) */
  public boolean search(int value) {
    return search(root, value) != null;
  }

  /** Time complexity: O(logn) */
  public Node search(Node root, int value) {
    Node tmp = root;
    while (tmp != null) {
      if (tmp.value == value) {
        return tmp;
      }
      tmp = (value < tmp.value) ? tmp.left : tmp.right;
    }

    return null;
  }

  /** Time complexity: O(logn) */
  public void delete(int value) {
    this.root = delete(this.root, value);
    size--;
  }

  protected Node delete(Node node, int value) {
    if (node == null) {
      return null;
    }

    if (value < node.value) {
      node.left = delete(node.left, value);
    } else if (value > node.value) {
      node.right = delete(node.right, value);
    } else {
      if (node.left == null || node.right == null) {
        Node temp = (node.left == null) ? node.right : node.left;

        if (temp == null) {
          node = null;
        } else {
          node = temp;
        }
      } else {
        Node temp = minimum(node.right);
        node.value = temp.value;
        node.right = delete(node.right, temp.value);
      }
    }

    if (node == null) {
      return null;
    }

    node.height = max(height(node.left), height(node.right)) + 1;
    int balance = getBalance(node);

    // Case: Left Left
    if (balance > 1 && getBalance(node.left) >= 0) {
      return rightRotation(node);
    }

    // Case: Left Right
    if (balance > 1 && getBalance(node.left) < 0) {
      node.left = leftRotation(node.left);
      return rightRotation(node);
    }

    // Case: Right Right
    if (balance < -1 && getBalance(node.right) <= 0) {
      return leftRotation(node);
    }

    // Case: Right Left
    if (balance < -1 && getBalance(node.right) > 0) {
      node.right = rightRotation(node.right);
      return leftRotation(node);
    }

    return node;
  }

  /** Time complexity: O(logn) if the tree is balanced */
  public Node minimum() {
    return minimum(root);
  }

  /** Time complexity: O(logn) if the tree is balanced */
  public Node maximum() {
    return maximum(root);
  }

  /** Time complexity: O(logn) if the tree is balanced */
  private Node minimum(Node node) {
    if (node == null || node.left == null) {
      return node;
    }
    return minimum(node.left);
  }

  /** Time complexity: O(logn) if the tree is balanced */
  private Node maximum(Node node) {
    if (node == null || node.right == null) {
      return node;
    }
    return maximum(node.right);
  }

  /** Time complexity: O(logn) if the tree is balanced */
  private Node successor(Node node) {
    if (node.right != null) {
      return minimum(node.right);
    }
    Node y = node.parent;
    while (y != null && node == y.right) {
      node = y;
      y = y.parent;
    }
    return y;
  }

  /** Time complexity: Θ(1) */
  public int size() {
    return this.size;
  }

  /** Time complexity: Θ(1) */
  protected Node leftRotation(Node x) {
    Node y = x.right;
    x.right = y.left;
    if (y.left != null) {
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
    x.parent = y;

    y.height = max(height(y.left), height(y.right)) + 1;
    x.height = max(height(x.left), height(x.right)) + 1;

    return y;
  }

  /** Time complexity: Θ(1) */
  protected Node rightRotation(Node x) {
    Node y = x.left;
    x.left = y.right;
    if (y.right != null) {
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
    x.parent = y;

    y.height = max(height(y.left), height(y.right)) + 1;
    x.height = max(height(x.left), height(x.right)) + 1;

    return y;
  }

  protected int getBalance(Node n) {
    if (n == null) {
      return 0;
    }

    return height(n.left()) - height(n.right());
  }

  private int height(Node n) {
    if (n == null) {
      return 0;
    }

    return n.height;
  }

  protected static class Node {

    private int value;
    private int height;
    private Node parent;
    private Node left;
    private Node right;

    Node(int value) {
      this.value = value;
      this.height = 1;
    }

    public int value() {
      return this.value;
    }

    public int getHeight() {
      return this.height;
    }

    public Node left() {
      return this.left;
    }

    public Node right() {
      return this.right;
    }

    public Node parent() {
      return this.parent;
    }

    public void setLeft(Node left) {
      this.left = left;
      left.parent = this;
    }

    public void setRight(Node right) {
      this.right = right;
      right.parent = this;
    }

    public void setParent(Node parent) {
      this.parent = parent;
    }
  }
}
