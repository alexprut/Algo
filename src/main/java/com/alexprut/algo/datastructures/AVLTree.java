package com.alexprut.algo.datastructures;

import static java.lang.Math.max;

/** In an AVL tree, the heights of the two child subtrees of any node differ by at most one */
public class AVLTree<T extends Comparable> {

  protected Node<T> root;
  private int size;

  AVLTree() {}

  /** Time complexity: O(logn) */
  public void insert(T value) {
    Node tmp = insert(root, value);
    if (this.root == null) {
      this.root = tmp;
    }
    size++;
  }

  /** Time complexity: O(logn) */
  protected Node<T> insert(Node<T> node, T value) {
    if (node == null) {
      return new Node<T>(value);
    }

    if (value.compareTo(node.value) <= 0) {
      node.left = insert(node.left, value);
    } else {
      node.right = insert(node.right, value);
    }

    node.height = Math.max(height(node.left), height(node.right)) + 1;
    int balance = getBalance(node);

    // Case: Left Left
    if (balance > 1 && value.compareTo(node.left.value) < 0) {
      return rightRotation(node);
    }

    // Case: Right Right
    if (balance < -1 && value.compareTo(node.right.value) > 0) {
      return leftRotation(node);
    }

    // Case: Left Right
    if (balance > 1 && value.compareTo(node.left.value) > 0) {
      node.left = leftRotation(node.left);
      return rightRotation(node);
    }

    // Case: Right Left
    if (balance < -1 && value.compareTo(node.right.value) < 0) {
      node.right = rightRotation(node.right);
      return leftRotation(node);
    }

    return node;
  }

  /** Time complexity: O(logn) */
  public boolean search(T value) {
    return search(root, value) != null;
  }

  /** Time complexity: O(logn) */
  public Node<T> search(Node<T> root, T value) {
    Node<T> tmp = root;
    while (tmp != null) {
      if (tmp.value.compareTo(value) == 0) {
        return tmp;
      }
      tmp = (value.compareTo(tmp.value) < 0) ? tmp.left : tmp.right;
    }

    return null;
  }

  /** Time complexity: O(logn) */
  public void delete(T value) {
    this.root = delete(this.root, value);
    size--;
  }

  protected Node<T> delete(Node<T> node, T value) {
    if (node == null) {
      return null;
    }

    if (value.compareTo(node.value) < 0) {
      node.left = delete(node.left, value);
    } else if (value.compareTo(node.value) > 0) {
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
        Node<T> temp = minimum(node.right);
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
  public Node<T> minimum() {
    return minimum(root);
  }

  /** Time complexity: O(logn) if the tree is balanced */
  public Node<T> maximum() {
    return maximum(root);
  }

  /** Time complexity: O(logn) if the tree is balanced */
  private Node<T> minimum(Node<T> node) {
    if (node == null || node.left == null) {
      return node;
    }
    return minimum(node.left);
  }

  /** Time complexity: O(logn) if the tree is balanced */
  private Node<T> maximum(Node<T> node) {
    if (node == null || node.right == null) {
      return node;
    }
    return maximum(node.right);
  }

  /** Time complexity: O(logn) if the tree is balanced */
  private Node<T> successor(Node<T> node) {
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
  protected Node<T> leftRotation(Node<T> x) {
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
  protected Node<T> rightRotation(Node<T> x) {
    Node<T> y = x.left;
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

  protected int getBalance(Node<T> n) {
    if (n == null) {
      return 0;
    }

    return height(n.left()) - height(n.right());
  }

  private int height(Node<T> n) {
    if (n == null) {
      return 0;
    }

    return n.height;
  }

  protected static class Node<T> {

    private T value;
    private int height;
    private Node<T> parent;
    private Node<T> left;
    private Node<T> right;

    Node(T value) {
      this.value = value;
      this.height = 1;
    }

    public T value() {
      return this.value;
    }

    public int getHeight() {
      return this.height;
    }

    public Node<T> left() {
      return this.left;
    }

    public Node<T> right() {
      return this.right;
    }

    public Node<T> parent() {
      return this.parent;
    }

    public void setLeft(Node<T> left) {
      this.left = left;
      left.parent = this;
    }

    public void setRight(Node<T> right) {
      this.right = right;
      right.parent = this;
    }

    public void setParent(Node<T> parent) {
      this.parent = parent;
    }
  }
}
