package com.alexprut.algo.datastructures;

import com.alexprut.algo.datastructures.DisjointSet.Element;
import org.junit.Assert;
import org.junit.Test;

public class DisjointSetTest {

  @Test
  public void shouldUnionSets() {
    DisjointSet sets = new DisjointSet();
    Element a = sets.makeSet(1);
    Element b = sets.makeSet(2);
    sets.union(a, b);
    Assert.assertEquals(a.parent().getValue(), b.getValue());
    Assert.assertEquals(b.parent().getValue(), b.getValue());
    Assert.assertEquals(b.getRank(), 1);
  }

}
