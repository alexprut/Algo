package com.alexprut.algo.datastructures;

/**
 * FIFO
 */
public class Queue {

  private DoubleLinkedList list;

  Queue() {
    list = new DoubleLinkedList();
  }

  public boolean empty() {
    return list.empty();
  }

  public void enqueue(int value) {
    list.insertFront(value);
  }

  public int dequeue() throws Exception {
    return list.removeBack();
  }

  public int size() {
    return list.size();
  }
}
