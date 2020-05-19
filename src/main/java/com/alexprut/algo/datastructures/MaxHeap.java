package com.alexprut.algo.datastructures;

/**
 * @see <a
 *     href="https://en.wikipedia.org/wiki/Min-max_heap">https://en.wikipedia.org/wiki/Min-max_heap</a>
 */
public class MaxHeap extends BinaryHeap {

  public MaxHeap() {}

  public MaxHeap(int[] elements) {
    super(elements);
  }

  /**
   * Time complexity: Θ(1)
   *
   * @return
   */
  public int max() {
    return root();
  }

  /**
   * Time complexity: O(logn)
   *
   * @return
   * @throws Exception
   */
  public int extractMax() throws Exception {
    return extract();
  }

  /**
   * Time complexity: O(logn)
   *
   * @param i
   * @param value
   * @throws Exception
   */
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

  /**
   * Time complexity: O(logn)
   *
   * @param e
   */
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
    } catch (Exception exception) {

    }
  }

  /**
   * Time complexity: O(logn)
   *
   * @param i
   */
  public void heapify(int i) {
    int left = left(i);
    int right = right(i);
    int largest = i;

    if (left < size && elements[left] > elements[i]) {
      largest = left;
    }

    if (right < size && elements[right] > elements[largest]) {
      largest = right;
    }

    if (largest != i) {
      int tmp = elements[largest];
      elements[largest] = elements[i];
      elements[i] = tmp;
      heapify(largest);
    }
  }
}
