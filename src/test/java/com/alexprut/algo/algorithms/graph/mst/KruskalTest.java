package com.alexprut.algo.algorithms.graph.mst;

import com.alexprut.algo.datastructures.Graph.Edge;
import com.alexprut.algo.datastructures.Graph.Node;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class KruskalTest {

  @Test
  public void shouldFindMst() {
    List<Edge> edges = new ArrayList<>();
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

    List<Edge> mst = Kruskal.kruskal(edges, 9);

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

    Assertions.assertEquals(mst.size(), expectedMst.size());
    for (int i = 0; i < mst.size(); i++) {
      Assertions.assertTrue(mst.get(i).equals(expectedMst.get(i)));
    }
  }
}
