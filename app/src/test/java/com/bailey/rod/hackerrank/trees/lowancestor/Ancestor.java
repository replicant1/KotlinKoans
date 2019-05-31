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
}

class Solution {

	private static Boolean debug = false;

	public static Node lca(Node root, int v1, int v2) {
		// Write your code here.
		return null;
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
				"/java/com/bailey/rod/hackerrank/trees/lowancestor/input/input00.txt"));
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

