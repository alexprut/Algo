package com.alexprut.algo.datastructures;

public class Pair<F, S> {

  private F first;
  private S second;

  public Pair(F first, S second) {
    this.first = first;
    this.second = second;
  }

  public F first() {
    return this.first;
  }

  public S second() {
    return this.second;
  }
}
