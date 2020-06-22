package com.alexprut.algo.datastructures;

/**
 * A tree data structure for storing intervals or segments. Also knows as static tree, it's a
 * structure that cannot be modified once it's built. It allows querying efficiently which of the
 * stored segments contain a given point.
 *
 * <p>Example: [8,7,3,9,3]
 *
 * <pre>
 *    x
 *  i:[a,b]
 *
 *  x = the element
 *  i = index in the data structure
 *  a = start interval
 *  b = end interval
 *
 *                     3
 *                   1:[0,4]
 *               /             \
 *            3                  3
 *          2:[0,2]            3:[3,4]
 *         /      \           /       \
 *        7        3         9         3
 *      4:[0,1]   5:[2,2]  6:[3,3]   7:[4,4]
 *    /       \
 *   8         7
 * 8:[0,0]   9:[1,1]
 * </pre>
 *
 * @see <a
 *     href="https://en.wikipedia.org/wiki/Segment_tree">https://en.wikipedia.org/wiki/Segment_tree</a>
 */
abstract class SegmentTree {

  private final int[] elements;
  private final int[] tree;

  public SegmentTree(int[] arr) {
    elements = arr;
    int x = (int) (Math.ceil(Math.log(elements.length) / Math.log(2)));
    tree = new int[2 * (int) Math.pow(2, x) - 1];
    build(0, elements.length - 1, 0);
  }

  /**
   * Build the Segment tree.
   *
   * <p>Time complexity: O(n)
   *
   * <p>Space complexity: O(1)
   *
   * @param startInterval the start of the interval
   * @param endInterval the end of the interval
   * @param current current index
   * @return the current value
   */
  private int build(int startInterval, int endInterval, int current) {
    if (startInterval == endInterval) {
      tree[current] = elements[startInterval];
      return elements[startInterval];
    }

    int middle = middle(startInterval, endInterval);
    tree[current] =
        calculate(
            build(startInterval, middle, left(current)),
            build(middle + 1, endInterval, right(current)));
    return tree[current];
  }

  /**
   * Search within the specified interval for the element containing the desired property, see
   * {@link #calculate(int, int)} method (e.g. for a {@link MinSegmentTree} it will be the minimum
   * element).
   *
   * <p>Example: given the array [8,7,3,9,3], the minimum element in the interval [0,1] is 7.
   *
   * <p>Time complexity: O(logn)
   *
   * <p>Space complexity: O(1)
   *
   * @param startInterval the starting interval to search
   * @param endInterval the ending interval to search
   * @return the element containing the property in the specified interval
   */
  public int search(int startInterval, int endInterval) {
    return search(0, elements.length - 1, startInterval, endInterval, 0);
  }

  /**
   * See {@link #search(int, int)}.
   *
   * <p>Time complexity: O(logn)
   *
   * <p>Space complexity: O(1)
   *
   * @param start temporary start index
   * @param end temporary end index
   * @param startInterval the starting interval to search
   * @param endInterval the ending interval to search
   * @param current current position
   * @return the element containing the property in the specified interval
   */
  private int search(int start, int end, int startInterval, int endInterval, int current) {
    if (startInterval <= start && endInterval >= end) {
      return tree[current];
    }

    if (end < startInterval || start > endInterval) {
      return searchUtilDefault();
    }

    int mid = middle(start, end);
    return calculate(
        search(start, mid, startInterval, endInterval, left(current)),
        search(mid + 1, end, startInterval, endInterval, right(current)));
  }

  /**
   * Specifies the default value of a property in case it is not inside the interval (e.g. the
   * default for a {@link MinSegmentTree} is Integer.MAX_VALUE).
   *
   * <p>Time complexity: O(1)
   *
   * <p>Space complexity: O(1)
   *
   * @return the default value
   */
  protected abstract int searchUtilDefault();

  /**
   * Defines the property of the tree (e.g. the default for a {@link MinSegmentTree} is minimum
   * value).
   *
   * <p>Time complexity: O(1)
   *
   * <p>Space complexity: O(1)
   *
   * @param left the left value
   * @param right the right value
   * @return the calculated property
   */
  protected abstract int calculate(int left, int right);

