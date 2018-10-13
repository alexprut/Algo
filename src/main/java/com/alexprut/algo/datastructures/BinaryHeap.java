package com.alexprut.algo.datastructures;

public abstract class BinaryHeap {

  private int[] elements;
  private int size = 0;

  BinaryHeap(int[] elements) {
    this.elements = elements;
    size = elements.length;
    build();
  }

  BinaryHeap() {
  }

  public int parent(int i) {
    return i / 2;
  }

  public int left(int i) {
    return 2 * i;
  }

  public int right(int i) {
    return 2 * i + 1;
  }

  public int root() {
    return elements[0];
  }

  /**
   * Time complexity: O(logn)
   */
  public abstract void heapify(int i);

  /**
   * Time complexity: O(n)
   */
  public void build() {
    for (int i = (size - 1) / 2; i >= 0; i--) {
      heapify(i);
    }
  }

  /**
   * Time complexity: O(logn)
   */
  public abstract void insert(int e);

  public int size() {
    return size;
  }

  public int[] elements() {
    int[] tmp = new int[size];
    for (int i = 0; i < size; i++) {
      tmp[i] = elements[i];
    }
    return tmp;
  }

  /**
   * Time complexity: O(nlogn)
   *
   * Space complexity:
   * algorithms is in-place
   */
  public abstract void heapsort();
}