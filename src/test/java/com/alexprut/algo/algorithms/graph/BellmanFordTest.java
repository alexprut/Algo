package com.alexprut.algo.algorithms.graph;

import static junit.framework.TestCase.fail;

import com.alexprut.algo.datastructures.Graph;
import com.alexprut.algo.datastructures.Graph.Edge;
import com.alexprut.algo.datastructures.Graph.Node;
import org.junit.Assert;
import org.junit.Test;

public class BellmanFordTest {

  @Test
  public void disconnectedGraph() {
    Graph g = new Graph(3);

    int[] expectedDistances = new int[]{0, Integer.MAX_VALUE, Integer.MAX_VALUE};

    try {
      Assert.assertArrayEquals(expectedDistances, BellmanFord.bellmanFord(g.getEdges(), 3, 0));
    } catch (Exception e) {
      fail("Should not thrown an exception");
    }
  }

  @Test
  public void connectedUndirectedGraph() {
    Graph g = new Graph(4);
    g.addEdge(new Edge(new Node(0), new Node(1), 4));
    g.addEdge(new Edge(new Node(1), new Node(3), -1));
    g.addEdge(new Edge(new Node(0), new Node(2), 4));
    g.addEdge(new Edge(new Node(2), new Node(3), 4));

    int[] expectedDistances = new int[]{0, 4, 4, 3};

    try {
      Assert.assertArrayEquals(expectedDistances, BellmanFord.bellmanFord(g.getEdges(), 4, 0));
    } catch (Exception e) {
      fail("Should not thrown an exception");
    }
  }

  @Test
  public void connectedDirectedGraph() {
    Graph g = new Graph(4, true);
    g.addEdge(new Edge(new Node(0), new Node(1), 4));
    g.addEdge(new Edge(new Node(1), new Node(3), -1));
    g.addEdge(new Edge(new Node(0), new Node(2), 4));
    g.addEdge(new Edge(new Node(2), new Node(3), 4));

    int[] expectedDistances = new int[]{0, 4, 4, 3};

    try {
      Assert.assertArrayEquals(expectedDistances, BellmanFord.bellmanFord(g.getEdges(), 4, 0));
    } catch (Exception e) {
      fail("Should not thrown an exception");
    }
  }

  @Test
  public void negativeCycleGraph() {
    Graph g = new Graph(3);
    g.addEdge(new Edge(new Node(0), new Node(1), -1));
    g.addEdge(new Edge(new Node(1), new Node(2), -4));
    g.addEdge(new Edge(new Node(2), new Node(0), -4));

    try {
      BellmanFord.bellmanFord(g.getEdges(), 4, 0);
      fail("Expected an Exception to be thrown");
    } catch (Exception e) {
      Assert.assertEquals("Graph contains a negative cycle", e.getMessage());
    }
  }
}
