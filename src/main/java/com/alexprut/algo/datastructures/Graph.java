package com.alexprut.algo.datastructures;

import com.alexprut.algo.algorithms.graph.mst.Kruskal;
import java.util.ArrayList;

public class Graph {

  private boolean isDirected = false;
  private ArrayList<Edge> edges = new ArrayList<>();
  private int[][] adjMatrix;
  private ArrayList<ArrayList<Pair<Integer, Integer>>> adjList;
  private int n;

  public Graph(int n) {
    this.n = n;
  }

  public Graph(int n, boolean isDirected) {
    this(n);
    this.isDirected = isDirected;
  }

  private void buildAdjacencyMatrix() {
    adjMatrix = new int[n][n];

    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        adjMatrix[i][j] = Integer.MAX_VALUE;
      }
    }

    for (Edge edge : edges) {
      adjMatrix[edge.x.value][edge.y.value] = edge.w;
      if (!isDirected) {
        adjMatrix[edge.y.value][edge.x.value] = edge.w;
      }
    }
  }

  private void buildAdjacencyList() {
    adjList = new ArrayList<>();

    for (int i = 0; i < n; i++) {
      adjList.add(new ArrayList<>());
    }

    for (Edge edge : edges) {
      adjList.get(edge.x.value).add(new Pair<>(edge.y.value, edge.w));
      if (!isDirected) {
        adjList.get(edge.y.value).add(new Pair<>(edge.x.value, edge.w));
      }
    }
  }

  public int[][] getAdjacencyMatrix() {
    if (adjMatrix == null) {
      buildAdjacencyMatrix();
    }

    return adjMatrix;
  }

  public ArrayList<ArrayList<Pair<Integer, Integer>>> getAdjacencyList() {
    if (adjMatrix == null) {
      buildAdjacencyList();
    }

    return adjList;
  }

  public ArrayList<Edge> getEdges() {
    return edges;
  }

  /**
   * Calculates the minimum spanning tree
   */
  public ArrayList<Edge> mst() {
    return Kruskal.kruskal(edges, n);
  }

  public void addEdge(ArrayList<Edge> edges) {
    for (Edge edge: edges) {
      addEdge(edge);
    }
  }

  public void addEdge(Edge edge) {
    edges.add(edge);

    if (!isDirected) {
      edges.add(new Edge(new Node(edge.y.value()), new Node(edge.x.value()), edge.w));
    }
  }

  public void addEdge(int x, int y, int w) {
    addEdge(new Edge(new Node(x), new Node(y), w));
  }

  public static class Node {

    int value;

    public Node(int value) {
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

    public Edge(Node x, Node y, int w) {
      this.x = x;
      this.y = y;
      this.w = w;
    }

    public Edge(Node x, Node y) {
      this(x, y, 1);
    }

    public int compareTo(Edge b) {
      return (this.w < b.w) ? -1 : 1;
    }

    public boolean equals(Edge b) {
      return x.equals(b.x) && y.equals(b.y) && w == b.w;
    }
  }
}
