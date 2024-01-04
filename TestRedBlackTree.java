
// --== CS400 Project One File Header ==--
// Name: Ethan Geoffrey Wijaya
// Email: egwijaya@wisc.edu
// Team: red
// Group: CI
// TA: Tingjia Cao
// Lecturer: Florian Heimerl
// Notes to Grader: 
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestRedBlackTree {

	/**
	 * Tests if all red black properties hold when inserting into a small tree
	 */
	@Test
	public static void testInsertSmallTreeProperties() {
		RedBlackTree<Integer> tree = new RedBlackTree<Integer>();
		tree.insert(7);
		tree.insert(14);
		assertEquals("[7, 14]", tree.root.toString(), "Tree improperly balanced");
		assertTrue(tree.root.isBlack && !tree.root.rightChild.isBlack, "Tree improperly balanced");
	}

	/**
	 * Tests for correct implementation of a black or null uncle case in insert()
	 */
	@Test
	public static void testInsertBlackUncle() {
		RedBlackTree<Integer> tree = new RedBlackTree<Integer>();
		tree.insert(7);
		tree.insert(14);
		tree.insert(18);
		assertEquals("[14, 7, 18]", tree.root.toString(), "Tree improperly balanced");
		assertTrue(tree.root.isBlack && !tree.root.leftChild.isBlack && !tree.root.rightChild.isBlack,
				"Tree improperly colored");
	}

	/**
	 * Tests for correct implementation of a red uncle case in insert()
	 */
	@Test
	public static void testInsertRedUncle() {
		RedBlackTree<Integer> tree = new RedBlackTree<Integer>();
		tree.insert(7);
		tree.insert(14);
		tree.insert(18);
		tree.insert(23);
		assertEquals("[14, 7, 18, 23]", tree.root.toString(), "Tree improperly balanced");
		assertTrue(tree.root.isBlack && tree.root.leftChild.isBlack && tree.root.rightChild.isBlack
				&& !tree.root.rightChild.rightChild.isBlack, "Tree improperly colored");
	}

	/**
	 * Tests implementation of double rotations (Right-left/Left-right) in the black
	 * uncle case in insert()
	 */
	@Test
	public static void testInsertBlackUncleDoubleRotations() {
		RedBlackTree<Integer> tree = new RedBlackTree<Integer>();
		tree.insert(7);
		tree.insert(14);
		tree.insert(18);
		tree.insert(23);
		tree.insert(20);
		assertEquals("[14, 7, 20, 18, 23]", tree.root.toString(), "Tree improperly balanced");
		assertTrue(
				tree.root.isBlack && tree.root.leftChild.isBlack && tree.root.rightChild.isBlack
						&& !tree.root.rightChild.rightChild.isBlack && !tree.root.rightChild.leftChild.isBlack,
				"Tree improperly colored");
	}

	/**
	 * Test if the tree can fix itself recursively in insert() so that all properties hold
	 */
	@Test
	public static void testInsertRecursiveFixes() {
		RedBlackTree<Integer> tree = new RedBlackTree<Integer>();
		tree.insert(7);
		tree.insert(14);
		tree.insert(18);
		tree.insert(23);
		tree.insert(20);
		tree.insert(29);
		tree.insert(25);
		tree.insert(27);
		assertEquals("[20, 14, 25, 7, 18, 23, 29, 27]", tree.root.toString(), "Tree improperly balanced");
		assertTrue(tree.root.isBlack && !tree.root.leftChild.isBlack && !tree.root.rightChild.isBlack
				&& tree.root.leftChild.leftChild.isBlack && tree.root.leftChild.rightChild.isBlack
				&& tree.root.rightChild.leftChild.isBlack && tree.root.rightChild.rightChild.isBlack
				&& !tree.root.rightChild.rightChild.leftChild.isBlack, "Tree improperly colored");
	}

	/**
	 * Runs all tests.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		testInsertSmallTreeProperties();
		testInsertBlackUncle();
		testInsertRedUncle();
		testInsertBlackUncleDoubleRotations();
		testInsertRecursiveFixes();

	}

}
