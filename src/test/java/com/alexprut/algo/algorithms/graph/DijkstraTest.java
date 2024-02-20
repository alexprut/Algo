package com.alexprut.algo.algorithms.graph;

import com.alexprut.algo.datastructures.Graph;
import com.alexprut.algo.datastructures.Graph.Edge;
import com.alexprut.algo.datastructures.Graph.Node;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DijkstraTest {

  @Test
  public void disconnectedGraph() {
    Graph g = new Graph(3);

    int[] expectedDistances = new int[] {0, Integer.MAX_VALUE, Integer.MAX_VALUE};

    try {
      Assertions.assertArrayEquals(
          expectedDistances, Dijkstra.dijkstra(g.getAdjacencyList(), 3, 0));
    } catch (Exception e) {
      Assertions.fail("Should not thrown an exception");
    }
  }

  @Test
  public void connectedUndirectedGraph() {
    Graph g = new Graph(4);
    g.addEdge(new Edge(new Node(0), new Node(1), 4));
    g.addEdge(new Edge(new Node(1), new Node(3), 1));
    g.addEdge(new Edge(new Node(0), new Node(2), 4));
    g.addEdge(new Edge(new Node(2), new Node(3), 4));

    int[] expectedDistances = new int[] {0, 4, 4, 5};

    try {
      Assertions.assertArrayEquals(
          expectedDistances, Dijkstra.dijkstra(g.getAdjacencyList(), 4, 0));
    } catch (Exception e) {
      Assertions.fail("Should not thrown an exception");
    }
  }

  @Test
  public void connectedDirectedGraph() {
    Graph g = new Graph(4, true);
    g.addEdge(new Edge(new Node(0), new Node(1), 4));
    g.addEdge(new Edge(new Node(1), new Node(3), 1));
    g.addEdge(new Edge(new Node(0), new Node(2), 4));
    g.addEdge(new Edge(new Node(2), new Node(3), 4));

    int[] expectedDistances = new int[] {0, 4, 4, 5};

    try {
      Assertions.assertArrayEquals(
          expectedDistances, Dijkstra.dijkstra(g.getAdjacencyList(), 4, 0));
    } catch (Exception e) {
      Assertions.fail("Should not thrown an exception");
    }
  }
}
