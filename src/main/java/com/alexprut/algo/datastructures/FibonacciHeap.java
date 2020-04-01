package com.alexprut.algo.datastructures;

import java.util.HashMap;

/**
 * A Fibonacci Heap is a collection of rooted trees that are min-heap ordered. That is, each tree
 * obeys the min-heap property: the key of a node is greater than or equal to the key of its parent.
 */
public class FibonacciHeap<T extends Comparable> {

  /** The minimum node */
  Node<T> min;

  /** The number of nodes in the heap */
  int size;

  /** Creates and returns a new heap containing no elements. */
  public FibonacciHeap() {
    size = 0;
  }

  /**
   * Inserts element x, whose key has already been filled in, into heap.
   *
   * <p>Time complexity: O(1)
   *
   * @param x value
   */
  public void insert(T x) {
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
      if (z == z.rightSibling) {
        min = null;
      } else {
        Node<T> leftChild = z.child.leftSibling;
        Node<T> rightChild = z.child;
        z.child.parent = null;
        while (leftChild != rightChild) {
          leftChild.parent = null;
          if (leftChild.leftSibling != rightChild) {
            leftChild = leftChild.leftSibling;
          }
        }

        rightChild.rightSibling = z.rightSibling;
        z.rightSibling.leftSibling = rightChild;
        leftChild.leftSibling = z.leftSibling;
        z.leftSibling.rightSibling = leftChild;

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
    HashMap<Integer, Node<T>> map = new HashMap<>();
    Node<T> start = min.rightSibling;
    map.put(min.degree, min);
    while (start != min) {}
  }

  protected void link() {}

  /**
   * Creates and returns a new heap that contains all the elements of heaps H1 and H2. Heaps H1 and
   * H2 are “destroyed” by this operation.
   *
   * <p>Time complexity: O(1)
   */
  public void union(Node<T> a, Node<T> b) {
    this.min = a;
    // Concatenate the root list of H2 with the root list of H
    if (a == null || (b != null && b.key.compareTo(a.key) < 0)) {
      this.min = b;
    }
  }

  /**
   * Assigns to element x within heap H the new key value k, which we assume to be no greater than
   * its current key value. TODO add reference to element as input
   *
   * <p>Time complexity: O(1)
   */
  public void decreaseKey() {}

  /**
   * Deletes element x from heap TODO add reference to element as input
   *
   * <p>Time complexity: O(logn)
   */
  public void delete(int x) {}

  /**
   * Search element x in heap
   *
   * <p>Time complexity: TODO
   */
  public void search(int x) {}

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
