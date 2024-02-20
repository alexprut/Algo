package com.alexprut.algo.datastructures;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TrieTest {

  @Test
  public void shouldInsertFindAndRemove() {
    /*
     *                ""
     *               / | \
     *              d  s  a
     *             /   |   \
     *            a    t    l
     *           /     |     \
     *          t      r      g
     *         / \     |      |
     *       e*   a*   u      o*
     *                 |      |
     *                 c      r
     *                 |      |
     *                 t*     i
     *                 |      |
     *                 u      t
     *                 |      |
     *                 r      h
     *                 |      |
     *                 e*     m
     */
    Trie trie = new Trie();
    Assertions.assertEquals(0, trie.size());
    trie.insert("data");
    trie.insert("date");
    trie.insert("structure");
    trie.insert("struct");
    trie.insert("algorithm");
    trie.insert("algo");

    Assertions.assertEquals(6, trie.size());
    Assertions.assertFalse(trie.search(""));
    Assertions.assertFalse(trie.searchPrefix(""));
    Assertions.assertTrue(trie.search("algo"));
    Assertions.assertTrue(trie.search("algorithm"));
    Assertions.assertTrue(trie.searchPrefix("algorithm"));
    Assertions.assertTrue(trie.searchPrefix("algorith"));
    Assertions.assertFalse(trie.searchPrefix("algorithms"));
    Assertions.assertTrue(trie.searchPrefix("structure"));
    Assertions.assertTrue(trie.searchPrefix("data"));
    Assertions.assertEquals(2, trie.sizePrefix("alg"));
    Assertions.assertEquals(2, trie.sizePrefix("algo"));

    Assertions.assertFalse(trie.remove(""));
    Assertions.assertFalse(trie.remove("alg"));
    Assertions.assertFalse(trie.remove("algos"));
    Assertions.assertEquals(6, trie.size());
    /*
     *                 ""
     *               / | \
     *              d  s  a
     *             /   |   \
     *            a    t    l
     *           /     |     \
     *          t      r      g
     *         / \     |      |
     *       e*   a*   u      o
     *                 |      |
     *                 c      r
     *                 |      |
     *                 t*     i
     *                 |      |
     *                 u      t
     *                 |      |
     *                 r      h
     *                 |      |
     *                 e*     m
     */
    Assertions.assertTrue(trie.remove("algo"));
    Assertions.assertEquals(5, trie.size());

    Assertions.assertFalse(trie.removePrefix(""));
    /*
     *                 ""
     *                 | \
     *                 s  a
     *                 |   \
     *                 t    l
     *                 |     \
     *                 r      g
     *                 |      |
     *                 u      o*
     *                 |      |
     *                 c      r
     *                 |      |
     *                 t*     i
     *                 |      |
     *                 u      t
     *                 |      |
     *                 r      h
     *                 |      |
     *                 e*     m
     */
    Assertions.assertTrue(trie.removePrefix("dat"));
    Assertions.assertEquals(3, trie.size());

    Assertions.assertTrue(trie.removePrefix("s"));
    Assertions.assertTrue(trie.removePrefix("a"));
    Assertions.assertEquals(0, trie.size());
  }
}
