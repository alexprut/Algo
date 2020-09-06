package com.alexprut.algo.algorithms.graph.mst;

import com.alexprut.algo.datastructures.DisjointSet;
import com.alexprut.algo.datastructures.Graph;
import com.alexprut.algo.datastructures.Graph.Edge;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Kruskal {

  private Kruskal() {}

  /**
   * Finds the minimum spanning tree (MST) of a weighted undirected graph.
   *
   * <p>Example:
   *
   * <pre>
   *          3
   *  node0 ----- node1
   *    |           |
   *  2 |           | 6
   *    |           |
   *  node3 ----- node2
   *          1
   *
   * The Minimum Spanning Tree is:
   *
   *          3
   *  node0 ----- node1
   *    |
   *  2 |
   *    |
   *  node3 ----- node2
   *          1
   * </pre>
   *
   * <p>Time complexity: O(|E|log|V|)
   *
   * <p>Space complexity: O(|V|+|E|)
   *
   * @see <a
   *     href="https://en.wikipedia.org/wiki/Kruskal%27s_algorithm">https://en.wikipedia.org/wiki/Kruskal%27s_algorithm</a>
   * @param edges the list of edges
   * @param n the number of nodes
   * @return the minimum spanning tree
   */
  public static List<Edge> kruskal(List<Graph.Edge> edges, int n) {
    ArrayList<Graph.Edge> mst = new ArrayList<>();
    DisjointSet<Integer> sets = new DisjointSet<>();

    for (int i = 0; i < n; i++) {
      sets.makeSet(i);
    }

    Collections.sort(edges);

    for (Edge e : edges) {
      if (sets.findSet(e.x.value()) != sets.findSet(e.y.value())) {
        sets.union(sets.getElement(e.x.value()), sets.getElement(e.y.value()));
        mst.add(e);
      }
    }

    return mst;
  }
}
