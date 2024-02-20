package com.alexprut.algo.datastructures;

import com.alexprut.algo.datastructures.DisjointSet.Element;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DisjointSetTest {

  @Test
  public void shouldUnionSets() {
    DisjointSet<Integer> sets = new DisjointSet<>();
    Element<Integer> a = sets.makeSet(1);
    Element<Integer> b = sets.makeSet(2);
    sets.union(a, b);
    Assertions.assertEquals(a.parent().getValue(), b.getValue());
    Assertions.assertEquals(b.parent().getValue(), b.getValue());
    Assertions.assertEquals(b.getRank(), 1);
  }
}
