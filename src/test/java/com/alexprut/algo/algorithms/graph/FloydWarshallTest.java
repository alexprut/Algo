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
}
