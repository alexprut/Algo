package com.alexprut.algo.datastructures;

import com.alexprut.algo.Utils;

/**
 * A MinMaxHeap is a data structure that combines both the {@link MinHeap} and {@link MaxHeap}
 * properties. It provides constant time retrieval and logarithmic time removal of both the minimum
 * and maximum elements in it. Like binary min heaps and max heaps, min-max heaps support
 * logarithmic insertion and deletion and can be built in linear time. The min-max heap property is:
 * each node at an even level in the tree is less than all of its descendants, while each node at an
 * odd level in the tree is greater than all of its descendants.
 *
 * <p>Example:
 *
 * <pre>
 * Array visualization:
 * [8,71,41,31,10,11,16,46,51,31,21,13]
 *
 * Tree visualization:
 *               8
 *          /          \
 *        71            41
 *     /     \        /    \
 *    31     10      11    16
 *   / \    /  \    /
 * 46  51  31  21  13
 * </pre>
 *
 * @see <a
 *     href="https://en.wikipedia.org/wiki/Min-max_heap">https://en.wikipedia.org/wiki/Min-max_heap</a>
 */
public class MinMaxHeap {

  protected int[] elements;
  protected int size = 0;

  public MinMaxHeap(int[] elements) {
    this.elements = elements;
    size = elements.length;
    build();
  }

  public MinMaxHeap() {
    elements = new int[0];
  }

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
   * Given the current index calculates the level in the tree.
   *
   * <p>Time complexity: O(1)
   *
   * <p>Space complexity: O(1)
   *
   * @param i current index
   * @return level in the tree
   */
  protected static int level(int i) {
    return (int) Math.floor(Math.log(i + 1) / Math.log(2));
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
   * Fixes and maintains the heap property.
   *
   * <p>Time complexity: O(logn)
   *
   * <p>Space complexity: O(1)
   *
   * @param i the index to apply the fix
   */
  protected void heapify(int i) {
    if (level(i) % 2 == 0) {
      heapifyMin(i);
    } else {
      heapifyMax(i);
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
  protected void heapifyMin(int i) {
    if (left(i) < size) {
      // Check for children
      int index = left(i);
      int min = elements[left(i)];
      if (right(i) < size && elements[right(i)] < min) {
        min = elements[right(i)];
        index = right(i);
      }
      // Check for grandchildren
      boolean isGrandChildrenMin = false;
      if (left(left(i)) < size && elements[left(left(i))] < min) {
        min = elements[left(left(i))];
        index = left(left(i));
        isGrandChildrenMin = true;
      }
      if (left(right(i)) < size && elements[left(right(i))] < min) {
        min = elements[left(right(i))];
        index = left(right(i));
        isGrandChildrenMin = true;
      }
      if (right(left(i)) < size && elements[right(left(i))] < min) {
        min = elements[right(left(i))];
        index = right(left(i));
        isGrandChildrenMin = true;
      }
      if (right(right(i)) < size && elements[right(right(i))] < min) {
        min = elements[right(right(i))];
        index = right(right(i));
        isGrandChildrenMin = true;
      }
      if (isGrandChildrenMin) {
        if (elements[index] < elements[i]) {
          Utils.swap(elements, index, i);
          if (elements[index] > elements[parent(index)]) {
            Utils.swap(elements, index, parent(index));
          }
          heapifyMin(index);
        }
      } else if (elements[index] < elements[i]) {
        Utils.swap(elements, index, i);
      }
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
  protected void heapifyMax(int i) {
    if (left(i) < size) {
      // Check for children
      int index = left(i);
      int max = elements[left(i)];
      if (right(i) < size && elements[right(i)] > max) {
        max = elements[right(i)];
        index = right(i);
      }
      // Check for grandchildren
      boolean isGrandChildrenMin = false;
      if (left(left(i)) < size && elements[left(left(i))] > max) {
        max = elements[left(left(i))];
        index = left(left(i));
        isGrandChildrenMin = true;
      }
      if (left(right(i)) < size && elements[left(right(i))] > max) {
        max = elements[left(right(i))];
        index = left(right(i));
        isGrandChildrenMin = true;
      }
      if (right(left(i)) < size && elements[right(left(i))] > max) {
        max = elements[right(left(i))];
        index = right(left(i));
        isGrandChildrenMin = true;
      }
      if (right(right(i)) < size && elements[right(right(i))] > max) {
        max = elements[right(right(i))];
        index = right(right(i));
        isGrandChildrenMin = true;
      }
      if (isGrandChildrenMin) {
        if (elements[index] > elements[i]) {
          Utils.swap(elements, index, i);
          if (elements[index] < elements[parent(index)]) {
            Utils.swap(elements, index, parent(index));
          }
          heapifyMin(index);
        }
      } else if (elements[index] > elements[i]) {
        Utils.swap(elements, index, i);
      }
    }
  }

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
    if (size >= 0) {
      System.arraycopy(elements, 0, tmp, 0, size);
    }
    return tmp;
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
      int[] tmp = new int[elements.length * 2 + 1];
      System.arraycopy(elements, 0, tmp, 0, elements.length);
      elements = tmp;
    }
    int i = size - 1;
    elements[i] = e;

    if (i != 0) {
      if (level(i) % 2 == 0) {
        // Min level
        if (elements[i] > elements[parent(i)]) {
          Utils.swap(elements, i, parent(i));
          pushUpMax(parent(i));
        } else {
          pushUpMin(i);
        }
      } else {
        // Max level
        if (elements[i] < elements[parent(i)]) {
          Utils.swap(elements, i, parent(i));
          pushUpMin(parent(i));
        } else {
          pushUpMax(i);
        }
      }
    }
  }

  public void pushUpMin(int i) {
    if (parent(parent(i)) >= 0 && elements[i] < elements[parent(parent(i))]) {
      Utils.swap(elements, i, parent(parent(i)));
      pushUpMin(parent(parent(i)));
    }
  }

  public void pushUpMax(int i) {
    if (parent(parent(i)) >= 0 && elements[i] > elements[parent(parent(i))]) {
      Utils.swap(elements, i, parent(parent(i)));
      pushUpMax(parent(parent(i)));
    }
  }

  /**
   * Get and remove the minimum element.
   *
   * <p>Time complexity: O(logn)
   *
   * <p>Space complexity: O(1)
   *
   * @return the minimum element
   */
  public int extractMin() {
    int extracted = elements[0];
    elements[0] = elements[size - 1];
    size--;
    heapify(0);
    return extracted;
  }

  /**
   * Get and remove the minimum element.
   *
   * <p>Time complexity: O(logn)
   *
   * <p>Space complexity: O(1)
   *
   * @return the minimum element
   */
  public int extractMax() {
    int index = 0;
    if (left(0) < size) {
      index = left(0);
      if (right(0) < size && elements[left(0)] < elements[right(0)]) {
        index = right(0);
      }
    }

    int extracted = elements[index];
    elements[index] = elements[size - 1];
    size--;
    heapify(index);
    return extracted;
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
   * Get the maximum element.
   *
   * <p>Time complexity: Θ(1)
   *
   * <p>Space complexity: Θ(1)
   *
   * @return the maximum element
   */
  public int max() {
    if (size == 1) {
      return root();
    }
    if (size == 2) {
      return Math.max(root(), elements[left(0)]);
    }

    return Math.max(elements[left(0)], elements[right(0)]);
  }
}
