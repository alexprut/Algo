package com.alexprut.algo.datastructures;

import java.util.ArrayList;

/**
 * @see <a
 *     href="https://en.wikipedia.org/wiki/Binary_search_tree">https://en.wikipedia.org/wiki/Binary_search_tree</a>
 * @param <T>
 */
public class BinarySearchTree<T extends Comparable<T>> {

  private BinaryNode<T> root;
  private int size;

  public BinarySearchTree() {
    size = 0;
  }

  /**
   * Time complexity: O(logn) if the tree is balanced, O(n) in the worst case
   *
   * @param value
   */
  public void insert(T value) {
    BinaryNode node = new BinaryNode<T>(value);

    if (root == null) {
      root = node;
    } else {
      insert(node);
    }
    size++;
  }

  /**
   * Time complexity: O(logn) if the tree is balanced, O(n) in the worst case
   *
   * @param value
   * @return
   */
  public BinaryNode<T> search(T value) {
    return search(root, value);
  }

  /**
   * Time complexity: O(logn) if the tree is balanced, O(n) in the worst case
   *
   * @param node
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
   * Replaces the subtree rooted at node `u` with the subtree rooted at node `v`
   *
   * <p>Time complexity: O(1)
   *
   * @param u
   * @param v
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
   *
   * @param x
   * @return
   */
  protected BinaryNode<T> successor(BinaryNode<T> x) {
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

  /**
   * Time complexity: O(logn) if the tree is balanced, O(n) in the worst case
   *
   * @return
   */
  public ArrayList<T> preOrderVisit() {
    if (root == null) {
      return new ArrayList<>();
    }
    ArrayList<T> result = new ArrayList<>();
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
   * Time complexity: O(logn) if the tree is balanced, O(n) in the worst case
   *
   * @return
   */
  public ArrayList<T> inOrderVisit() {
    if (root == null) {
      return new ArrayList<>();
    }
    BinaryNode<T> node = root;
    ArrayList<T> result = new ArrayList<>();
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
   * Time complexity: O(logn) if the tree is balanced, O(n) in the worst case
   *
   * @return
   */
  public ArrayList<T> postOrderVisit() {
    if (root == null) {
      return new ArrayList<>();
    }
    ArrayList<T> result = new ArrayList<>();
    Stack<BinaryNode<T>> stack = new Stack<>();
    BinaryNode node = root;
    BinaryNode lastVisited = null;
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
   * TODO interactive
   *
   * <p>Time complexity: O(logn) if the tree is balanced, O(n) in the worst case
   *
   * @param node
   * @param value
   * @return
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
   * Time complexity: O(logn) if the tree is balanced, O(n) in the worst case
   *
   * @param value
   * @return
   */
  public boolean contains(T value) {
    return search(root, value) != null;
  }

  /**
   * Time complexity: Θ(1)
   *
   * @return
   */
  public int size() {
    return this.size;
  }

  /**
   * Time complexity: O(logn) if the tree is balanced, O(n) in the worst case
   *
   * @param node
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
   * Time complexity: O(logn) if the tree is balanced, O(n) in the worst case
   *
   * @return
   */
  public BinaryNode<T> minimum() {
    return minimum(root);
  }

  /**
   * Time complexity: O(logn) if the tree is balanced, O(n) in the worst case
   *
   * @return
   */
  public BinaryNode<T> maximum() {
    return maximum(root);
  }

  /**
   * Time complexity: O(logn) if the tree is balanced, O(n) in the worst case TODO interactive
   *
   * @param node
   * @return
   */
  private BinaryNode<T> minimum(BinaryNode<T> node) {
    if (node == null || node.left == null) {
      return node;
    }
    return minimum(node.left);
  }

  /**
   * Time complexity: O(logn) if the tree is balanced, O(n) in the worst case TODO interactive
   *
   * @param node
   * @return
   */
  private BinaryNode<T> maximum(BinaryNode<T> node) {
    if (node == null || node.right == null) {
      return node;
    }
    return maximum(node.right);
  }

  public static class BinaryNode<T> {

    private T value;
    private BinaryNode<T> parent;
    private BinaryNode<T> left;
    private BinaryNode<T> right;

    BinaryNode(T value) {
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

    public void setParent(BinaryNode<T> parent) {
      this.parent = parent;
    }

    public void setLeft(BinaryNode<T> left) {
      this.left = left;
    }

    public void setRight(BinaryNode<T> right) {
      this.right = right;
    }

    public boolean equals(BinaryNode<T> b) {
      return this.value == b.value();
    }
  }
}
