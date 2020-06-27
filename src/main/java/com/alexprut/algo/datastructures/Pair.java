package com.alexprut.algo.datastructures;

/**
 * Utility data structure for creating tuples of other data structures.
 *
 * @param <F> type of first element
 * @param <S> type of second element
 */
public class Pair<F, S> {

  private final F first;
  private final S second;

  public Pair(F first, S second) {
    this.first = first;
    this.second = second;
  }

  /**
   * Get the first element.
   *
   * @return first element
   */
  public F first() {
    return this.first;
  }

  /**
   * Get the second element.
   *
   * @return second element
   */
  public S second() {
    return this.second;
  }
}
