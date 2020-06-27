package com.alexprut.algo.datastructures;

/**
 * The binary max heap data structure is an array object that we can view as a nearly complete
 * binary tree. Each node of the tree corresponds to an element of the array. The data structure
 * provides constant time retrieval and logarithmic time removal of the maximum elements in it. The
 * max heap property: each parent node if greater than it's child nodes.
 *
 * <p>Example:
 *
 * <pre>
 * Array visualization:
 * [7,5,6,2,1,6]
 *
 * Tree visualization:
 *       7
 *    /    \
 *   5      6
 *  / \    /
 * 2   1  4
 * </pre>
 *
 * @see <a
 *     href="https://en.wikipedia.org/wiki/Min-max_heap">https://en.wikipedia.org/wiki/Min-max_heap</a>
 */
public class MaxHeap extends BinaryHeap {

  public MaxHeap() {}

  public MaxHeap(int[] elements) {
    super(elements);
  }

  /**
   * Get the maximum element.
   *
   * <p>Time complexity: Θ(1)
   *
   * <p>Space complexity: Θ(1)
   *
   * @return the maximum element
   */
  public int max() {
    return root();
  }

  /**
   * Get and remove the maximum element.
   *
   * <p>Time complexity: O(logn)
   *
   * <p>Space complexity: O(1)
   *
   * @return the maximum element
   * @throws Exception if the heap is empty
   */
  public int extractMax() throws Exception {
    return extract();
  }

  /**
   * Increases the key value of an element.
   *
   * <p>Time complexity: O(logn)
   *
   * <p>Space complexity: O(logn)
   *
   * @param i the element index in the array
   * @param value the new value
   * @throws Exception if the element value is smaller
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
   * Insert a new element in the heap.
   *
   * <p>Time complexity: O(logn)
   *
   * <p>Space complexity: O(1)
   *
   * @param e the new element
   */
  public void insert(int e) {
    size++;
    if (elements.length < size) {
      int[] tmp = new int[elements.length * 2];
      System.arraycopy(elements, 0, tmp, 0, elements.length);
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
   * Fixes and maintains the heap property.
   *
   * <p>Time complexity: O(logn)
   *
   * <p>Space complexity: O(1)
   *
   * @param i the index to apply the fix
   */
  protected void heapify(int i) {
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
