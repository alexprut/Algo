package com.alexprut.algo.datastructures;

import static java.lang.Math.max;

/**
 * An AVL Tree is a balanced binary search tree (alike {@link RedBlackTree}). The data structure is
 * height balanced, it satisfies the following property:
 *
 * <ol>
 *   <li>for each node x, the heights of the left and right subtrees of x differ by at most 1.
 * </ol>
 *
 * <p>Example:
 *
 * <pre>
 * x,h
 *
 * x = the element
 * h = the heights difference between the left and right subtrees
 *
 *    30,1
 *   /   \
 * 5,0  35,0
 *      /   \
 *   32,0   40,0
 * </pre>
 *
 * @see <a href="https://en.wikipedia.org/wiki/AVL_tree">https://en.wikipedia.org/wiki/AVL_tree</a>
 */
public class AVLTree<T extends Comparable<T>> {

  protected Node<T> root;
  private int size;

  AVLTree() {}

  /**
   * Get the root node.
   *
   * <p>Time complexity: O(1)
   *
   * <p>Space complexity: O(1)
   *
   * @return the root node
   */
  public Node<T> root() {
    return this.root;
  }

  /**
   * Insert a new element.
   *
   * <p>Time complexity: O(logn)
   *
   * <p>Space complexity: O(1)
   *
   * @param value the new element value
   */
  public void insert(T value) {
    Node<T> tmp = insert(root, value);
    if (this.root == null) {
      this.root = tmp;
    }
    size++;
  }

  /**
   * Insert a new element.
   *
   * <p>Time complexity: O(logn)
   *
   * <p>Space complexity: O(1)
   *
   * @param node the root node where to insert
   * @param value the new element value
   * @return the new inserted node
   */
  protected Node<T> insert(Node<T> node, T value) {
    if (node == null) {
      return new Node<>(value);
    }

    if (value.compareTo(node.value) <= 0) {
      node.left = insert(node.left, value);
      node.left.parent = node;
    } else {
      node.right = insert(node.right, value);
      node.right.parent = node;
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

  /**
   * Search if an element is within the tree.
   *
   * <p>Time complexity: O(logn)
   *
   * <p>Space complexity: O(1)
   *
   * @param value the element value to search
   * @return true if element is within the tree
   */
  public boolean search(T value) {
    return search(root, value) != null;
  }

  /**
   * Given a root node, search if an element is within the rooted tree.
   *
   * <p>Time complexity: O(logn)
   *
   * <p>Space complexity: O(1)
   *
   * @param root the root node where to start the search
   * @param value the element value to search
   * @return the node containing the element value, otherwise null
   */
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

  /**
   * Delete the specified element from the tree if exists.
   *
   * <p>Time complexity: O(logn)
   *
   * <p>Space complexity: O(1)
   *
   * @param value the element value
   */
  public void delete(T value) {
    this.root = delete(this.root, value);
    size--;
  }

  /**
   * Utility method for {@link #delete(Comparable)}.
   *
   * <p>Time complexity: O(logn)
   *
   * <p>Space complexity: O(1)
   *
   * @param node the root node where to start the deletion
   * @param value the element value
   * @return the new root
   */
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
        node = (node.left == null) ? node.right : node.left;
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

  /**
   * Get the minimum element.
   *
   * <p>Time complexity: O(logn)
   *
   * <p>Space complexity: O(1)
   *
   * @return the minimum element
   */
  public Node<T> minimum() {
    return minimum(root);
  }

  /**
   * Get the maximum element.
   *
   * <p>Time complexity: O(logn)
   *
   * <p>Space complexity: O(1)
   *
   * @return the maximum element
   */
  public Node<T> maximum() {
    return maximum(root);
  }

  /**
   * Get the minimum element node.
   *
   * <p>Time complexity: O(logn)
   *
   * <p>Space complexity: O(1)
   *
   * @param node the minimum in the rooted node
   * @return the minimum node
   */
  private Node<T> minimum(Node<T> node) {
    if (node == null || node.left == null) {
      return node;
    }
    return minimum(node.left);
  }

  /**
   * Get the maximum element node.
   *
   * <p>Time complexity: O(logn)
   *
   * <p>Space complexity: O(1)
   *
   * @param node the maximum in the rooted node
   * @return the maximum node
   */
  private Node<T> maximum(Node<T> node) {
    if (node == null || node.right == null) {
      return node;
    }
    return maximum(node.right);
  }

  /**
   * Get the successor of element node.
   *
   * <p>Time complexity: O(logn)
   *
   * <p>Space complexity: O(1)
   *
   * @param node the current node
   * @return the successor node
   */
  public Node<T> successor(Node<T> node) {
    if (node.right != null) {
      return minimum(node.right);
    }
    Node<T> y = node.parent;
    while (y != null && node == y.right) {
      node = y;
      y = y.parent;
    }
    return y;
  }

  /**
   * Get the number of elements contained within the tree.
   *
   * <p>Time complexity: Θ(1)
   *
   * <p>Space complexity: Θ(1)
   *
   * @return the size of the tree
   */
  public int size() {
    return this.size;
  }

  /**
   * Utility method. Use the specified element as a pivot and left rotate.
   *
   * <p>Example:
   *
   * <pre>
   *   x                           y
   *  / \     leftRotate(x)       / \
   * w   y    ------------→      x   z
   *    / \                     / \
   *   u   z                   w   u
   * </pre>
   *
   * <p>Time complexity: Θ(1)
   *
   * <p>Space complexity: Θ(1)
   *
   * @param x the pivot element
   * @return the rotated rooted element
   */
  protected Node<T> leftRotation(Node<T> x) {
    Node<T> y = x.right;
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

    x.height = max(height(x.left), height(x.right)) + 1;
    y.height = max(height(y.left), height(y.right)) + 1;

    return y;
  }

  /**
   * Utility method. Use the specified element as a pivot and right rotate.
   *
   * <p>Example:
   *
   * <pre>
   *   x                            y
   *  / \     rightRotate(y)       / \
   * w   y    ←-------------      x   z
   *    / \                      / \
   *   u   z                    w   u
   * </pre>
   *
   * <p>Time complexity: Θ(1)
   *
   * <p>Space complexity: Θ(1)
   *
   * @param x the pivot element
   * @return the rotated rooted element
   */
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

    x.height = max(height(x.left), height(x.right)) + 1;
    y.height = max(height(y.left), height(y.right)) + 1;

    return y;
  }

  /**
   * Utility method. Calculate the balance between the left and right subtrees.
   *
   * <p>Time complexity: O(1)
   *
   * <p>Space complexity: O(1)
   *
   * @param n the root node
   * @return the balance
   */
  protected int getBalance(Node<T> n) {
    if (n == null) {
      return 0;
    }

    return height(n.left()) - height(n.right());
  }

  /**
   * Get the height of a node.
   *
   * <p>Time complexity: O(1)
   *
   * <p>Space complexity: O(1)
   *
   * @param n the node
   * @return the height
   */
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

    protected Node(T value) {
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

    protected void setLeft(Node<T> left) {
      this.left = left;
      left.parent = this;
    }

    protected void setRight(Node<T> right) {
      this.right = right;
      right.parent = this;
    }

    protected void setParent(Node<T> parent) {
      this.parent = parent;
    }
  }
}
