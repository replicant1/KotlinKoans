package com.bailey.rod.hackerrank.trees.huffman;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

abstract class Node implements Comparable<Node> {
	public int frequency; // the frequency of this tree
	public char data;
	public Node left, right;

	public Node(int freq) {
		frequency = freq;
	}

	// compares on the frequency
	public int compareTo(Node tree) {
		return frequency - tree.frequency;
	}

	@Override
	public String toString() {
		return data + " " + frequency;
	}
}

class HuffmanLeaf extends Node {
	public HuffmanLeaf(int freq, char val) {
		super(freq);
		data = val;
	}
}

class HuffmanNode extends Node {
	public HuffmanNode(Node l, Node r) {
		super(l.frequency + r.frequency);
		left = l;
		right = r;
	}
}


class Decoding {
	private static final boolean DEBUG = false;

	void trace(String s) {
		if (DEBUG)
			System.out.println(s);
	}

	/**
	 * @param s    Huffman code (string of "1"s and "0"s
	 * @param root Root of Huffman tree
	 */
	void decode(String s, Node root) {
		trace("Into decode: s=\"" + s + "\", root=" + root);
		StringBuilder decoded = new StringBuilder();
		StringBuilder encoded = new StringBuilder(s);
		while (!encoded.toString().isEmpty()) {
			LeafVisitedToken result = traverse(encoded.toString(), root, 0);
			trace("result token = " + result);
			decoded.append(result.character);
			encoded.delete(0, result.depth);
		}
		trace("decoded=" + decoded.toString());
		System.out.println(decoded.toString());
	}

	LeafVisitedToken traverse(String s, Node root, int depth) {
		trace("Into traverse: s=\"" + s + "\",root=" + root + ",depth=" + depth);
		boolean r_visited = false;
		char r_character = ' ';
		int r_depth = 0;

		LeafVisitedToken leftToken = new LeafVisitedToken(0);
		LeafVisitedToken rightToken = new LeafVisitedToken(0);

		String remaining = (s.length() == 0) ? "" : s.substring(1);

		// 'root' is the first leaf encountered in the traversal so far,
		// so capture all details about it in the LeafVisitedToken
		// to be returned by this method.
		if ((root.left == null) && (root.right == null)) {
			r_visited = true;
			r_character = root.data;
			r_depth = depth;
		}
		else {
			if ((s.charAt(0) == '0') && (root.left != null)) {
				leftToken = traverse(remaining, root.left, depth + 1);
			}

			if ((s.charAt(0) == '1') && (root.right != null)) {
				rightToken = traverse(remaining, root.right, depth + 1);
			}

			r_visited = leftToken.visited || rightToken.visited;
			r_character = leftToken.character == ' ' ? rightToken.character : leftToken.character;
			r_depth = leftToken.depth == 0 ? rightToken.depth : leftToken.depth;

		} // else

		return new LeafVisitedToken(r_visited, r_character, r_depth);
	}
}

class LeafVisitedToken {
	final boolean visited;
	final char character;
	final int depth;

	LeafVisitedToken(int depth) {
		visited = false;
		character = ' ';
		this.depth = depth;
	}

	LeafVisitedToken(boolean visited, char character, int depth) {
		this.visited = visited;
		this.character = character;
		this.depth = depth;
	}

	@Override
	public String toString() {
		return "visited=" + visited + ", character=" + character + ", depth=" + depth;
	}
}


public class Solution {

	// input is an array of frequencies, indexed by character code
	public static Node buildTree(int[] charFreqs) {

		PriorityQueue<Node> trees = new PriorityQueue<Node>();
		// initially, we have a forest of leaves
		// one for each non-empty character
		for (int i = 0; i < charFreqs.length; i++)
			if (charFreqs[i] > 0)
				trees.offer(new HuffmanLeaf(charFreqs[i], (char) i));

		assert trees.size() > 0;

		// loop until there is only one tree left
		while (trees.size() > 1) {
			// two trees with least frequency
			Node a = trees.poll();
			Node b = trees.poll();

			// put into new node and re-insert into queue
			trees.offer(new HuffmanNode(a, b));
		}

		return trees.poll();
	}

	public static Map<Character, String> mapA = new HashMap<Character, String>();

	public static void printCodes(Node tree, StringBuffer prefix) {

		assert tree != null;

		if (tree instanceof HuffmanLeaf) {
			HuffmanLeaf leaf = (HuffmanLeaf) tree;

			// print out character, frequency, and code for this leaf (which is just the prefix)
			//System.out.println(leaf.data + "\t" + leaf.frequency + "\t" + prefix);
			mapA.put(leaf.data, prefix.toString());

		} else if (tree instanceof HuffmanNode) {
			HuffmanNode node = (HuffmanNode) tree;

			// traverse left
			prefix.append('0');
			printCodes(node.left, prefix);
			prefix.deleteCharAt(prefix.length() - 1);

			// traverse right
			prefix.append('1');
			printCodes(node.right, prefix);
			prefix.deleteCharAt(prefix.length() - 1);
		}
	}

	public static void printTree(Node node) {
		if (node == null) {
			return;
		}
		StringBuilder sb = new StringBuilder();
		sb.append("node.data=" + node.data);
		if (node.left != null)
			sb.append("node.left.data=" + node.left.data);
		if (node.right != null)
			sb.append("node.right.data=" + node.right.data);
		System.out.println(sb.toString());

		printTree(node.left);
		printTree(node.right);
	}

	public static void main(String[] args) throws FileNotFoundException {
		Scanner input = new Scanner(new File("/Users/rodbailey/AndroidStudioProjects/KotlinKoans/app/src/test" +
				"/java/com/bailey/rod/hackerrank/trees/huffman/input/input05.txt"));

		String test = input.next();

		// we will assume that all our characters will have
		// code less than 256, for simplicity
		int[] charFreqs = new int[256];

		// read each character and record the frequencies
		for (char c : test.toCharArray())
			charFreqs[c]++;

		// build tree
		Node tree = buildTree(charFreqs);

//		printTree(tree);

		// print out results
		printCodes(tree, new StringBuffer());
		StringBuffer s = new StringBuffer();

		for (int i = 0; i < test.length(); i++) {
			char c = test.charAt(i);
			s.append(mapA.get(c));
		}


		//System.out.println(s);
		Decoding d = new Decoding();
		d.decode(s.toString(), tree);
	}
}