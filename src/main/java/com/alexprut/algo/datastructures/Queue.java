package com.alexprut.algo.datastructures;

/** FIFO */
public class Queue<T> {

  private DoubleLinkedList<T> list;

  public Queue() {
    list = new DoubleLinkedList<>();
  }

  /** Time complexity: Θ(1) */
  public boolean empty() {
    return list.empty();
  }

  /** Time complexity: Θ(1) */
  public void enqueue(T value) {
    list.insertFront(value);
  }

  /** Time complexity: Θ(1) */
  public T dequeue() throws Exception {
    return list.removeBack();
  }

  /** Time complexity: Θ(1) */
  public int size() {
    return list.size();
  }
}
