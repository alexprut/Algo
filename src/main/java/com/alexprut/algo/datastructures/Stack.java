package com.alexprut.algo.datastructures;

/**
 * LIFO
 */
public class Stack {

  private LinkedList list;

  Stack() {
    list = new LinkedList();
  }

  public void push(int value) {
    list.insertFront(value);
  }

  public int size() {
    return list.size();
  }

  public boolean empty() {
    return list.empty();
  }

  public int pop() throws Exception {
    return list.removeFront();
  }

  public void insert(int value) {
    push(value);
  }

  public void remove() throws Exception {
    pop();
  }
}
