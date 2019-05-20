package com.bailey.rod.hackerrank.search.swapnodes

import org.junit.Test
import java.io.File
import java.util.*
import kotlin.collections.ArrayList

const val debug = false

fun swapNodes(nodes: Array<Array<Int>>, queries: Array<Int>): Array<Array<Int>> {
	if (debug) {
		println("nodes.size=${nodes.size}, queries.size=${queries.size}")
	}

	val result = ArrayList<Array<Int>>()

	for (k in queries) {
		// Swap subtrees of all nodes at depth k, 2k, 3k ...
		if (debug)
			println("----k = ${k}. Setting kFactor to ${k}")
		var kMultiplier = 1

		while ((kMultiplier * k) <= nodes.size) {
			val subtreeRootNums = nodesAtDepth(nodes, kMultiplier * k)

			if (subtreeRootNums.isEmpty()) {
				break
			}

			if (debug)
				println("subtreeRoots at ${kMultiplier * k} = ${subtreeRootNums
						.toList()}")

			for (subtreeRootNum in subtreeRootNums) {
				if (subtreeRootNum != -1)
					swapSubtree(nodes, subtreeRootNum)
			}

			kMultiplier += 1
		}

		val output = LinkedList<Int>()
		inorder(nodes, 1, output)

		result.add(output.toTypedArray())
	}

	return result.toTypedArray()
}

fun swapSubtree(nodes: Array<Array<Int>>, subtreeRootNodeNum: Int) {
	val subtreeRootNodeIdx = subtreeRootNodeNum - 1
	val oldLeft = nodes[subtreeRootNodeIdx][0]
	val oldRight = nodes[subtreeRootNodeIdx][1]
	nodes[subtreeRootNodeIdx][0] = oldRight
	nodes[subtreeRootNodeIdx][1] = oldLeft
}

fun nodesAtDepth(nodes: Array<Array<Int>>, depthNum: Int): Array<Int> {
	val result: MutableList<Int> = LinkedList<Int>()
	depthFind(nodes, depthNum, 1, 1, result)
	return result.toTypedArray()
}

fun depthFind(nodes: Array<Array<Int>>,
			  targetDepth: Int,
			  nodeNum: Int,
			  thisDepth: Int,
			  foundNodeNums: MutableList<Int>) {
	if (nodeNum != -1) {

		if (thisDepth == targetDepth) {
			foundNodeNums.add(nodeNum)
		} else {
			depthFind(nodes, targetDepth, nodes[nodeNum - 1][0], thisDepth +
					1, foundNodeNums)
			depthFind(nodes, targetDepth, nodes[nodeNum - 1][1], thisDepth +
					1, foundNodeNums)
		}
	}
}

fun inorder(nodes: Array<Array<Int>>, nodeNum: Int, output: MutableList<Int>) {
	if (nodeNum != -1) {
		inorder(nodes, nodes[nodeNum - 1][0], output)
		output.add(nodeNum)
		inorder(nodes, nodes[nodeNum - 1][1], output)
	}
}

// sample0.txt =
// 3 1 2
// 2 1 3

// sample1.txt =
// 4 2 1 5 3

// sample2.txg =
// 2 9 6 4 1 3 7 5 11 8 10
// 2 6 9 4 1 3 7 5 10 8 11

class SwapNodes {

	@Test
	fun main() {
		val scan = Scanner(File("/Users/rodbailey/AndroidStudioProjects/KotlinKoans/app/src/test/java/com" +
				"/bailey/rod/hackerrank/search/swapnodes/sample0.txt"))
		val n = scan.nextLine().trim().toInt()
		val indexes = Array<Array<Int>>(n, { Array<Int>(2, { 0 }) })
		for (indexesRowItr in 0 until n) {
			indexes[indexesRowItr] = scan.nextLine().split(" ").map { it.trim().toInt() }.toTypedArray()
		}
		val queriesCount = scan.nextLine().trim().toInt()
		val queries = Array<Int>(queriesCount, { 0 })
		for (queriesItr in 0 until queriesCount) {
			val queriesItem = scan.nextLine().trim().toInt()
			queries[queriesItr] = queriesItem
		}
		val result = swapNodes(indexes, queries)

		println(result.map { it.joinToString(" ") }.joinToString("\n"))
	}
}