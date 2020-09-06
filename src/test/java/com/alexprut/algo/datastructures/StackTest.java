package com.alexprut.algo.datastructures;

import org.junit.Assert;
import org.junit.Test;

public class StackTest {

  @Test
  public void shouldSupportBasicOperations() {
    Stack<Integer> stack = new Stack<>();
    Assert.assertEquals(0, stack.size());
    Assert.assertTrue(stack.empty());

    stack.push(5);
    Assert.assertEquals(1, stack.size());

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
    Assert.assertEquals(4, value);
    Assert.assertEquals(1, stack.size());
  }
}
