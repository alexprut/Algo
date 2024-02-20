package com.alexprut.algo.datastructures;

import com.alexprut.algo.datastructures.BTree.Node;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BTreeTest {

  private BTree.Node<Character> createBTreeFixture() {
    /*
    Initial B-Tree

                     -------- [P] --------
                    /                     \
            ---[C,G,M]-----             --[T,X]----
           /     |  \      \           /    |      \
      [A,B] [D,E,F] [J,K,L] [N,O]   [Q,R,S] [U,V] [Y,Z]

     */
    BTree.Node<Character> nodeA = new Node<>();
    nodeA.isLeaf = true;
    nodeA.key.add('A');
    nodeA.key.add('B');
    BTree.Node<Character> nodeD = new Node<>();
    nodeD.isLeaf = true;
    nodeD.key.add('D');
    nodeD.key.add('E');
    nodeD.key.add('F');
    BTree.Node<Character> nodeJ = new Node<>();
    nodeJ.isLeaf = true;
    nodeJ.key.add('J');
    nodeJ.key.add('K');
    nodeJ.key.add('L');
    BTree.Node<Character> nodeN = new Node<>();
    nodeN.isLeaf = true;
    nodeN.key.add('N');
    nodeN.key.add('O');
    BTree.Node<Character> nodeQ = new Node<>();
    nodeQ.isLeaf = true;
    nodeQ.key.add('Q');
    nodeQ.key.add('R');
    nodeQ.key.add('S');
    BTree.Node<Character> nodeU = new Node<>();
    nodeU.isLeaf = true;
    nodeU.key.add('U');
    nodeU.key.add('V');
    BTree.Node<Character> nodeY = new Node<>();
    nodeY.isLeaf = true;
    nodeY.key.add('Y');
    nodeY.key.add('Z');
    BTree.Node<Character> nodeC = new Node<>();
    nodeC.isLeaf = false;
    nodeC.key.add('C');
    nodeC.key.add('G');
    nodeC.key.add('M');
    nodeC.children.add(nodeA);
    nodeC.children.add(nodeD);
    nodeC.children.add(nodeJ);
    nodeC.children.add(nodeN);
    BTree.Node<Character> nodeT = new Node<>();
    nodeT.isLeaf = false;
    nodeT.key.add('T');
    nodeT.key.add('X');
    nodeT.children.add(nodeQ);
    nodeT.children.add(nodeU);
    nodeT.children.add(nodeY);
    BTree.Node<Character> root = new Node<>();
    root.isLeaf = false;
    root.key.add('P');
    root.children.add(nodeC);
    root.children.add(nodeT);

    return root;
  }

  @Test
  public void shouldGetPredecessor() {
    BTree.Node<Character> root = createBTreeFixture();
    BTree<Character> test = new BTree<>(3);
    Pair<Node<Character>, Integer> foundNode = test.search(root, 'P');
    Pair<Node<Character>, Integer> foundPredecessorNode =
        test.getPredecessor(foundNode.first().children.get(foundNode.second()));

    Assertions.assertEquals(
        Character.valueOf('O'),
        foundPredecessorNode.first().key.get(foundPredecessorNode.second()));
  }

  @Test
  public void shouldGetSuccessor() {
    BTree.Node<Character> root = createBTreeFixture();
    BTree<Character> test = new BTree<>(3);
    Pair<Node<Character>, Integer> foundNode = test.search(root, 'P');
    Pair<Node<Character>, Integer> foundSuccessorNode =
        test.getSuccessor(foundNode.first().children.get(foundNode.second() + 1));

    Assertions.assertEquals(
        Character.valueOf('Q'), foundSuccessorNode.first().key.get(foundSuccessorNode.second()));
  }

  @Test
  public void shouldDelete() {
    BTree.Node<Character> root = createBTreeFixture();
    BTree<Character> test = new BTree<>(3);

    /*
    Case 1

                     -------- [P] --------
                    /                     \
            ---[C,G,M]-----             --[T,X]----
           /     |  \      \           /    |      \
      [A,B]   [D,E] [J,K,L] [N,O]   [Q,R,S] [U,V] [Y,Z]

       */
    test.delete(root, 'F');

    /*
    Case 2a

                     -------- [P] --------
                    /                     \
            ---[C,G,L]-----             --[T,X]----
           /     |  \      \           /    |      \
      [A,B]   [D,E] [J,K]   [N,O]   [Q,R,S] [U,V] [Y,Z]

       */
    test.delete(root, 'M');

    /*
    Case 2c

                     -------- [P] --------
                    /                     \
            ---[C,L]-----             --[T,X]----
           /     |       \           /    |      \
      [A,B]  [D,E,J,K]   [N,O]   [Q,R,S] [U,V] [Y,Z]

       */
    test.delete(root, 'G');

    /*
    Case 3b

          ------------[C,L,P,T,X]--------------
         /     /       /           \    \      \
      [A,B]  [E,J,K]  [N,O]   [Q,R,S] [U,V] [Y,Z]

       */
    test.delete(root, 'D');

    /*
    Case 3a

          ------------[E,L,P,T,X]----------
         /      /       /        \    \    \
      [A,C]   [J,K]  [N,O]   [Q,R,S] [U,V] [Y,Z]

       */
    test.delete(root, 'B');

    test.delete(root, 'N');
    test.delete(root, 'O');
    test.delete(root, 'Q');
    test.delete(root, 'R');
    test.delete(root, 'S');
    test.delete(root, 'U');
    test.delete(root, 'V');
    test.delete(root, 'Y');
    test.delete(root, 'Z');
    test.delete(root, 'P');
    test.delete(root, 'T');
    test.delete(root, 'X');
    test.delete(root, 'J');
    test.delete(root, 'K');
    test.delete(root, 'A');
    test.delete(root, 'C');
    test.delete(root, 'E');
    test.delete(root, 'L');
  }

  @Test
  public void shouldInsert() {
    // TODO add graphical explanation
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
      Assertions.assertTrue(tree.search(i));
    }
    for (int i = 10; i < 100; i++) {
      Assertions.assertFalse(tree.search(i));
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
    Assertions.assertTrue(tree.search(0));
    Assertions.assertTrue(tree.search(1));
    Assertions.assertFalse(tree.search(2));
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

    Assertions.assertEquals(4, root.children.size());
    Assertions.assertEquals(3, root.key.size());
    Assertions.assertEquals(Character.valueOf('A'), root.key.get(0));
    Assertions.assertEquals(Character.valueOf('C'), root.key.get(1));
    Assertions.assertEquals(Character.valueOf('F'), root.key.get(2));
    Assertions.assertEquals(Character.valueOf('B'), root.children.get(1).key.get(0));
    Assertions.assertEquals(1, root.children.get(1).key.size());
    Assertions.assertEquals(Character.valueOf('D'), root.children.get(2).key.get(0));
    Assertions.assertEquals(1, root.children.get(2).key.size());
  }
}
