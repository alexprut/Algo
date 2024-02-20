package com.alexprut.algo.datastructures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StackTest {

  @Test
  public void shouldSupportBasicOperations() {
    Stack<Integer> stack = new Stack<>();
    Assertions.assertEquals(0, stack.size());
    Assertions.assertTrue(stack.empty());

    stack.push(5);
    Assertions.assertEquals(1, stack.size());

    stack.insert(4);
    try {
      stack.remove();
    } catch (Exception e) {

    }

    stack.push(4);
    int value;
    try {
      value = stack.pop();
    } catch (Exception e) {
      value = -1;
    }
    Assertions.assertEquals(4, value);
    Assertions.assertEquals(1, stack.size());
  }
}
