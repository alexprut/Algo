package com.alexprut.algo.algorithms.graph.search;

import com.alexprut.algo.algorithms.search.BreadthFirstSearch;
import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Test;

public class BreadthFirstSearchTest {

  @Test
  public void computeShortestPathWithBfs() {
    /*
        node2 — node1 — node0
          |
        node3 — node5
        /   \
     node4   node6
    */
    ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
    adj.add(new ArrayList<>());
    adj.add(new ArrayList<>());
    adj.add(new ArrayList<>());
    adj.add(new ArrayList<>());
    adj.add(new ArrayList<>());
    adj.add(new ArrayList<>());
    adj.add(new ArrayList<>());
    adj.get(0).add(1);
    adj.get(1).add(0);
    adj.get(1).add(2);
    adj.get(2).add(1);
    adj.get(2).add(3);
    adj.get(3).add(2);
    adj.get(3).add(5);
    adj.get(3).add(6);
    adj.get(3).add(4);
    adj.get(4).add(3);
    adj.get(5).add(3);
    adj.get(6).add(3);

    int[] expectedShortestPath = new int[] {0, 1, 2, 3, 4, 4, 4};
    int[] shortestPath = BreadthFirstSearch.shortestPath(adj, 0);
    Assert.assertArrayEquals(expectedShortestPath, shortestPath);
  }

  @Test
  public void computePredecessorSubGraph() {
    /*
         node2 — node1 — node0
           |
         node3 — node5
         /   \   /
     node4 - node6
    */
    ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
    adj.add(new ArrayList<>());
    adj.add(new ArrayList<>());
    adj.add(new ArrayList<>());
    adj.add(new ArrayList<>());
    adj.add(new ArrayList<>());
    adj.add(new ArrayList<>());
    adj.add(new ArrayList<>());
    adj.get(0).add(1);
    adj.get(1).add(0);
    adj.get(1).add(2);
    adj.get(2).add(1);
    adj.get(2).add(3);
    adj.get(3).add(2);
    adj.get(3).add(5);
    adj.get(3).add(6);
    adj.get(3).add(4);
    adj.get(4).add(3);
    adj.get(4).add(6);
    adj.get(5).add(3);
    adj.get(5).add(6);
    adj.get(6).add(3);
    adj.get(6).add(4);
    adj.get(6).add(5);

    int[] expectedShortestPath = new int[] {Integer.MAX_VALUE, 0, 1, 2, 3, 3, 3};
    int[] shortestPath = BreadthFirstSearch.predecessorSubgraph(adj, 0);
    Assert.assertArrayEquals(expectedShortestPath, shortestPath);
  }
}
