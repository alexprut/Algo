package com.alexprut.algo.datastructures;

/**
 * A Stack is a data structure that serves as a collection of elements, with two principal
 * operations: "push", which adds an element to the collection, and "pop", which removes the most
 * recently added element that was not yet removed. The order in which elements come off a stack
 * gives rise to its alternative name, LIFO (last in, first out).
 *
 * <p>Example:
 *
 * <pre>
 * |24| → push(42) → |42| → pop() → |24|
 * |——|              |24|           |——|
 *                   |——|
 * </pre>
 *
 * @see <a
 *     href="https://en.wikipedia.org/wiki/Stack_(abstract_data_type)">https://en.wikipedia.org/wiki/Stack_(abstract_data_type)</a>
 */
public class Stack<T> {

  private final LinkedList<T> list;

  public Stack() {
    list = new LinkedList<>();
  }

  /**
   * Get the element in peek of the stack.
   *
   * <p>Time complexity: Θ(1)
   *
   * <p>Space complexity: Θ(1)
   *
   * @return the peek element
   * @throws Exception if the stack is empty
   */
  public T peek() throws Exception {
    T tmp = pop();
    push(tmp);
    return tmp;
  }

  /**
   * Add a new element on top of the stack.
   *
   * <p>Time complexity: Θ(1)
   *
   * <p>Space complexity: Θ(1)
   *
   * @param value the element to be added
   */
  public void push(T value) {
    list.insertFront(value);
  }

  /**
   * Get the number of elements within the stack.
   *
   * <p>Time complexity: Θ(1)
   *
   * <p>Space complexity: Θ(1)
   *
   * @return number of elements
   */
  public int size() {
    return list.size();
  }

  /**
   * Check if the stack is empty.
   *
   * <p>Time complexity: Θ(1)
   *
   * <p>Space complexity: Θ(1)
   *
   * @return true if the stack is empty
   */
  public boolean empty() {
    return list.empty();
  }

  /**
   * Removes and returns the peek element in the stack.
   *
   * <p>Time complexity: Θ(1)
   *
   * <p>Space complexity: Θ(1)
   *
   * @return the peek element
   * @throws Exception if the stack is empty
   */
  public T pop() throws Exception {
    return list.removeFront();
  }

  /**
   * Add a new element on top of the stack.
   *
   * <p>Time complexity: Θ(1)
   *
   * <p>Space complexity: Θ(1)
   *
   * @param value the new value to insert
   */
  public void insert(T value) {
    push(value);
  }

  /**
   * Removes the peek element in the stack without returning the element.
   *
   * <p>Time complexity: Θ(1)
   *
   * <p>Space complexity: Θ(1)
   *
   * @throws Exception if the stack is empty
   */
  public void remove() throws Exception {
    pop();
  }
}
