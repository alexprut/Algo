package com.alexprut.algo.datastructures;

/**
 * FIFO
 *
 * <p>TODO
 *
 * @see <a
 *     href="https://en.wikipedia.org/wiki/Queue_(abstract_data_type)">https://en.wikipedia.org/wiki/Queue_(abstract_data_type)</a>
 */
public class Queue<T> {

  private final DoubleLinkedList<T> list;

  public Queue() {
    list = new DoubleLinkedList<>();
  }

  /**
   * Time complexity: Θ(1)
   *
   * @return
   */
  public boolean empty() {
    return list.empty();
  }

  /**
   * Time complexity: Θ(1)
   *
   * @param value
   */
  public void enqueue(T value) {
    list.insertFront(value);
  }

  /**
   * Time complexity: Θ(1)
   *
   * @return
   * @throws Exception
   */
  public T dequeue() throws Exception {
    return list.removeBack();
  }

  /**
   * Time complexity: Θ(1)
   *
   * @return
   */
  public int size() {
    return list.size();
  }
}
