package com.alexprut.algo.datastructures;

import org.junit.Assert;
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
	public void shouldInsertAndSearchDistinct() {
		BTree<Integer> tree = new BTree<>(2);
		for (int i = 0; i < 10; i++) {
			tree.insert(i);
		}
		for (int i = 0; i < 10; i++) {
			Assert.assertTrue(tree.search(i));
		}
		for (int i = 10; i < 100; i++) {
			Assert.assertFalse(tree.search(i));
		}
	}

	@Test
	public void shouldInsertAndSearchDuplicates() {
		BTree<Integer> tree = new BTree<>(2);
		for (int i = 0; i < 10; i++) {
			tree.insert(0);
		}
		for (int i = 0; i < 10; i++) {
			tree.insert(1);
		}
		Assert.assertTrue(tree.search(0));
		Assert.assertTrue(tree.search(1));
		Assert.assertFalse(tree.search(2));
	}

	@Test
	public void shouldSplitChild() {
    /*
            [A,F]                  [A,C,F]
              |          ->         /   \
           [B,C,D]                [B]   [D]
     */
		BTree.Node<Character> child = new BTree.Node<>();
		child.key.add('B');
		child.key.add('C');
		child.key.add('D');
		child.isLeaf = true;

		BTree.Node<Character> root = new BTree.Node<>();
		root.isLeaf = false;
		root.key.add('A');
		root.key.add('F');
		root.children.add(null);
		root.children.add(child);
		root.children.add(null);

		BTree<Character> test = new BTree<>(2);
		test.splitChild(root, 1);

		Assert.assertEquals(4, root.children.size());
		Assert.assertEquals(3, root.key.size());
		Assert.assertEquals(new Character('A'), root.key.get(0));
		Assert.assertEquals(new Character('C'), root.key.get(1));
		Assert.assertEquals(new Character('F'), root.key.get(2));
		Assert.assertEquals(new Character('B'), root.children.get(1).key.get(0));
		Assert.assertEquals(1, root.children.get(1).key.size());
		Assert.assertEquals(new Character('D'), root.children.get(2).key.get(0));
		Assert.assertEquals(1, root.children.get(2).key.size());
	}
}
