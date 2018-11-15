package com.alexprut.algo.datastructures;

import org.junit.Assert;
import org.junit.Test;

public class GraphTest {

  @Test
  public void shouldCreateUndirectedGraph() {
    int n = 4;
    Graph graph = new Graph(n);
    graph.addEdge(0, 1, 1);
    graph.addEdge(0, 2, 1);

    int[][] expected = new int[n][n];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        expected[i][j] = Integer.MAX_VALUE;
      }
    }
    expected[0][1] = 1;
    expected[1][0] = 1;
    expected[0][2] = 1;
    expected[2][0] = 1;

    Assert.assertArrayEquals(expected, graph.getAdjacencyMatrix());
  }

  @Test
  public void shouldCreateDirectedGraph() {
    int n = 4;
    Graph graph = new Graph(n, true);
    graph.addEdge(0, 1, 1);
    graph.addEdge(0, 2, 1);

    int[][] expected = new int[n][n];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        expected[i][j] = Integer.MAX_VALUE;
      }
    }
    expected[0][1] = 1;
    expected[0][2] = 1;

    Assert.assertArrayEquals(expected, graph.getAdjacencyMatrix());
  }
}
