package com.alexprut.algo.datastructures;

import java.util.ArrayList;

/**
 * A Fibonacci Heap is a collection of rooted trees that are min-heap ordered. That is, each tree
 * obeys the min-heap property: the key of a node is greater than or equal to the key of its parent.
 * Several Fibonacci Heap operations run in constant amortized time.
 *
 * <p>Example:
 *
 * <pre>
 *                 min
 *                  ↓
 * 23 ———— 7 ————   3    ———— 17 ——— 24
 *               /  |  \      |     /  \
 *             18  52  38    30    26  46
 *             |       |           |
 *            39      41          35
 * </pre>
 *
 * @see <a
 *     href="https://en.wikipedia.org/wiki/Fibonacci_heap">https://en.wikipedia.org/wiki/Fibonacci_heap</a>
 */
public class FibonacciHeap<T extends Comparable<T>> {

  /** The minimum node. */
  protected Node<T> min;

  /** The number of nodes in the heap. */
  protected int size = 0;

  /** Creates and returns a new heap containing no elements. */
  public FibonacciHeap() {}

  /**
   * Inserts element x, whose key has already been filled in.
   *
   * <p>Time complexity: O(1)
   *
   * <p>Space complexity: O(1)
   *
   * @param x value
   * @return the newly inserted node
   */
  public Node<T> insert(T x) {
    Node<T> node = new Node<>(x);

    if (min == null) {
      min = node;
    } else {
      if (min.leftSibling != null) {
        node.leftSibling = min;
        node.rightSibling = min.rightSibling;
        min.rightSibling = node;
        node.rightSibling.leftSibling = node;
      } else {
        min.leftSibling = node;
      }
      if (node.key.compareTo(min.key) < 0) {
        min = node;
      }
    }
    size++;
    return node;
  }

  /**
   * Returns the element in heap whose key is minimum.
   *
   * <p>Time complexity: O(1)
   *
   * <p>Space complexity: O(1)
   *
   * @return the minimum element
   */
  public T minimum() {
    return min.key;
  }

  /**
   * Deletes the element from heap whose key is minimum.
   *
   * <p>Time complexity: O(logn)
   *
   * <p>Space complexity: O(1)
   *
   * @return the minimum element
   */
  public Node<T> extractMin() {
    Node<T> z = min;
    if (z != null) {
      if (z.child != null) {
        Node<T> leftChild = z.child.leftSibling;
        Node<T> rightChild = z.child;
        z.child.parent = null;
        while (leftChild != rightChild) {
          leftChild.parent = null;
          leftChild = leftChild.leftSibling;
        }
        leftChild = leftChild.rightSibling;

        // add child to the root list
        Node<T> tmp = z.rightSibling;
        z.rightSibling = leftChild;
        leftChild.leftSibling = z;
        tmp.leftSibling = rightChild;
        rightChild.rightSibling = tmp;
      }

      // remove z from the root list
      z.rightSibling.leftSibling = z.leftSibling;
      z.leftSibling.rightSibling = z.rightSibling;

      if (z == z.rightSibling) {
        min = null;
      } else {
        min = z.rightSibling;
        consolidate();
      }

      size--;
    }
    return z;
  }

  /**
   * Helper method used in {@link #extractMin()}. Reduce the number of trees in the Fibonacci heap.
   * Consolidating the root list consists of repeatedly executing the following steps until every
   * root in the root list has a distinct degree value.
   *
   * <p>Time complexity: O(d(n)) where d is {@link #getDegreeBound(double)}
   *
   * <p>Space complexity: O(1)
   */
  protected void consolidate() {
    ArrayList<Node<T>> degrees = new ArrayList<>();
    for (int i = 0; i <= getDegreeBound(this.size); i++) {
      degrees.add(null);
    }
    ArrayList<Node<T>> rootList = new ArrayList<>();
    Node<T> root = min;
    Node<T> current = min.rightSibling;
    rootList.add(root);
    while (root != current) {
      rootList.add(current);
      current = current.rightSibling;
    }
    for (Node<T> x : rootList) {
      int d = x.degree;
      while (degrees.get(d) != null) {
        Node<T> y = degrees.get(d);
        if (x.key.compareTo(y.key) > 0) {
          Node<T> s = x;
          x = y;
          y = s;
        }
        link(y, x);
        degrees.set(d, null);
        d++;
      }
      degrees.set(d, x);
    }
    min = null;
    for (Node<T> degree : degrees) {
      if (degree != null) {
        if (min == null) {
          min = degree;
        } else {
          if (degree.key.compareTo(min.key) < 0) {
            min = degree;
          }
        }
      }
    }
  }

