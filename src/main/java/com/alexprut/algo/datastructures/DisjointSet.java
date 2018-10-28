package com.alexprut.algo.datastructures;

import java.util.HashMap;

public class DisjointSet<T> {

  private HashMap<T, Element<T>> sets = new HashMap<>();

  public Element<T> makeSet(T value) {
    sets.put(value, new Element<>(value));
    return sets.get(value);
  }

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

  public Element<T> findSet(Element<T> s) {
    if (s.parent().getValue() == s.getValue()) {
      return s;
    }

    s.setParent(findSet(s.parent()));
    return s.parent();
  }

  public Element<T> findSet(T value) {
    return findSet(getElement(value));
  }

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
