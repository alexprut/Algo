package com.alexprut.algo.datastructures;

import com.alexprut.algo.algorithms.graph.mst.Kruskal;
import java.util.ArrayList;

/**
 * A graph data structure consists of a finite (and possibly mutable) set of vertices (also called
 * nodes), together with a set of unordered pairs of these vertices for an undirected graph or a set
 * of ordered pairs for a directed graph. These pairs are known as edges (also called links).
 *
 * <p>Conventions: the nodes are labeled from 0 to n (exclusive).
 *
 * <p>Example:
 *
 * <pre>
 *   1 — 2 — 32 -43
 *       |
 *       3 — 12
 *     /  \
 *   65    6
 * </pre>
 *
 * @see <a
 *     href="https://en.wikipedia.org/wiki/Graph_(abstract_data_type)">https://en.wikipedia.org/wiki/Graph_(abstract_data_type)</a>
 */
public class Graph {

  /** True if the graph is directed, otherwise if it is undirected. */
  private boolean isDirected = false;
  /** The graph edges (or links). */
  private final ArrayList<Edge> edges = new ArrayList<>();
  /** The adjacency-matrix representation of the graph. */
  private int[][] adjMatrix;
  /** The adjacency-list representation of the graph. */
  private ArrayList<ArrayList<Pair<Integer, Integer>>> adjList;
  /** The number of nodes in the graph. */
  private int n;

  public Graph(int n) {
    this.n = n;
  }

  public Graph(int n, boolean isDirected) {
    this(n);
    this.isDirected = isDirected;
  }

  /**
   * Builds the adjacency-matrix representation of the graph.
   *
   * <p>Time complexity: O(|V|^2)
   *
   * <p>Space complexity: O(|V|^2)
   */
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

  /**
   * Builds the adjacency-list representation of the graph.
   *
   * <p>Time complexity: O(|V|+|E|)
   *
   * <p>Space complexity: O(|V|+|E|)
   */
  private void buildAdjacencyList() {
    adjList = new ArrayList<>();

    for (int i = 0; i < n; i++) {
      adjList.add(new ArrayList<>());
    }

    for (Edge edge : edges) {
      adjList.get(edge.x.value).add(new Pair<>(edge.y.value, edge.w));
    }
  }

  /**
   * Get the adjacency-matrix.
   *
   * <p>Time complexity: O(1), otherwise O(|V|^2) if the matrix needs to be computed
   *
   * <p>Space complexity: O(1), otherwise O(|V|^2) if the matrix needs to be computed
   *
   * @return the adjacency-matrix
   */
  public int[][] getAdjacencyMatrix() {
    if (adjMatrix == null) {
      buildAdjacencyMatrix();
    }

    return adjMatrix;
  }

  /**
   * Get the adjacency-list.
   *
   * <p>Time complexity: O(1), otherwise O(|V|+|E|) if the matrix needs to be computed
   *
   * <p>Space complexity: O(1), otherwise O(|V|+|E|) if the matrix needs to be computed
   *
   * @return the adjacency-list
   */
  public ArrayList<ArrayList<Pair<Integer, Integer>>> getAdjacencyList() {
    if (adjList == null) {
      buildAdjacencyList();
    }

    return adjList;
  }

  /**
   * Get all the edges.
   *
   * <p>Time complexity: O(1)
   *
   * <p>Space complexity: O(1)
   *
   * @return the list of the edges
   */
  public ArrayList<Edge> getEdges() {
    return edges;
  }

  /**
   * Calculates the minimum spanning tree. Uses {@link Kruskal}.
   *
   * <p>Time complexity: O(|E|log|V|)
   *
   * <p>Space complexity: O(n)
   *
   * @return the minimum spanning tree
   */
  public ArrayList<Edge> mst() {
    return Kruskal.kruskal(edges, n);
  }

  /**
   * Add all the edges to the graph.
   *
   * <p>Time complexity: O(1)
   *
   * <p>Space complexity: O(1)
   *
   * @param edges the edges to add
   */
  public void addEdge(ArrayList<Edge> edges) {
    for (Edge edge : edges) {
      addEdge(edge);
    }
  }

  /**
   * Add a new edge to the graph.
   *
   * <p>Time complexity: O(1)
   *
   * <p>Space complexity: O(1)
   *
   * @param edge the new edge
   */
  public void addEdge(Edge edge) {
    edges.add(edge);

    if (!isDirected) {
      edges.add(new Edge(new Node(edge.y.value()), new Node(edge.x.value()), edge.w));
    }
  }

  /**
   * Add a new edge to the graph.
   *
   * <p>Time complexity: O(1)
   *
   * <p>Space complexity: O(1)
   *
   * @param x the starting node
   * @param y the ending node
   * @param w the weight
   */
  public void addEdge(int x, int y, int w) {
    addEdge(new Edge(new Node(x), new Node(y), w));
  }

  public static class Node {

    private int value;

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
