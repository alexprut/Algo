package com.alexprut.algo.datastructures;

/**
 * The binary heap data structure is an array object that we can view as a nearly complete binary
 * tree. Each node of the tree corresponds to an element of the array. This data structure makes it
 * easy to implement priority queues. Used for implementing {@link MinHeap} and {@link MaxHeap}.
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
 *     href="https://en.wikipedia.org/wiki/Binary_heap">https://en.wikipedia.org/wiki/Binary_heap</a>
 */
public abstract class BinaryHeap {

  protected int[] elements = new int[0];
  protected int size = 0;

  public BinaryHeap(int[] elements) {
    this.elements = elements;
    size = elements.length;
    build();
  }

  BinaryHeap() {}

  /**
   * Get the parent index.
   *
   * <p>Time complexity: Θ(1)
   *
   * <p>Space complexity: Θ(1)
   *
   * @param i the current index
   * @return the parent index
   */
  protected static int parent(int i) {
    return (i + 1) / 2 - 1;
  }

  /**
   * Given the parent index calculates the index of the left child.
   *
   * <p>Time complexity: O(1)
   *
   * <p>Space complexity: O(1)
   *
   * @param i parent index
   * @return index of the left child
   */
  protected static int left(int i) {
    return 2 * i + 1;
  }

  /**
   * Given the parent index calculates the index of the right child.
   *
   * <p>Time complexity: O(1)
   *
   * <p>Space complexity: O(1)
   *
   * @param i parent index
   * @return index of the right child
   */
  protected static int right(int i) {
    return 2 * i + 2;
  }

  /**
   * Get the root element.
   *
   * <p>Time complexity: Θ(1)
   *
   * <p>Space complexity: Θ(1)
   *
   * @return the root element
   */
  public int root() {
    return elements[0];
  }

  /**
   * Extract the root element.
   *
   * <p>Time complexity: O(logn)
   *
   * <p>Space complexity: O(1)
   *
   * @return the root element
   * @throws Exception if the heap is empty
   */
  public int extract() throws Exception {
    if (size < 1) {
      throw new Exception("Heap underflow");
    }

    int extracted = elements[0];
    elements[0] = elements[size - 1];
    size--;
    heapify(0);
    return extracted;
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
  protected abstract void heapify(int i);

  /**
   * Build the heap.
   *
   * <p>Time complexity: O(n)
   *
   * <p>Space complexity: O(1)
   */
  protected void build() {
    for (int i = (size - 1) / 2; i >= 0; i--) {
      heapify(i);
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
  public abstract void insert(int e);

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

  /**
   * Get the array of elements.
   *
   * <p>Time complexity: Θ(1)
   *
   * <p>Space complexity: Θ(1)
   *
   * @return the array of elements
   */
  protected int[] elements() {
    int[] tmp = new int[size];
    if (size >= 0) System.arraycopy(elements, 0, tmp, 0, size);
    return tmp;
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
    for (int i = size - 1; i >= 1; i--) {
      int tmp = elements[0];
      elements[0] = elements[i];
      elements[i] = tmp;
      size--;
      heapify(0);
    }
    size = elements.length;
  }
}
