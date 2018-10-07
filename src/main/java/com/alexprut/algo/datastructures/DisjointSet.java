package com.alexprut.algo.datastructures;

import java.util.HashMap;

public class DisjointSet {

  private HashMap<Integer, Element> sets = new HashMap<>();

  public Element makeSet(int value) {
    sets.put(value, new Element(value));
    return sets.get(value);
  }

  public void union(Element a, Element b) {
    Element parentA = findSet(a);
    Element parentB = findSet(b);
    if (parentA.getRank() > parentB.getRank()) {
      parentB.setParent(parentA);
    } else {
      parentA.setParent(parentB);
      if (parentA.getRank() == parentB.getRank()) {
        parentB.setRank(parentB.getRank() + 1);
      }
    }
  }

  public Element findSet(Element s) {
    if (s.parent().getValue() == s.getValue()) {
      return s;
    }

    s.setParent(findSet(s.parent()));
    return s.parent();
  }

  public Element getElement(int value) {
    return sets.get(value);
  }

  class Element {

    private Element parent;
    private int rank;
    private int value;

    Element(int value) {
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

    public int getValue() {
      return this.value;
    }

    public Element parent() {
      return this.parent;
    }

    public void setParent(Element parent) {
      this.parent = parent;
    }
  }
}