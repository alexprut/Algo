package com.alexprut.algo.algorithms.graph;

import java.util.ArrayList;

public class Graph {

  public static int[][] buildAdjacencyMatrix(ArrayList<Edge> edges, int n) {
    int[][] adj = new int[n][n];

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        adj[i][j] = Integer.MAX_VALUE;
      }
    }

    for (Edge edge : edges) {
      adj[edge.x.value][edge.y.value] = edge.w;
      adj[edge.y.value][edge.x.value] = edge.w;
    }

    return adj;
  }

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
