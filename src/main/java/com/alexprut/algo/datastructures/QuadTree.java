package com.alexprut.algo.datastructures;

import java.util.ArrayList;
import java.util.List;

/**
 * A quadtree is a tree data structure in which each internal node has exactly four children.
 * Quadtrees are most often used to partition a two-dimensional space by recursively subdividing it
 * into four quadrants or regions. The data associated with a leaf cell varies by application, but
 * the leaf cell represents a "unit of interesting spatial information".
 *
 * <p>Example: Area size between 0 and 50, with 2 points: P1 in (25,10) and P2 in (40,40)
 *
 * <pre>
 *   |--------|--------| (50,50)
 *   |        |    .P2 |
 *   |        |        |
 *   |--------|--------| (0,25)
 *   |        |        |
 *   |  .P1   |        |
 *   |--------|--------|
 * (0,0)   (25,0)   (50,0)
 * </pre>
 *
 * @see <a href="https://en.wikipedia.org/wiki/Quadtree">https://en.wikipedia.org/wiki/Quadtree</a>
 */
public class QuadTree {
  private final int MAX_POINTS;
  private final Region area;
  private final List<Point> points = new ArrayList<>();
  private final List<QuadTree> quadTrees = new ArrayList<>();
  private int size;

  /**
   * @param x1 first point, x coordinate of the range
   * @param y1 first point, y coordinate of the range
   * @param x2 second point, x coordinate of the range
   * @param y2 second point, x coordinate of the range
   * @param maxPoints number of points per quadtree node
   */
  public QuadTree(float x1, float y1, float x2, float y2, int maxPoints) {
    MAX_POINTS = maxPoints;
    this.area = new Region(x1, y1, x2, y2);
  }

  protected QuadTree(Region region, int maxPoints) {
    MAX_POINTS = maxPoints;
    this.area = region;
  }

  /**
   * Insert an element within the quadtree.
   *
   * <p>Time complexity: O(logn), O(n) worst case
   *
   * <p>Space complexity: O(n)
   *
   * @param point the point to be inserted
   * @return true if the element was successfully inserted, false otherwise
   */
  public boolean insert(Point point) {
    if (this.area.containsPoint(point)) {
      size++;
      if (this.points.size() < MAX_POINTS) {
        this.points.add(point);
        return true;
      } else {
        if (this.quadTrees.isEmpty()) {
          createQuadrants();
        }
        return addPointToOneQuadrant(point);
      }
    }
    return false;
  }

  /**
   * Get the number of points contained within the quadtree.
   *
   * <p>Time complexity: Θ(1)
   *
   * <p>Space complexity: Θ(1)
   *
   * @return the number of points
   */
  public int size() {
    return this.size;
  }

  /**
   * Utility method, add the point to one of the four quadrants.
   *
   * <p>Time complexity: O(logn), O(n) in worst case
   *
   * <p>Space complexity: O(n)
   *
   * @param point the point to be added
   * @return true if the point has been added to a quadrant
   */
  private boolean addPointToOneQuadrant(Point point) {
    boolean isPointAdded;
    for (int i = 0; i < 4; i++) {
      isPointAdded = this.quadTrees.get(i).insert(point);
      if (isPointAdded) {
        return true;
      }
    }
    return false;
  }

  /**
   * Utility method, create the four quadrants.
   *
   * <p>Time complexity: O(1)
   *
   * <p>Space complexity: O(1)
   */
  private void createQuadrants() {
    Region region;
    for (int i = 0; i < 4; i++) {
      region = this.area.getQuadrant(i);
      quadTrees.add(new QuadTree(region, MAX_POINTS));
    }
  }

