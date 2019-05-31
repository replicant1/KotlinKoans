package com.bailey.rod.hackerrank.trees.bst;

import java.io.FileNotFoundException;

/**
 * Hidden stub code will pass a root argument to the function below.
 * Complete the function to solve the challenge. Hint: you may want to write one or more helper functions.
 */

class Node {
	int data;
	Node left;
	Node right;

	@Override
	public String toString() {
		return "NODE(" + data + ")";
	}
}

class BST {
	private static final boolean debug = false;

	private void trace(String s) {
		if (debug)
			System.out.println(s);
	}

	/**
	 * @param root Root node of the subtree
	 * @return true if the subtree rooted at [root] is a valid binary sort tree.
	 */
	boolean checkBST(Node root) {
		boolean leftTest = true;
		boolean rightTest = true;

		trace(root.toString());

		if (root.left != null) {
			if (root.left.data < root.data) {
				leftTest = checkBST(root.left);
			} else {
				leftTest = false;
			}
		}

		if (root.right != null) {
			if (root.data < root.right.data) {
				rightTest = checkBST(root.right);
			} else {
				rightTest = false;
			}
		}

		trace(root + " : returning " + (leftTest && rightTest) + " because leftTest=" +
				leftTest + " and rightTest=" + rightTest);

		return leftTest && rightTest;
	}

	public static void main(String[] args) throws FileNotFoundException {
		// Put together a tree programmatically by linking together nodes.
		Node tree = createSample3();
		System.out.println(new BST().checkBST(tree));
	}

	private static Node createSample1() {
		Node[] nodes = new Node[6];
		for (int i = 0; i < 6; i++) {
			nodes[i] = new Node();
			nodes[i].data = i + 1;
		}
		set(3, 2, 4, nodes);
		set(2, 1, 0, nodes);
		set(4, 5, 6, nodes);

		return nodes[2];
	}

	private static Node createSample2() {
		Node[] nodes = new Node[6];
		for (int i = 0; i < 6; i++) {
			nodes[i] = new Node();
			nodes[i].data = i + 1;
		}
		set(3, 2, 5, nodes);
		set(2, 1, 0, nodes);
		set(5, 6, 1, nodes);

		return nodes[2];
	}

	private static Node createSample3() {
		Node[] nodes = new Node[7];
		for (int i = 0; i < 7; i++) {
			nodes[i] = new Node();
			nodes[i].data = i + 1;
		}
		set(4, 2, 6, nodes);
		set(2, 1, 3, nodes);
		set(6, 5, 7, nodes);

		return nodes[3];
	}

	private static void set(int parent, int left, int right, Node[] nodes) {
		if (left >= 1)
			nodes[parent - 1].left = nodes[left - 1];
		if (right >= 1)
			nodes[parent - 1].right = nodes[right - 1];
	}
}