package com.alexprut.algo.datastructures;

import java.util.ArrayList;
import java.util.List;

/**
 * A binary search tree (BST), is a rooted binary tree whose internal nodes each store a key greater
 * than all the keys in the node's left subtree and less than those in its right subtree. Binary
 * search trees allow binary search for fast lookup, addition and removal of elements.
 *
 * <p>Example:
 *
 * <pre>
 *       5
 *    /    \
 *   2      6
 *  / \
 * 1   3
 *      \
 *       4
 * </pre>
 *
 * @see <a
 *     href="https://en.wikipedia.org/wiki/Binary_search_tree">https://en.wikipedia.org/wiki/Binary_search_tree</a>
 * @param <T>
 */
public class BinarySearchTree<T extends Comparable<T>> {

  private BinaryNode<T> root;
  private int size = 0;

  public BinarySearchTree() {}

  /**
   * Insert a new element.
   *
   * <p>Time complexity: O(logn) if the tree is balanced, O(n) in the worst case
   *
   * <p>Space complexity: O(1)
   *
   * @param value the new element value
   */
  public void insert(T value) {
    BinaryNode<T> node = new BinaryNode<T>(value);

    if (root == null) {
      root = node;
    } else {
      insert(node);
    }
    size++;
  }

  /**
   * Search and get an element if it is within the tree.
   *
   * <p>Time complexity: O(logn) if the tree is balanced, O(n) in the worst case
   *
   * <p>Space complexity: O(1)
   *
   * @param value the element value to search
   * @return the node containing the element value, otherwise null
   */
  public BinaryNode<T> search(T value) {
    return search(root, value);
  }

