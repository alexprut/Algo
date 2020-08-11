package com.alexprut.algo.algorithms.graph.search;

import com.alexprut.algo.algorithms.search.DepthFirstSearch;
import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Test;

public class DepthFirstSearchTest {

  @Test
  public void computeShortestPathWithBfs() {
    /*
          node2       node1 — node0
            |           |    /
          node3        node5
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
    adj.get(0).add(5);
    adj.get(1).add(0);
    adj.get(1).add(5);
    adj.get(2).add(3);
    adj.get(3).add(2);
    adj.get(3).add(4);
    adj.get(3).add(6);
    adj.get(4).add(3);
    adj.get(5).add(0);
    adj.get(5).add(1);
    adj.get(6).add(3);

    Assert.assertEquals(2, DepthFirstSearch.countForest(adj));
  }

  @Test
  public void checkFindCycle() {
    /*
     node1 → node0 → node3
        ↑    ↓
        node2
    */
    ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
    adj.add(new ArrayList<>());
    adj.add(new ArrayList<>());
    adj.add(new ArrayList<>());
    adj.add(new ArrayList<>());
    adj.get(0).add(3);
    adj.get(0).add(2);
    adj.get(1).add(0);
    adj.get(2).add(1);

    Assert.assertTrue(DepthFirstSearch.hasCycle(adj));
  }

  @Test
  public void shouldNotFindCycle() {
    /*
     node1 → node0 → node3
               ↓
             node2
    */
    ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
    adj.add(new ArrayList<>());
    adj.add(new ArrayList<>());
    adj.add(new ArrayList<>());
    adj.add(new ArrayList<>());
    adj.get(0).add(3);
    adj.get(0).add(2);
    adj.get(1).add(0);

    Assert.assertFalse(DepthFirstSearch.hasCycle(adj));
  }

  @Test
  public void shouldFindCycleInStronglyConnectedGraph() {
    /*
     node1 ←→ node0
    */
    ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
    adj.add(new ArrayList<>());
    adj.add(new ArrayList<>());
    adj.get(0).add(1);
    adj.get(1).add(0);

    Assert.assertTrue(DepthFirstSearch.hasCycle(adj));
  }

  @Test
  public void shouldGetTopologicalSort() {
    /*
      node0 → node1 → node3
        ↓
      node2
    */
    ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
    adj.add(new ArrayList<>());
    adj.add(new ArrayList<>());
    adj.add(new ArrayList<>());
    adj.get(0).add(1);
    adj.get(0).add(2);
    adj.get(1).add(3);

    ArrayList<Integer> expected = new ArrayList<>();
    expected.add(0);
    expected.add(1);
    expected.add(3);
    expected.add(2);

    try {
      Assert.assertEquals(expected, DepthFirstSearch.topologicalSort(adj));
    } catch (Exception e) {
    }
  }

  @Test
  public void shouldCheckCycleProperty() {
    /*
      node0 → node1 → node3
        ↓
      node2
    */
    ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
    adj.add(new ArrayList<>());
    adj.add(new ArrayList<>());
    adj.add(new ArrayList<>());
    adj.get(0).add(1);
    adj.get(0).add(2);
    adj.get(1).add(3);

    Assert.assertFalse(DepthFirstSearch.hasCycle(adj));

    /*
      node0 → node1 → node3
          ↑   ↓
          node2
    */
    ArrayList<ArrayList<Integer>> adj2 = new ArrayList<>();
    adj2.add(new ArrayList<>());
    adj2.add(new ArrayList<>());
    adj2.add(new ArrayList<>());
    adj2.add(new ArrayList<>());
    adj2.get(0).add(1);
    adj2.get(1).add(3);
    adj2.get(1).add(2);
    adj2.get(2).add(0);

    Assert.assertTrue(DepthFirstSearch.hasCycle(adj2));
  }
}
