package com.alexprut.algo.algorithms.graph;

public class Graph {

  public static class Node {

    int value;

    Node(int value) {
      this.value = value;
    }

    public int value() {
      return this.value;
    }

    public boolean equals(Node b) {
      return this.value == b.value();
    }
  }

  public static class Edge implements Comparable<Edge> {

    public Node x;
    public Node y;
    public int w;

    Edge(Node x, Node y, int w) {
      this.x = x;
      this.y = y;
      this.w = w;
    }

    public int compareTo(Edge b) {
      return (this.w < b.w) ? -1 : 1;
    }

    public boolean equals(Edge b) {
      return x.equals(b.x) && y.equals(b.y) && w == b.w;
    }
  }
}
