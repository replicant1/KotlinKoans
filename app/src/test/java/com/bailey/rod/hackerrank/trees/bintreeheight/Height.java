package com.bailey.rod.hackerrank.trees.bintreeheight;

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
		return "N" + Integer.toString(data);
	}
}

class Solution {

	private static Boolean debug = false;

	public static int height(Node root) {
		return traverse(root, 0);
	}

	/**
	 * @param node   Root of subtree to traverse
	 * @param height Height of [node] in the subtree. Root has height of 0.
	 * @return
	 */
	public static int traverse(Node node, int height) {
		if (debug)
			System.out.println("Into traverse with node=" + node + " and height=" + height);

		if (node == null) {
			if (debug)
				System.out.println("node == null, so returning (height - 1) =" + (height - 1));
			return height - 1;
		}
		int leftHeight = traverse(node.left, height + 1);

		if (debug)
			System.out.println("leftHeight=" + leftHeight);

		int rightHeight = traverse(node.right, height + 1);

		if (debug)
			System.out.println("rightHeight=" + rightHeight);

		int result = Math.max(leftHeight, rightHeight);

		if (debug)
			System.out.println("return result = " + result);

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
				"/java/com/bailey/rod/hackerrank/trees/bintreeheight/input/input05.txt"));
		int t = scan.nextInt();
		Node root = null;
		while (t-- > 0) {
			int data = scan.nextInt();
			root = insert(root, data);
		}
		scan.close();
		int height = height(root);
		System.out.println(height);
	}
}

