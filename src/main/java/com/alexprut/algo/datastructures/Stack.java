package com.alexprut.algo.datastructures;

/** LIFO */
public class Stack<T> {

  private LinkedList<T> list;

  Stack() {
    list = new LinkedList<>();
  }

  public void push(T value) {
    list.insertFront(value);
  }

  public int size() {
    return list.size();
  }

  public boolean empty() {
    return list.empty();
  }

  public T pop() throws Exception {
    return list.removeFront();
  }

  public void insert(T value) {
    push(value);
  }

  public void remove() throws Exception {
    pop();
  }
}
