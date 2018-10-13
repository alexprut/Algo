package com.alexprut.algo.datastructures;

public class MaxHeap extends BinaryHeap {

  private int[] elements;
  private int size = 0;

  MaxHeap() {
  }

  MaxHeap(int[] elements) {
    super(elements);
  }

  public int max() {
    return root();
  }

  public void insert(int e) {
    size++;
    if (elements.length < size) {
      int[] tmp = new int[elements.length * 2];
      for (int i = 0; i < elements.length; i++) {
        tmp[i] = elements[i];
      }
      elements = tmp;
    }
    int k = size - 1;
    elements[k] = e;
    while (parent(k) >= 0 && elements[parent(k)] < e) {
      int tmp = elements[parent(k)];
      elements[parent(k)] = e;
      elements[k] = tmp;
      k = parent(k);
    }
  }

  /**
   * Time complexity: O(logn)
   */
  public void heapify(int i) {
    int left = left(i);
    int right = right(i);
    int largest = i;

    if (left < size && elements[left] > elements[i]) {
      largest = left;
    }

    if (right < size && elements[right] > elements[i]) {
      largest = right;
    }

    if (largest != i) {
      int tmp = elements[largest];
      elements[largest] = elements[i];
      elements[i] = tmp;
      heapify(largest);
    }
  }

  /**
   * Time complexity: O(nlogn)
   *
   * Space complexity:
   * algorithms is in-place
   */
  public void heapsort() {
    for (int i = size - 1; i >= 1; i--) {
      int tmp = elements[0];
      elements[0] = elements[i];
      elements[i] = tmp;
      size--;
      heapify(0);
    }
  }
}
