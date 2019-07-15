package com.alexprut.algo.datastructures;

/**
 * Stores intervals, and optimized for "which of these intervals contains a given point" queries.
 */
abstract class SegmentTree {

  private int elements[];
  private int tree[];

  public SegmentTree(int arr[]) {
    elements = arr;
    int x = (int) (Math.ceil(Math.log(elements.length) / Math.log(2)));
    tree = new int[2 * (int) Math.pow(2, x) - 1];
    build(0, elements.length - 1, 0);
  }

  /** Time complexity: O(logn) */
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

  /** Time complexity: O(1) */
  protected abstract int searchUtilDefault();

  /** Time complexity: O(1) */
  protected abstract int calculate(int left, int right);

  /** Time complexity: O(logn) */
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

  /** Time complexity: O(logn) */
  public void update(int i, int newValue) {
    elements[i] = newValue;

    update(0, elements.length - 1, i, 0);
  }

  /** Time complexity: O(logn) */
  public int search(int startInterval, int endInterval) {
    return search(0, elements.length - 1, startInterval, endInterval, 0);
  }

  /** Time complexity: O(n) */
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

  /** Time complexity: O(1) */
  protected int left(int i) {
    return i * 2 + 1;
  }

  /** Time complexity: O(1) */
  protected int right(int i) {
    return i * 2 + 2;
  }

  /** Time complexity: O(1) */
  protected int middle(int start, int end) {
    return (start + end) / 2;
  }

  public static class SumSegmentTree extends SegmentTree {

    public SumSegmentTree(int arr[]) {
      super(arr);
    }

    protected int searchUtilDefault() {
      return 0;
    }

    protected int calculate(int leftValue, int rightValue) {
      return leftValue + rightValue;
    }
  }

  public static class MinSegmentTree extends SegmentTree {

    public MinSegmentTree(int arr[]) {
      super(arr);
    }

    protected int searchUtilDefault() {
      return Integer.MAX_VALUE;
    }

    protected int calculate(int leftValue, int rightValue) {
      return Math.min(leftValue, rightValue);
    }
  }

  public static class MaxSegmentTree extends SegmentTree {

    public MaxSegmentTree(int arr[]) {
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
