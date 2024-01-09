package com.alexprut.algo.datastructures;

import java.util.List;
import org.junit.Assert;
import org.junit.Test;

public class QuadTreeTest {

  @Test
  public void shouldInsertAndSearchForRange() {
    QuadTree quadTree = new QuadTree(0, 0, 400, 400, 4);

    float[][] points =
        new float[][] {
          {401, 401},
          {21, 25},
          {55, 53},
          {70, 318},
          {98, 302},
          {49, 229},
          {135, 229},
          {224, 292},
          {206, 321},
          {197, 258},
          {245, 238}
        };

    for (float[] floats : points) {
      QuadTree.Point point = new QuadTree.Point(floats[0], floats[1]);
      quadTree.insert(point);
    }

    List<QuadTree.Point> result = quadTree.search(200, 200, 250, 250);

    Assert.assertEquals(1, result.size());
    Assert.assertEquals(10, quadTree.size());
    Assert.assertEquals(10, quadTree.search(0, 0, 400, 400).size());
    Assert.assertArrayEquals(
        new float[] {245, 238}, new float[] {result.get(0).first(), result.get(0).second()}, 0);
  }
}
