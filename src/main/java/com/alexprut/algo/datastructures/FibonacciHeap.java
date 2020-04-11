package com.alexprut.algo.datastructures;

import java.util.ArrayList;

/**
 * A Fibonacci Heap is a collection of rooted trees that are min-heap ordered. That is, each tree
 * obeys the min-heap property: the key of a node is greater than or equal to the key of its parent.
 */
public class FibonacciHeap<T extends Comparable> {

  /** The minimum node */
  protected Node<T> min;

  /** The number of nodes in the heap */
  protected int size = 0;

  /** Creates and returns a new heap containing no elements. */
  public FibonacciHeap() {}

  /**
   * Inserts element x, whose key has already been filled in, into heap.
   *
   * <p>Time complexity: O(1)
   *
   * @param x value
   */
  public Node<T> insert(T x) {
    Node<T> node = new Node<>(x);

    if (min == null) {
      min = node;
    } else {
      if (min.leftSibling != null) {
        Node<T> tmp = min.leftSibling;
        node.rightSibling = min;
        node.leftSibling = tmp;
        min.leftSibling = node;
        tmp.rightSibling = node;
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
   */
  public T minimum() {
    return min.key;
  }

  /**
   * Deletes the element from heap whose key is minimum.
   *
   * <p>Time complexity: O(logn)
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

        rightChild.rightSibling = z.rightSibling;
        z.rightSibling.leftSibling = rightChild;
        leftChild.leftSibling = z.leftSibling;
        z.leftSibling.rightSibling = leftChild;
      }

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
   * Reduce the number of trees in the Fibonacci heap. Consolidating the root list consists of
   * repeatedly executing the following steps until every root in the root list has a distinct
   * degree value.
   */
  protected void consolidate() {
    Node<T>[] degrees = (Node<T>[]) new Object[getDegreeBound(this.size)];
    ArrayList<Node<T>> rootList = new ArrayList<>();
    Node<T> root = min;
    Node<T> current = min.rightSibling;
    rootList.add(root);
    while (root != current) {
      rootList.add(current);
      current = current.rightSibling;
    }
    for (int i = 0; i < rootList.size(); i++) {
      Node<T> x = rootList.get(i);
      int d = x.degree;
      while (degrees[d] != null) {
        Node<T> y = degrees[d];
        if (x.key.compareTo(y.key) > 0) {
          Node<T> s = x;
          x = y;
          y = s;
        }
        link(y, x);
        d++;
      }
      degrees[d] = x;
    }
    min = null;
    for (int i = 0; i < degrees.length; i++) {
      if (degrees[i] != null) {
        if (min == null) {
          min = degrees[i];
        } else {
          if (degrees[i].key.compareTo(min.key) < 0) {
            min = degrees[i];
          }
        }
      }
    }
  }

  /**
   * Time complexity: O(1)
   *
   * @param n number of elements in the heap
   * @return the upper bound
   */
  protected int getDegreeBound(double n) {
    // the base should be the golden ratio: 1.61803...
    return (int) Math.floor(Math.log(n) / Math.log(1.6));
  }

  protected void link(Node<T> y, Node<T> x) {
    // remove y from the root list
    y.leftSibling.rightSibling = y.rightSibling;
    y.rightSibling.leftSibling = y.leftSibling;

    // make y a child of x
    Node<T> child = x.child;
    y.leftSibling = child;
    y.rightSibling = child.rightSibling;
    child.rightSibling = y;
    y.rightSibling.leftSibling = y;

    x.degree++;
    y.mark = true;
  }

  /**
   * Assigns to element x within heap H the new key value k, which we assume to be no greater than
   * its current key value.
   *
   * <p>Time complexity: O(1)
   *
   * @param x node to decrease value
   * @param k the new value
   */
  public boolean decreaseKey(Node<T> x, T k) {
    if (x.key.compareTo(k) < 0) {
      return false;
    }
    x.key = k;
    Node<T> y = x.parent;
    if (y != null && x.key.compareTo(y.key) < 0) {
      cut(x, y);
      cascadingCut(y);
    }
    if (x.key.compareTo(min.key) <= 0) {
      min = x;
    }

    return true;
  }

  /** */
  protected void cut(Node<T> x, Node<T> y) {
    // remove x from the child list of y
    if (x.rightSibling == x) {
      y.child = null;
    } else {
      x.rightSibling.leftSibling = x.leftSibling;
      x.leftSibling.rightSibling = x.rightSibling;
    }
    y.degree--;

    // add x to the root list
    min.rightSibling.leftSibling = x;
    x.rightSibling = min.rightSibling;
    x.leftSibling = min;
    min.rightSibling = x;

    x.parent = null;
    x.mark = false;
  }

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
   * Deletes element x from heap
   *
   * <p>Time complexity: O(logn)
   */
  public void delete(Node<T> x) {
    decreaseKey(x, min.key);
    extractMin();
  }

  protected static class Node<T> {

    protected T key;

    /** The number of children in the child list of node x in x:degree */
    protected int degree = 0;

    /**
     * indicates whether node x has lost a child since the last time x was made the child of another
     * node
     */
    protected boolean mark = false;

    protected Node<T> parent;

    /** If node y is an only child, then y:left D y:right D y */
    protected Node<T> leftSibling = this;

    protected Node<T> rightSibling = this;

    protected Node<T> child;

    Node(T value) {
      key = value;
    }
  }
}
