package com.alexprut.algo.datastructures;

import org.junit.Test;

public class BTreeTest {

	@Test
	public void shouldInsert() {
		BTree<Character> tree = new BTree<>(2);
		tree.insert('B');
		tree.insert('A');
		tree.insert('D');
		tree.insert('C');
		tree.insert('F');
		tree.insert('G');
		tree.insert('E');
	}

	@Test
	public void shouldSearch() {
    /*
                    -------[M]-----
                  /                 \
             [D,H]               [Q,  T,  X]
            /  |  \              /   |   |   \
       [B,C] [F,G] [J,K,L]  [N,P] [R,S] [V,W] [Y,Z]
     */
		BTree<Character> tree = new BTree<>(3);
	}
}