  /**
   * Delete the specified element from the tree if exists.
   *
   * <p>Time complexity: O(logn) if the tree is balanced, O(n) in the worst case
   *
   * <p>Space complexity: O(1)
   *
   * @param node the node to delete
   */
  public void delete(BinaryNode<T> node) {
    if (node.left == null) {
      transplant(node, node.right);
    } else if (node.right == null) {
      transplant(node, node.left);
    } else {
      BinaryNode<T> y = minimum(node.right);
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
   * Replaces the subtree rooted at node `u` with the subtree rooted at node `v`.
   *
   * <p>Time complexity: O(1)
   *
   * <p>Space complexity: O(1)
   *
   * @param u to be replaced
   * @param v the one that replaces
   */
  private void transplant(BinaryNode<T> u, BinaryNode<T> v) {
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
   * Get the successor of element node.
   *
   * <p>Time complexity: O(logn) if the tree is balanced, O(n) in the worst case
   *
   * <p>Space complexity: O(1)
   *
   * @param x the current node
   * @return the successor node
   */
  protected BinaryNode<T> successor(BinaryNode<T> x) {
    if (x.right != null) {
      return minimum(x.right);
    }
    BinaryNode<T> y = x.parent;
    while (y != null && x == y.right) {
      x = y;
      y = y.parent;
    }
    return y;
  }

  // TODO predecessor

  /**
   * Pre-Order visit of the BST.
   *
   * <p>Time complexity: O(logn) if the tree is balanced, O(n) in the worst case
   *
   * <p>Space complexity: O(n)
   *
   * @return the list of elements
   */
  public List<T> preOrderVisit() {
    if (root == null) {
      return new ArrayList<>();
    }
    List<T> result = new ArrayList<>();
    Stack<BinaryNode<T>> stack = new Stack<>();
    stack.push(root);
    while (!stack.empty()) {
      try {
        BinaryNode<T> current = stack.pop();
        result.add(current.value);
        if (current.right != null) {
          stack.push(current.right);
        }
        if (current.left != null) {
          stack.push(current.left);
        }
      } catch (Exception e) {
      }
    }

    return result;
  }

  /**
   * In-Order visit of the BST.
   *
   * <p>Time complexity: O(logn) if the tree is balanced, O(n) in the worst case
   *
   * <p>Space complexity: O(n)
   *
   * @return the list of elements
   */
  public List<T> inOrderVisit() {
    if (root == null) {
      return new ArrayList<>();
    }
    BinaryNode<T> node = root;
    List<T> result = new ArrayList<>();
    Stack<BinaryNode<T>> stack = new Stack<>();
    while (!stack.empty() || node != null) {
      try {
        if (node != null) {
          stack.push(node);
          node = node.left;
        } else {
          node = stack.pop();
          result.add(node.value);
          node = node.right;
        }
      } catch (Exception e) {
      }
    }

    return result;
  }

  /**
   * Post-Order visit of the BST.
   *
   * <p>Time complexity: O(logn) if the tree is balanced, O(n) in the worst case
   *
   * <p>Space complexity: O(n)
   *
   * @return the list of elements
   */
  public List<T> postOrderVisit() {
    if (root == null) {
      return new ArrayList<>();
    }
    List<T> result = new ArrayList<>();
    Stack<BinaryNode<T>> stack = new Stack<>();
    BinaryNode<T> node = root;
    BinaryNode<T> lastVisited = null;
    while (!stack.empty() || node != null) {
      try {
        if (node != null) {
          stack.push(node);
          node = node.left;
        } else {
          BinaryNode<T> tmp = stack.peek();
          if (tmp.right != null && lastVisited != tmp.right) {
            node = tmp.right;
          } else {
            result.add(tmp.value);
            lastVisited = stack.pop();
          }
        }
      } catch (Exception e) {
      }
    }

    return result;
  }

  /**
   * Search and get an element if it is within the tree.
   *
   * <p>Time complexity: O(logn) if the tree is balanced, O(n) in the worst case
   *
   * <p>Space complexity: O(1)
   *
   * @param node the root node where to start the search
   * @param value the element value to search
   * @return the node containing the element value, otherwise null
   */
  private BinaryNode<T> search(BinaryNode<T> node, T value) {
    if (node == null || node.value.compareTo(value) == 0) {
      return node;
    }
    if (node.value.compareTo(value) < 0) {
      return search(node.right, value);
    }
    return search(node.left, value);
  }

  /**
   * Search if an element is within the tree.
   *
   * <p>Time complexity: O(logn) if the tree is balanced, O(n) in the worst case
   *
   * <p>Space complexity: O(1)
   *
   * @param value the element value to search
   * @return true if the element is within the tree
   */
  public boolean contains(T value) {
    return search(root, value) != null;
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
   * Given the root, insert the new node.
   *
   * <p>Time complexity: O(logn) if the tree is balanced, O(n) in the worst case
   *
   * <p>Space complexity: O(1)
   *
   * @param node the root where to insert
   */
  private void insert(BinaryNode<T> node) {
    BinaryNode<T> y = null;
    BinaryNode<T> x = root;
    while (x != null) {
      y = x;
      if (node.value().compareTo(x.value()) < 0) {
        x = x.left();
      } else {
        x = x.right();
      }
    }
    node.setParent(y);
    if (y == null) {
      root = node;
    } else if (node.value().compareTo(y.value()) < 0) {
      y.left = node;
    } else {
      y.right = node;
    }
  }

  /**
   * Get the minimum element.
   *
   * <p>Time complexity: O(logn) if the tree is balanced, O(n) in the worst case
   *
   * <p>Space complexity: O(1)
   *
   * @return the minimum element
   */
  public BinaryNode<T> minimum() {
    return minimum(root);
  }

  /**
   * Get the maximum element.
   *
   * <p>Time complexity: O(logn) if the tree is balanced, O(n) in the worst case
   *
   * <p>Space complexity: O(1)
   *
   * @return the maximum element
   */
  public BinaryNode<T> maximum() {
    return maximum(root);
  }

  /**
   * Get the minimum element.
   *
   * <p>Time complexity: O(logn) if the tree is balanced, O(n) in the worst case
   *
   * <p>Space complexity: O(1)
   *
   * @param node the minimum in the rooted node
   * @return the minimum element
   */
  private BinaryNode<T> minimum(BinaryNode<T> node) {
    if (node == null || node.left == null) {
      return node;
    }
    return minimum(node.left);
  }

  /**
   * Get the maximum element.
   *
   * <p>Time complexity: O(logn) if the tree is balanced, O(n) in the worst case
   *
   * <p>Space complexity: O(1)
   *
   * @param node the maximum in the rooted node
   * @return the maximum element
   */
  private BinaryNode<T> maximum(BinaryNode<T> node) {
    if (node == null || node.right == null) {
      return node;
    }
    return maximum(node.right);
  }

  protected static class BinaryNode<T> {

    private T value;
    private BinaryNode<T> parent;
    private BinaryNode<T> left;
    private BinaryNode<T> right;

    BinaryNode(T value) {
      this.value = value;
    }

    protected void setValue(T value) {
      this.value = value;
    }

    public T value() {
      return this.value;
    }

    public BinaryNode<T> parent() {
      return this.parent;
    }

    public BinaryNode<T> left() {
      return this.left;
    }

    public BinaryNode<T> right() {
      return this.right;
    }

    protected void setParent(BinaryNode<T> parent) {
      this.parent = parent;
    }

    protected void setLeft(BinaryNode<T> left) {
      this.left = left;
    }

    protected void setRight(BinaryNode<T> right) {
      this.right = right;
    }

    public boolean equals(BinaryNode<T> b) {
      return this.value == b.value();
    }
  }
}
