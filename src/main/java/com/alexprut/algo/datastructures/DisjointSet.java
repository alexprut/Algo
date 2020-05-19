package com.alexprut.algo.datastructures;

import java.util.HashMap;

/**
 * @see <a
 *     href="https://en.wikipedia.org/wiki/Disjoint-set_data_structure">https://en.wikipedia.org/wiki/Disjoint-set_data_structure</a>
 * @param <T>
 */
public class DisjointSet<T> {

  private HashMap<T, Element<T>> sets = new HashMap<>();

  /**
   * TODO
   *
   * @param value
   * @return
   */
  public Element<T> makeSet(T value) {
    sets.put(value, new Element<>(value));
    return sets.get(value);
  }

  /**
   * TODO
   *
   * @param a
   * @param b
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
   * TODO
   *
   * @param s
   * @return
   */
  public Element<T> findSet(Element<T> s) {
    if (s.parent().getValue() == s.getValue()) {
      return s;
    }

    s.setParent(findSet(s.parent()));
    return s.parent();
  }

  /**
   * TODO
   *
   * @param value
   * @return
   */
  public Element<T> findSet(T value) {
    return findSet(getElement(value));
  }

  /**
   * TODO
   *
   * @param value
   * @return
   */
  public Element<T> getElement(T value) {
    return sets.get(value);
  }

  class Element<T> {

    private Element<T> parent;
    private int rank;
    private T value;

    Element(T value) {
      this.value = value;
      this.rank = 0;
      this.parent = this;
    }

    public int getRank() {
      return this.rank;
    }

    public void setRank(int rank) {
      this.rank = rank;
    }

    public T getValue() {
      return this.value;
    }

    public Element<T> parent() {
      return this.parent;
    }

    public void setParent(Element<T> parent) {
      this.parent = parent;
    }
  }
}
