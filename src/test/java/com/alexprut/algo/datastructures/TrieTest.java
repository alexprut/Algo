package com.alexprut.algo.datastructures;

import org.junit.Assert;
import org.junit.Test;

public class TrieTest {

  @Test
  public void shouldInsertAndFind() {
    Trie trie = new Trie();
    trie.insert("data");
    trie.insert("structure");
    trie.insert("algorithms");

    Assert.assertTrue(trie.contains("algo"));
    Assert.assertTrue(trie.contains("algorithm"));
    Assert.assertTrue(trie.containsWord("algorithms"));
    Assert.assertTrue(trie.containsWord("structure"));
    Assert.assertTrue(trie.containsWord("data"));
  }
}
