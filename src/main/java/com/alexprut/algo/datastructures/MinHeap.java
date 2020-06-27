package com.alexprut.algo.datastructures;

import com.alexprut.algo.Utils;

/**
 * The binary min heap data structure is an array object that we can view as a nearly complete
 * binary tree. Each node of the tree corresponds to an element of the array. The data structure
 * provides constant time retrieval and logarithmic time removal of the minimum elements in it. The
 * min heap property: each parent node if less than it's child nodes.
 *
 * <p>Example:
 *
 * <pre>
 * Array visualization:
 * [1,3,2,6,5,4]
 *
 * Tree visualization:
 *       1
 *    /    \
 *   3      2
 *  / \    /
 * 6   5  4
 * </pre>
 *
 * @see <a
 *     href="https://en.wikipedia.org/wiki/Min-max_heap">https://en.wikipedia.org/wiki/Min-max_heap</a>
 */
public class MinHeap extends BinaryHeap {

  public MinHeap() {}

  public MinHeap(int[] elements) {
    super(elements);
  }

  /**
   * Get the minimum element.
   *
   * <p>Time complexity: Θ(1)
   *
   * <p>Space complexity: Θ(1)
   *
   * @return the minimum element
   */
  public int min() {
    return root();
  }

  /**
   * Get and remove the minimum element.
   *
   * <p>Time complexity: O(logn)
   *
   * <p>Space complexity: O(1)
   *
   * @return the minimum element
   * @throws Exception if the heap is empty
   */
  public int extractMin() throws Exception {
    return extract();
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
    elements[k] = e;
    while (parent(k) >= 0 && elements[parent(k)] > e) {
      int tmp = elements[parent(k)];
      elements[parent(k)] = e;
      elements[k] = tmp;
      k = parent(k);
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
   * Sorts an array in place.
   *
   * <p>Time complexity: O(nlogn)
   *
   * <p>Space complexity: O(1), algorithms is in-place
   *
   * @see <a
   *     href="https://en.wikipedia.org/wiki/Heapsort">https://en.wikipedia.org/wiki/Heapsort</a>
   */
  public void heapsort() {
    super.heapsort();
    for (int i = 0; i < size / 2; i++) {
      Utils.swap(elements, i, size - 1 - i);
    }
  }
}
