package com.alexprut.algo.datastructures;

/**
 * Stores intervals, and optimized for "which of these intervals contains a given point" queries.
 */
public class SegmentTree {

  private int elements[];
  private int tree[];

  public SegmentTree(int arr[]) {
    elements = arr;
    int x = (int) (Math.ceil(Math.log(elements.length) / Math.log(2)));
    tree = new int[2 * (int) Math.pow(2, x) - 1];
    build(0, elements.length - 1, 0);
  }

  private int search(int start, int end, int startInterval, int endInterval, int current) {
    if (startInterval <= start && endInterval >= end) {
      return tree[current];
    }

    if (end < startInterval || start > endInterval) {
      return 0;
    }

    int mid = middle(start, end);
    return search(start, mid, startInterval, endInterval, left(current)) +
        search(mid + 1, end, startInterval, endInterval, right(current));
  }

  private void update(int start, int end, int i, int diff, int current) {
    if (i < start || i > end) {
      return;
    }

    tree[current] = tree[current] + diff;
    if (end != start) {
      int mid = middle(start, end);
      update(start, mid, i, diff, left(current));
      update(mid + 1, end, i, diff, right(current));
    }
  }

  public void update(int i, int newValue) {
    int diff = newValue - elements[i];
    elements[i] = newValue;

    update(0, tree.length - 1, i, diff, 0);
  }

  public int search(int startInterval, int endInterval) {
    return search(0, elements.length - 1, startInterval, endInterval, 0);
  }

  private int build(int startInterval, int endInterval, int current) {
    if (startInterval == endInterval) {
      tree[current] = elements[startInterval];
      return elements[startInterval];
    }

    int middle = middle(startInterval, endInterval);
    tree[current] = build(startInterval, middle, left(current)) +
        build(middle + 1, endInterval, right(current));
    return tree[current];
  }

  protected int left(int i) {
    return i * 2 + 1;
  }

  protected int right(int i) {
    return i * 2 + 2;
  }

  protected int middle(int start, int end) {
    return (start + end) / 2;
  }
}
