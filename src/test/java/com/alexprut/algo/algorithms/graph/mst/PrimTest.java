package com.alexprut.algo.algorithms.graph.mst;

import com.alexprut.algo.datastructures.Graph;
import org.junit.Assert;
import org.junit.Test;

public class PrimTest {

  @Test
  public void shouldFindMst() {
    /*
             2
    node0 ------- node1
        \           /
       3 \         / 4
          \       /
            node2 ------- node3
                     1
     */
    Graph g = new Graph(4);
    g.addEdge(0, 1, 2);
    g.addEdge(1, 2, 4);
    g.addEdge(0, 2, 3);
    g.addEdge(2, 3, 1);

    /*
             2
    node0 ------- node1
        \
       3 \
          \
            node2 ------- node3
                     1
     */
    Assert.assertArrayEquals(new int[] {-1, 0, 0, 2}, Prim.prim(g.getAdjacencyList(), 4, 0));
    Assert.assertArrayEquals(new int[] {2, 0, 3, -1}, Prim.prim(g.getAdjacencyList(), 4, 3));
  }

  @Test
  public void singleNodeMst() {
    Graph g = new Graph(1);
    Assert.assertArrayEquals(new int[] {-1}, Prim.prim(g.getAdjacencyList(), 1, 0));
  }
}
