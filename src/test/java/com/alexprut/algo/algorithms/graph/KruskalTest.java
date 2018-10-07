package com.alexprut.algo.algorithms.graph;

import com.alexprut.algo.algorithms.graph.Graph.Edge;
import com.alexprut.algo.algorithms.graph.Graph.Node;
import com.alexprut.algo.algorithms.graph.mst.Kruskal;
import java.util.ArrayList;
import java.util.Collections;
import org.junit.Assert;
import org.junit.Test;

public class KruskalTest {

  @Test
  public void shouldFindMst() {
    ArrayList<Edge> edges = new ArrayList<>();
    edges.add(new Edge(new Node(0), new Node(1), 4));
    edges.add(new Edge(new Node(0), new Node(7), 8));
    edges.add(new Edge(new Node(1), new Node(7), 11));
    edges.add(new Edge(new Node(1), new Node(2), 8));
    edges.add(new Edge(new Node(2), new Node(3), 7));
    edges.add(new Edge(new Node(2), new Node(8), 2));
    edges.add(new Edge(new Node(8), new Node(7), 7));
    edges.add(new Edge(new Node(7), new Node(6), 1));
    edges.add(new Edge(new Node(8), new Node(6), 6));
    edges.add(new Edge(new Node(2), new Node(5), 4));
    edges.add(new Edge(new Node(6), new Node(5), 2));
    edges.add(new Edge(new Node(3), new Node(5), 14));
    edges.add(new Edge(new Node(3), new Node(4), 9));
    edges.add(new Edge(new Node(5), new Node(4), 10));

    ArrayList<Edge> mst = Kruskal.kruskal(edges, 9);

    ArrayList<Edge> expectedMst = new ArrayList<>();
    expectedMst.add(new Edge(new Node(0), new Node(1), 4));
    expectedMst.add(new Edge(new Node(0), new Node(7), 8));
    expectedMst.add(new Edge(new Node(2), new Node(3), 7));
    expectedMst.add(new Edge(new Node(2), new Node(8), 2));
    expectedMst.add(new Edge(new Node(7), new Node(6), 1));
    expectedMst.add(new Edge(new Node(2), new Node(5), 4));
    expectedMst.add(new Edge(new Node(6), new Node(5), 2));
    expectedMst.add(new Edge(new Node(3), new Node(4), 9));

    Collections.sort(mst);
    Collections.sort(expectedMst);

    Assert.assertEquals(mst.size(), expectedMst.size());
    for (int i = 0; i < mst.size(); i++) {
      Assert.assertTrue(mst.get(i).equals(expectedMst.get(i)));
    }
  }
}
