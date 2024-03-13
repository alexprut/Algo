package com.alexprut.algo.datastructures;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class QuadTreeTest {

  @Test
  public void shouldInsertAndDelete() {
    class PlacesDecimalDegreesPoint extends QuadTree.Point {
      final String description;

      public PlacesDecimalDegreesPoint(float x, float y, String description) {
        super(x, y);
        this.description = description;
      }
    }

    QuadTree quadTree = new QuadTree(-90 + 90, -180 + 180, 90 + 90, 180 + 180, 2);

    quadTree.insert(
        new PlacesDecimalDegreesPoint(51.5014f + 90, -0.1419f + 180, "Buckingham Palace"));
    quadTree.insert(
        new PlacesDecimalDegreesPoint(51.5081f + 90, -0.0759f + 180, "Tower of London"));
    quadTree.insert(new PlacesDecimalDegreesPoint(51.5045f + 90, -0.0865f + 180, "The Shard"));
    quadTree.insert(new PlacesDecimalDegreesPoint(51.5073f + 90, -0.1657f + 180, "Hyde Park"));
    quadTree.insert(new PlacesDecimalDegreesPoint(51.5194f + 90, -0.1270f + 180, "British Museum"));

    List<QuadTree.Point> resultWorld = quadTree.search(-90 + 90, -180 + 180, 90 + 90, 180 + 180);

    Assertions.assertEquals(5, quadTree.size());
    Assertions.assertEquals(5, resultWorld.size());
    Assertions.assertFalse(quadTree.delete(new PlacesDecimalDegreesPoint(0, 0, "")));
    Assertions.assertEquals(5, resultWorld.size());
    Assertions.assertTrue(
        quadTree.delete(new PlacesDecimalDegreesPoint(51.5014f + 90, -0.1419f + 180, "")));
    Assertions.assertEquals(4, quadTree.size());
    Assertions.assertFalse(
        quadTree.delete(new PlacesDecimalDegreesPoint(51.5014f + 90, -0.1419f + 180, "")));
    Assertions.assertTrue(
        quadTree.delete(
            new PlacesDecimalDegreesPoint(51.5081f + 90, -0.0759f + 180, "Tower of London")));
    Assertions.assertEquals(3, quadTree.size());
    Assertions.assertTrue(
        quadTree.delete(new PlacesDecimalDegreesPoint(51.5045f + 90, -0.0865f + 180, "The Shard")));
    Assertions.assertTrue(
        quadTree.delete(new PlacesDecimalDegreesPoint(51.5073f + 90, -0.1657f + 180, "Hyde Park")));
    Assertions.assertTrue(
        quadTree.delete(
            new PlacesDecimalDegreesPoint(51.5194f + 90, -0.1270f + 180, "British Museum")));
    Assertions.assertEquals(0, quadTree.size());
  }

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

    Assertions.assertEquals(1, result.size());
    Assertions.assertEquals(10, quadTree.size());
    Assertions.assertEquals(10, quadTree.search(0, 0, 400, 400).size());
    Assertions.assertArrayEquals(
        new float[] {245, 238}, new float[] {result.get(0).first(), result.get(0).second()}, 0);
  }
}
