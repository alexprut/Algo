package com.alexprut.algo.datastructures;

/** FIFO */
public class Queue<T> {

  private DoubleLinkedList<T> list;

  public Queue() {
    list = new DoubleLinkedList<>();
  }

  public boolean empty() {
    return list.empty();
  }

  public void enqueue(T value) {
    list.insertFront(value);
  }

  public T dequeue() throws Exception {
    return list.removeBack();
  }

  public int size() {
    return list.size();
  }
}
