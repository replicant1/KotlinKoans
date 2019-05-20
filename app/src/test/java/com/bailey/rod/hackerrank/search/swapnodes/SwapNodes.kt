package com.bailey.rod.hackerrank.search.swapnodes

import org.junit.Test
import java.io.File
import java.util.*

const val debug = true

fun swapNodes(indexes: Array<Array<Int>>, queries: Array<Int>): Array<Array<Int>> {
	if (debug) {
		for (idx in indexes) {
			println("indexes=${idx.toList()}")
		}
		println("queries=${queries.toList()}")
	}
	return emptyArray()
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