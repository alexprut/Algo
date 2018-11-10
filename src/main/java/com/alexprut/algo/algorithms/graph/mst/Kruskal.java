package com.alexprut.algo.algorithms.graph.mst;

import com.alexprut.algo.datastructures.Graph;
import com.alexprut.algo.datastructures.Graph.Edge;
import com.alexprut.algo.datastructures.DisjointSet;
import java.util.ArrayList;
import java.util.Collections;

public class Kruskal {

  /**
   * Finds the minimum spanning tree (MST) of an undirected graph
   *
   * Time complexity: O(|E|log|V|)
   *
   * Space complexity: TODO
   */
  public static ArrayList<Graph.Edge> kruskal(ArrayList<Graph.Edge> edges, int n) {
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
