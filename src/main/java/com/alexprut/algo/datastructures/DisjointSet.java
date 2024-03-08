package com.alexprut.algo.datastructures;

import java.util.HashMap;

/**
 * A disjoint-set data structure (also called a union–find data structure or merge–find set) is a
 * data structure that tracks a set of elements partitioned into a number of disjoint
 * (non-overlapping) subsets. It provides near-constant-time operations. To improve the running
 * time, the following heuristics are used: union by rank and path compression.
 *
 * <p>Example: 3 sets, 1st set contains: {1,2,5,6,8}, 2nd set contains: {3,4}, 3rd set contains: {7}
 *
 * <pre>
 *    |-----------------|   |--------|   |-----|
 *    |  1  2  5  6  8  |   |  3  4  |   |  7  |
 *    |-----------------|   |--------|   |-----|
 * </pre>
 *
 * @see <a
 *     href="https://en.wikipedia.org/wiki/Disjoint-set_data_structure">https://en.wikipedia.org/wiki/Disjoint-set_data_structure</a>
 * @param <T>
 */
public class DisjointSet<T> {

  private final HashMap<T, Element<T>> sets = new HashMap<>();

  /**
   * Creates a new set.
   *
   * <p>Time complexity: O(1)
   *
   * <p>Space complexity: O(1)
   *
   * @param value the element value
   * @return the new set containing one element
   */
  public Element<T> makeSet(T value) {
    sets.put(value, new Element<>(value));
    return sets.get(value);
  }

  /**
   * Union of two sets.
   *
   * <p>Time complexity: O(1)
   *
   * <p>Space complexity: O(1)
   *
   * @param a first set
   * @param b second set
   */
  public void union(Element<T> a, Element<T> b) {
    Element<T> parentA = findSet(a);
    Element<T> parentB = findSet(b);

    if (parentA.getValue() == parentB.getValue()) {
      return;
    }

    if (parentA.getRank() > parentB.getRank()) {
      parentB.setParent(parentA);
    } else {
      parentA.setParent(parentB);
      if (parentA.getRank() == parentB.getRank()) {
        parentB.setRank(parentB.getRank() + 1);
      }
    }
  }

  /**
   * Given an element finds the set to with it belongs.
   *
   * <p>Time complexity: O(1)
   *
   * <p>Space complexity: O(1)
   *
   * @param s the element to find
   * @return the set to witch the element belongs
   */
  public Element<T> findSet(Element<T> s) {
    if (s.parent().getValue() == s.getValue()) {
      return s;
    }

    s.setParent(findSet(s.parent()));
    return s.parent();
  }

  /**
   * Given an element finds the set to with it belongs.
   *
   * <p>Time complexity: O(1)
   *
   * <p>Space complexity: O(1)
   *
   * @param value the element value to find
   * @return the set to witch the element belongs
   */
  public Element<T> findSet(T value) {
    return findSet(getElement(value));
  }

  /**
   * Given the value find the element.
   *
   * <p>Time complexity: O(1)
   *
   * <p>Space complexity: O(1)
   *
   * @param value the element value to find
   * @return the element to witch the value belongs
   */
  public Element<T> getElement(T value) {
    return sets.get(value);
  }

  static class Element<T> {

    private Element<T> parent;
    private int rank;
    private final T value;

    Element(T value) {
      this.value = value;
      this.rank = 0;
      this.parent = this;
    }

    public int getRank() {
      return this.rank;
    }

    protected void setRank(int rank) {
      this.rank = rank;
    }

    public T getValue() {
      return this.value;
    }

    public Element<T> parent() {
      return this.parent;
    }

    protected void setParent(Element<T> parent) {
      this.parent = parent;
    }
  }
}