  /**
   * Retrieve all the points withing a range.
   *
   * <p>Time complexity: O(logn), O(n) in worst case
   *
   * <p>Space complexity: O(n)
   *
   * @param x1 first point, x coordinate of the range
   * @param y1 first point, y coordinate of the range
   * @param x2 second point, x coordinate of the range
   * @param y2 second point, x coordinate of the range
   * @return points found within the range
   */
  public List<Point> search(float x1, float y1, float x2, float y2) {
    List<Point> matches = new ArrayList<>();
    Region searchRegion = new Region(x1, y1, x2, y2);
    if (!this.area.doesOverlap(searchRegion)) {
      return matches;
    }
    for (Point point : points) {
      if (searchRegion.containsPoint(point)) {
        matches.add(point);
      }
    }
    if (!this.quadTrees.isEmpty()) {
      for (int i = 0; i < 4; i++) {
        matches.addAll(quadTrees.get(i).search(x1, y1, x2, y2));
      }
    }
    return matches;
  }

  /**
   * Delete a Point from the QuadTree if exists.
   *
   * <p>Time complexity: O(logn), O(n) in worst case
   *
   * <p>Space complexity: O(1)
   *
   * @param point to be deleted
   * @return true if the point was deleted, false otherwise
   */
  public boolean delete(Point point) {
    if (!area.containsPoint(point)) {
      return false;
    }

    boolean removed = points.remove(point);
    if (removed) {
      size--;
      return true;
    }

    for (QuadTree quadTree : quadTrees) {
      if (quadTree.delete(point)) {
        size--;
        return true;
      }
    }

    return false;
  }

  /** Utility class, specifies a region within the tree. */
  protected static class Region {
    private final float x1;
    private final float y1;
    private final float x2;
    private final float y2;

    public Region(float x1, float y1, float x2, float y2) {
      if (x1 >= x2 || y1 >= y2)
        throw new IllegalArgumentException("(x1,y1) should be lesser than (x2,y2)");
      this.x1 = x1;
      this.y1 = y1;
      this.x2 = x2;
      this.y2 = y2;
    }

    /**
     * Creates a region for one of the four specified quadrant.
     *
     * <p>Time complexity: O(1)
     *
     * <p>Space complexity: O(1)
     *
     * @param quadrantIndex the quadrant index: 0 for South West, 1 for North West, 2 for North Est,
     *     3 for South Est
     * @return the created region
     */
    public Region getQuadrant(int quadrantIndex) {
      float quadrantWidth = (this.x2 - this.x1) / 2;
      float quadrantHeight = (this.y2 - this.y1) / 2;

      switch (quadrantIndex) {
        case 0: // South West
          return new Region(x1, y1, x1 + quadrantWidth, y1 + quadrantHeight);
        case 1: // North West
          return new Region(x1, y1 + quadrantHeight, x1 + quadrantWidth, y2);
        case 2: // North Est
          return new Region(x1 + quadrantWidth, y1 + quadrantHeight, x2, y2);
        case 3: // South Est
          return new Region(x1 + quadrantWidth, y1, x2, y1 + quadrantHeight);
      }
      return null;
    }

    /**
     * Checks if a region contains the point. Consider left and top side to be inclusive for points
     * on border.
     *
     * <p>Time complexity: O(1)
     *
     * <p>Space complexity: O(1)
     *
     * @param point the point to be checked
     * @return true if the region contains the point
     */
    public boolean containsPoint(Point point) {
      return point.getX() >= this.x1
          && point.getX() < this.x2
          && point.getY() >= this.y1
          && point.getY() < this.y2;
    }

    /**
     * Checks if the tested region overlaps with the current one.
     *
     * <p>Time complexity: O(1)
     *
     * <p>Space complexity: O(1)
     *
     * @param testRegion the region to be tested
     * @return true if the region overlaps with the current one
     */
    public boolean doesOverlap(Region testRegion) {
      if (testRegion.x2 < this.x1) {
        return false;
      }
      if (testRegion.x1 > this.x2) {
        return false;
      }
      if (testRegion.y1 > this.y2) {
        return false;
      }
      if (testRegion.y2 < this.y1) {
        return false;
      }
      return true;
    }
  }

  /** A point within the Quadtree */
  public static class Point extends Pair<Float, Float> {

    public Point(float x, float y) {
      super(x, y);
    }

    public float getX() {
      return first();
    }

    public float getY() {
      return second();
    }

    @Override
    public boolean equals(Object o) {
      Point that = (Point) o;
      return this.getX() == that.getX() && this.getY() == that.getY();
    }
  }
}