  /**
   * Updates the value of an element.
   *
   * <p>Time complexity: O(logn)
   *
   * <p>Space complexity: O(1)
   *
   * @param i the element index to update
   * @param newValue the new value of the element
   */
  public void update(int i, int newValue) {
    elements[i] = newValue;

    update(0, elements.length - 1, i, 0);
  }

  /**
   * See {@link #update(int, int)}.
   *
   * <p>Time complexity: O(logn)
   *
   * <p>Space complexity: O(1)
   *
   * @param start current start interval
   * @param end current end interval
   * @param i the index of the value to update
   * @param current the current index
   */
  private void update(int start, int end, int i, int current) {
    if (i < start || i > end) {
      return;
    }

    if (start == end) {
      tree[current] = elements[start];
      return;
    }

    int mid = middle(start, end);
    update(start, mid, i, left(current));
    update(mid + 1, end, i, right(current));

    tree[current] = calculate(tree[left(current)], tree[right(current)]);
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
  protected int left(int i) {
    return i * 2 + 1;
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
  protected int right(int i) {
    return i * 2 + 2;
  }

  /**
   * Given an interval calculates the middle point.
   *
   * <p>Time complexity: O(1)
   *
   * <p>Space complexity: O(1)
   *
   * @param start start of the interval
   * @param end end of the interval
   * @return the middle point in the interval
   */
  protected int middle(int start, int end) {
    return (start + end) / 2;
  }

  /**
   * A {@link SegmentTree} with the sum of the elements in the interval property.
   *
   * <p>Example: [8,7,3,9,3]
   *
   * <pre>
   *    x
   *  i:[a,b]
   *
   *  x = the element
   *  i = index in the data structure
   *  a = start interval
   *  b = end interval
   *
   *                     30
   *                   1:[0,4]
   *               /             \
   *            18                 12
   *          2:[0,2]            3:[3,4]
   *         /      \           /       \
   *        15       3         9         3
   *      4:[0,1]   5:[2,2]  6:[3,3]   7:[4,4]
   *    /       \
   *   8         7
   * 8:[0,0]   9:[1,1]
   * </pre>
   */
  public static class SumSegmentTree extends SegmentTree {

    public SumSegmentTree(int[] arr) {
      super(arr);
    }

    protected int searchUtilDefault() {
      return 0;
    }

    protected int calculate(int leftValue, int rightValue) {
      return leftValue + rightValue;
    }
  }

  /**
   * A {@link SegmentTree} with the minimum element in the interval property.
   *
   * <p>Example: [8,7,3,9,3]
   *
   * <pre>
   *    x
   *  i:[a,b]
   *
   *  x = the element
   *  i = index in the data structure
   *  a = start interval
   *  b = end interval
   *
   *                     3
   *                   1:[0,4]
   *               /             \
   *            3                  3
   *          2:[0,2]            3:[3,4]
   *         /      \           /       \
   *        7        3         9         3
   *      4:[0,1]   5:[2,2]  6:[3,3]   7:[4,4]
   *    /       \
   *   8         7
   * 8:[0,0]   9:[1,1]
   * </pre>
   */
  public static class MinSegmentTree extends SegmentTree {

    public MinSegmentTree(int[] arr) {
      super(arr);
    }

    protected int searchUtilDefault() {
      return Integer.MAX_VALUE;
    }

    protected int calculate(int leftValue, int rightValue) {
      return Math.min(leftValue, rightValue);
    }
  }

  /**
   * A {@link SegmentTree} with the maximum element in the interval property.
   *
   * <p>Example: [8,7,3,9,3]
   *
   * <pre>
   *    x
   *  i:[a,b]
   *
   *  x = the element
   *  i = index in the data structure
   *  a = start interval
   *  b = end interval
   *
   *                     9
   *                   1:[0,4]
   *               /             \
   *            8                  9
   *          2:[0,2]            3:[3,4]
   *         /      \           /       \
   *        8        3         9         3
   *      4:[0,1]   5:[2,2]  6:[3,3]   7:[4,4]
   *    /       \
   *   8         7
   * 8:[0,0]   9:[1,1]
   * </pre>
   */
  public static class MaxSegmentTree extends SegmentTree {

    public MaxSegmentTree(int[] arr) {
      super(arr);
    }

    protected int searchUtilDefault() {
      return Integer.MIN_VALUE;
    }

    protected int calculate(int leftValue, int rightValue) {
      return Math.max(leftValue, rightValue);
    }
  }
}
