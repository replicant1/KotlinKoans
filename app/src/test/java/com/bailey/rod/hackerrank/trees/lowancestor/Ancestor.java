package com.bailey.rod.hackerrank.trees.lowancestor;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Node {
	Node left;
	Node right;
	int data;

	Node(int data) {
		this.data = data;
		left = null;
		right = null;
	}

	@Override
	public String toString() {
		return "NODE " + data;
	}
}

class Token {

	public Token() {
		v1Found = false;
		v2Found = false;
		lca = null;
	}

	public Token(boolean v1Found, boolean v2Found, Node lca) {
		this.v1Found = v1Found;
		this.v2Found = v2Found;
		this.lca = lca;
	}

	final boolean v1Found;
	final boolean v2Found;
	final Node lca;

	@Override
	public String toString() {
		return "[@" + Integer.toHexString(hashCode()) + ": v1Found=" +
				v1Found + ", v2Found=" + v2Found +
				", lca=" + lca + "]";
	}
}

class Solution {

	private static final Boolean debug = false;

	public static void trace(String s) {
		if (debug) {
			System.out.println(s);
		}
	}

	/**
	 * @param root Root of binary search tree
	 * @param v1   Value 1 - a node in the tree
	 * @param v2   Value 2 - another node in the tree
	 * @return The Node that is the common parent of v1 and v2
	 */
	public static Node lca(Node root, int v1, int v2) {
		Token token = new Token(false, false, null);
		trace("Calling traverse() with v1=" + v1 + ", v2=" + v2);
		Token resultToken = traverse(root, v1, v2);
		return resultToken.lca;
	}

	public static Token traverse(Node node, int v1, int v2) {
		trace(node.toString());

		Token leftToken = new Token();
		if (node.left != null) {
			leftToken = traverse(node.left, v1, v2);
		}
		trace(node + ": leftToken=" + leftToken);

		Token rightToken = new Token();
		if (node.right != null) {
			rightToken = traverse(node.right, v1, v2);
		}
		trace(node + ": rightToken=" + rightToken);

		// Merge tokens from left and right subtrees and pass up tree via
		// returned result token.
		boolean resultV1Found = leftToken.v1Found || rightToken.v1Found;
		boolean resultV2Found = leftToken.v2Found || rightToken.v2Found;
		Node resultLCA = null;

		if (leftToken.lca != null) {
			resultLCA = leftToken.lca;
		} else if (rightToken.lca != null) {
			resultLCA = rightToken.lca;
		}

		if (node.data == v1) {
			resultV1Found = true;
			trace(node + ": *** Found v1 (" + v1 + ") at " + node + " ***");
		} else if (node.data == v2) {
			trace(node + ": *** Found v2 (" + v2 + ") at " + node + " ***");
			resultV2Found = true;
		}

		if ((leftToken.v1Found && rightToken.v2Found) || (leftToken.v2Found && rightToken.v1Found)) {
			trace(node + ": *** Found LCA at node " + node.data + " ***");
			resultLCA = node;
		}

		if ((leftToken.v1Found && node.data == v2) || (leftToken.v2Found && node.data == v1)) {
			resultLCA = node;
		}

		if ((rightToken.v1Found && (node.data == v2)) || (rightToken.v2Found && node.data == v1)) {
			resultLCA = node;
		}


		Token result = new Token(resultV1Found, resultV2Found, resultLCA);
		trace(node + ": RESULT token=" + result);
		return result;
	}

	public static Node insert(Node root, int data) {
		if (root == null) {
			return new Node(data);
		} else {
			Node cur;
			if (data <= root.data) {
				cur = insert(root.left, data);
				root.left = cur;
			} else {
				cur = insert(root.right, data);
				root.right = cur;
			}
			return root;
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		Scanner scan = new Scanner(new File("/Users/rodbailey/AndroidStudioProjects/KotlinKoans/app/src/test" +
				"/java/com/bailey/rod/hackerrank/trees/lowancestor/input/input08.txt"));
		int t = scan.nextInt();
		Node root = null;
		while (t-- > 0) {
			int data = scan.nextInt();
			root = insert(root, data);
		}
		int v1 = scan.nextInt();
		int v2 = scan.nextInt();
		scan.close();
		Node ans = lca(root, v1, v2);
		System.out.println(ans.data);
	}
}

