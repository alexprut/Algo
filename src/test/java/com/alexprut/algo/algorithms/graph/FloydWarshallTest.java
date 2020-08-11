package com.alexprut.algo.algorithms.graph;

import com.alexprut.algo.datastructures.Graph;
import com.alexprut.algo.datastructures.Graph.Edge;
import com.alexprut.algo.datastructures.Graph.Node;
import org.junit.Assert;
import org.junit.Test;

public class FloydWarshallTest {

  @Test
  public void disconnectedGraph() {
    Graph graph = new Graph(3);
    int[][] expected = graph.getAdjacencyMatrix();
    Assert.assertArrayEquals(expected, FloydWarshall.floydWarshall(expected));
  }

  @Test
  public void connectedGraph() {
    Graph graph = new Graph(3);
    graph.addEdge(new Edge(new Node(0), new Node(1)));
    graph.addEdge(new Edge(new Node(0), new Node(2)));
    graph.addEdge(new Edge(new Node(1), new Node(2)));
    int[][] expected = graph.getAdjacencyMatrix();
    Assert.assertArrayEquals(expected, FloydWarshall.floydWarshall(expected));
  }

  @Test
  public void connectedGraphWeighted() {
    /*
              3           1
      node0 ←---→ node1 ----→ node4
        ↑           |
      2 |           | -1
        ↓           ↓
      node3 ←---→ node2
              2
    */
    Graph graph = new Graph(5, true);
    Node node0 = new Node(0);
    Node node1 = new Node(1);
    Node node2 = new Node(2);
    Node node3 = new Node(3);
    Node node4 = new Node(4);
    graph.addEdge(new Edge(node0, node1, 3));
    graph.addEdge(new Edge(node0, node3, 2));
    graph.addEdge(new Edge(node1, node0, 3));
    graph.addEdge(new Edge(node1, node2, -1));
    graph.addEdge(new Edge(node1, node4, 1));
    graph.addEdge(new Edge(node2, node3, 2));
    graph.addEdge(new Edge(node3, node0, 2));
    graph.addEdge(new Edge(node3, node2, 2));
    int[][] expected = {
      {0, 3, 2, 2, 4},
      {3, 0, -1, 1, 1},
      {4, 7, 0, 2, 8},
      {2, 5, 2, 0, 6},
      {Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 0}
    };

    int[][] actual = FloydWarshall.floydWarshall(graph.getAdjacencyMatrix());
    Assert.assertArrayEquals(expected, actual);
  }
}
