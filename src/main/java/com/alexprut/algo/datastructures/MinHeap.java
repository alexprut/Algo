package com.alexprut.algo.datastructures;

import com.alexprut.algo.Utils;

/**
 * @see <a
 *     href="https://en.wikipedia.org/wiki/Min-max_heap">https://en.wikipedia.org/wiki/Min-max_heap</a>
 */
public class MinHeap extends BinaryHeap {

  public MinHeap() {}

  public MinHeap(int[] elements) {
    super(elements);
  }

  /** Time complexity: Θ(1) */
  public int min() {
    return root();
  }

  /** Time complexity: O(logn) */
  public int extractMin() throws Exception {
    return extract();
  }

  /** Time complexity: O(logn) */
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
    while (parent(k) >= 0 && elements[parent(k)] > e) {
      int tmp = elements[parent(k)];
      elements[parent(k)] = e;
      elements[k] = tmp;
      k = parent(k);
    }
  }

  /** Time complexity: O(logn) */
  public void heapify(int i) {
    int left = left(i);
    int right = right(i);
    int smallest = i;

    if (left < size && elements[left] < elements[i]) {
      smallest = left;
    }

    if (right < size && elements[right] < elements[smallest]) {
      smallest = right;
    }

    if (smallest != i) {
      int tmp = elements[smallest];
      elements[smallest] = elements[i];
      elements[i] = tmp;
      heapify(smallest);
    }
  }

  /**
   * Time complexity: O(nlogn)
   *
   * <p>Space complexity: algorithms is in-place
   */
  public void heapsort() {
    super.heapsort();
    for (int i = 0; i < size / 2; i++) {
      Utils.swap(elements, i, size - 1 - i);
    }
  }
}
