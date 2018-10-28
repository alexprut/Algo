package com.alexprut.algo.datastructures;

public class MaxHeap extends BinaryHeap {

  MaxHeap() {}

  MaxHeap(int[] elements) {
    super(elements);
  }

  public int max() {
    return root();
  }

  public int extractMax() throws Exception {
    return extract();
  }

  /** Time complexity: O(logn) */
  public void increaseKey(int i, int value) throws Exception {
    if (elements[i] >= value) {
      throw new Exception("The new key is smaller than the current key");
    }

    elements[i] = value;
    while (parent(i) >= 0 && elements[parent(i)] < elements[i]) {
      int tmp = elements[i];
      elements[i] = elements[parent(i)];
      elements[parent(i)] = tmp;
      i = parent(i);
    }
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
    elements[k] = Integer.MIN_VALUE;
    try {
      increaseKey(k, e);
    } catch (Exception exeption) {

    }
  }

  /** Time complexity: O(logn) */
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
   * <p>Space complexity: algorithms is in-place
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