  /**
   * The upper bound on the maximum degree.
   *
   * <p>Time complexity: O(1)
   *
   * <p>Space complexity: O(1)
   *
   * @param n number of elements in the heap
   * @return the upper bound
   */
  protected int getDegreeBound(double n) {
    // The base should be the golden ratio: 1.61803...
    return (int) Math.floor(Math.log(n) / Math.log(1.6));
  }

  /**
   * Helper method used in {@link #consolidate()}. Links two nodes.
   *
   * <p>Time complexity: O(1)
   *
   * <p>Space complexity: O(1)
   *
   * @param y the first node
   * @param x the second node
   */
  protected void link(Node<T> y, Node<T> x) {
    // Remove y from the root list
    y.leftSibling.rightSibling = y.rightSibling;
    y.rightSibling.leftSibling = y.leftSibling;
    y.leftSibling = y;
    y.rightSibling = y;

    // Make y a child of x
    if (x.child == null) {
      x.child = y;
    } else {
      Node<T> child = x.child;
      y.leftSibling = child;
      y.rightSibling = child.rightSibling;
      child.rightSibling = y;
      y.rightSibling.leftSibling = y;
    }
    y.parent = x;
    x.degree++;
    y.mark = true;
  }

  /**
   * Assigns to element x within heap the new key value, which we assume to be no greater than its
   * current key value.
   *
   * <p>Time complexity: O(1)
   *
   * <p>Space complexity: O(1)
   *
   * @param x node to decrease value
   * @param k the new value
   * @return true if the key was decreased
   */
  public boolean decreaseKey(Node<T> x, T k) {
    if (x.key.compareTo(k) < 0) {
      return false;
    }
    x.key = k;
    Node<T> y = x.parent;
    if (y != null && x.key.compareTo(y.key) <= 0) {
      cut(x, y);
      cascadingCut(y);
    }
    if (x.key.compareTo(min.key) <= 0) {
      min = x;
    }

    return true;
  }

  /**
   * Helper method. “Cuts” the link between x and its parent y, making x a root.
   *
   * <p>Time complexity: O(1)
   *
   * <p>Space complexity: O(1)
   *
   * @param x the first node
   * @param y the second node
   */
  protected void cut(Node<T> x, Node<T> y) {
    // Remove x from the child list of y
    if (x.rightSibling == x) {
      y.child = null;
    } else {
      Node<T> rightSibling = x.rightSibling;
      Node<T> leftSibling = x.leftSibling;
      rightSibling.leftSibling = leftSibling;
      leftSibling.rightSibling = rightSibling;
      y.child = rightSibling;
    }
    y.degree--;

    // Add x to the root list
    min.rightSibling.leftSibling = x;
    x.rightSibling = min.rightSibling;
    x.leftSibling = min;
    min.rightSibling = x;

    x.parent = null;
    x.mark = false;
  }

  /**
   * Method recurse its way up the tree until it finds either a root or an unmarked node.
   *
   * <p>Time complexity: O(logn)
   *
   * <p>Space complexity: O(1)
   *
   * @param y the node to start the cut
   */
  protected void cascadingCut(Node<T> y) {
    Node<T> z = y.parent;
    if (z != null) {
      if (!y.mark) {
        y.mark = true;
      } else {
        cut(y, z);
        cascadingCut(z);
      }
    }
  }

  /**
   * Deletes element x from heap.
   *
   * <p>Time complexity: O(logn)
   *
   * <p>Space complexity: O(1)
   *
   * @param x the node to delete
   */
  public void delete(Node<T> x) {
    decreaseKey(x, min.key);
    extractMin();
  }

  /**
   * Get the size of the heap.
   *
   * <p>Time complexity: Θ(1)
   *
   * <p>Space complexity: Θ(1)
   *
   * @return the size of the heap
   */
  public int size() {
    return size;
  }

  public static class Node<T> {

    protected Node() {}

    protected T key;

    /** The number of children in the child list of node x */
    protected int degree = 0;

    /**
     * Indicates whether node x has lost a child since the last time x was made the child of another
     * node
     */
    protected boolean mark = false;

    protected Node<T> parent;

    protected Node<T> leftSibling = this;

    protected Node<T> rightSibling = this;

    protected Node<T> child;

    Node(T value) {
      key = value;
    }

    public T getKey() {
      return this.key;
    }

    public Node<T> getParent() {
      return this.parent;
    }
  }
}
