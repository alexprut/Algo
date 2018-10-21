package com.alexprut.algo.algorithms.graph;

import com.alexprut.algo.algorithms.graph.Graph.Edge;
import com.alexprut.algo.algorithms.graph.Graph.Node;
import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Test;

public class FloydWarshallTest {

  @Test
  public void disconnectedGraph() {

    int[][] expected = Graph.buildAdjacencyMatrix(new ArrayList<>(), 3);
    Assert.assertArrayEquals(expected, FloydWarshall.floydWarshall(expected));
  }

  @Test
  public void connectedGraph() {
    ArrayList<Edge> edges = new ArrayList<>();
    edges.add(new Edge(new Node(0), new Node(1), 1));
    edges.add(new Edge(new Node(0), new Node(2), 1));
    edges.add(new Edge(new Node(1), new Node(2), 1));
    int[][] expected = Graph.buildAdjacencyMatrix(edges, 3);
    Assert.assertArrayEquals(expected, FloydWarshall.floydWarshall(expected));
  }
}
