package com.alexprut.algo.datastructures;

import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

public class MerkleTreeTest {

	/*
		hash(Merkle) -> b96c4732b691beb72b3a8f28c59897bd58f618dbac1c3b0119bcea85ada0212f
		hash(Tree) -> 53b7c609f4e7d52e2c25395f0807beb2bdfb2d5835d70b11ffc275c650297d3d
		hash(Data) -> 26eaa1c649e57cf5e19d2c433055982f8726c13f426e701c32160aac1835328b
		hash(Structure) -> 24482fdb1117455ee1e14a2660da3ba3563df480afd4322c10f074454a74d732
		hash(hash(Merkle) + hash(Tree)) -> 1f050a00c0a0454efa54a4c1ff48b8d02c8284d4f9e1aa4dfbd934254f6ce3ad
		hash(hash(Data) + hash(Structure)) -> 44aeeeb05b1a21f0e449614b5d1df8b8c50ec7a80f183787f86e09a52bdf26f8
		hash(hash(hash(Merkle) + hash(Tree)) + hash(hash(Data) + hash(Structure))) -> ffb45343c6dbeb8491483ffa9020420e029909b263480ea37b21e3cad75b11f2
		 */
	private final ArrayList<String> elements = new ArrayList<>(
			Arrays.asList("Merkle", "Tree", "Data", "Structure")
	);
	private final MerkleTree tree = new MerkleTree(elements);

	@Test
	public void shouldGetRoot() {
		Assert.assertEquals(
				"ffb45343c6dbeb8491483ffa9020420e029909b263480ea37b21e3cad75b11f2",
				tree.root()
		);
	}

	@Test
	public void shouldVerifyPath() {
		Assert.assertTrue(MerkleTree.verify(
				"Merkle",
				"ffb45343c6dbeb8491483ffa9020420e029909b263480ea37b21e3cad75b11f2",
				new ArrayList<>(
						Arrays.asList(
								"53b7c609f4e7d52e2c25395f0807beb2bdfb2d5835d70b11ffc275c650297d3d",
								"44aeeeb05b1a21f0e449614b5d1df8b8c50ec7a80f183787f86e09a52bdf26f8"
						)
				)
		));
		Assert.assertFalse(MerkleTree.verify(
				elements.get(1),
				tree.root(),
				tree.getProofPath(0)
		));
	}

	@Test
	public void shouldGetProofPath() {
		ArrayList<String> expectedPath0 = new ArrayList<>(
				Arrays.asList(
						"53b7c609f4e7d52e2c25395f0807beb2bdfb2d5835d70b11ffc275c650297d3d",
						"44aeeeb05b1a21f0e449614b5d1df8b8c50ec7a80f183787f86e09a52bdf26f8"
				)
		);

		ArrayList<String> expectedPath3 = new ArrayList<>(
				Arrays.asList(
						"26eaa1c649e57cf5e19d2c433055982f8726c13f426e701c32160aac1835328b",
						"1f050a00c0a0454efa54a4c1ff48b8d02c8284d4f9e1aa4dfbd934254f6ce3ad"
				)
		);

		Assert.assertEquals(expectedPath0, tree.getProofPath(0));
		Assert.assertEquals(expectedPath3, tree.getProofPath(3));
	}

	@Test
	public void shouldHash() {
		String fixture = "Merkle Tree";
		Assert.assertEquals(
				"53abb8eaa2e2de187ddfb96d420896348c7f4c478fbf6e28b18ed168bfe0905e",
				MerkleTree.hash(fixture));
	}
}
