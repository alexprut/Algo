package com.alexprut.algo.algorithms.graph.mst;

import com.alexprut.algo.algorithms.graph.Graph;
import com.alexprut.algo.algorithms.graph.Graph.Edge;
import com.alexprut.algo.datastructures.DisjointSet;
import java.util.ArrayList;
import java.util.Collections;

public class Kruskal {

  public static ArrayList<Graph.Edge> kruskal(ArrayList<Graph.Edge> edges, int n) {
    ArrayList<Graph.Edge> mst = new ArrayList<>();
    DisjointSet sets = new DisjointSet();

    for (int i = 0; i < n; i++) {
      sets.makeSet(i);
    }

    Collections.sort(edges);

    for (int i = 0; i < edges.size(); i++) {
      Edge e = edges.get(i);
      if (sets.findSet(e.x.value()) != sets.findSet(e.y.value())) {
        sets.union(sets.getElement(e.x.value()), sets.getElement(e.y.value()));
        mst.add(e);
      }
    }

    return mst;
  }
}
