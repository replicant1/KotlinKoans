package com.bailey.rod.hackerrank.search.pairs

import org.junit.Test
import java.io.File
import java.util.*

const val debug = false

fun pairs(k: Int, arr: Array<Int>): Int {
	if (debug)
		println("k=${k}, arr.size=${arr.size}")

	var i = 0
	var pairCount = 0

	arr.sort()

	while (i <= (arr.size - 2)) {
		var j = i + 1
		while (j <= (arr.size - 1)) {
			val diff = arr[j] - arr[i]
			if (diff == k) {
				pairCount++
				break
			} else if (diff > k) {
				break
			}
			j++
		}
		i++
	}

	return pairCount
}

// sample0.txt = 3
// sample1.txt = 3
// input10.txt = 43253
class Pairs {
	@Test
	fun main() {
		val scan = Scanner(File("/Users/rodbailey/AndroidStudioProjects/KotlinKoans/app/src/test/java/com" +
				"/bailey/rod/hackerrank/search/pairs/input10.txt"))
		val nk = scan.nextLine().split(" ")
		val n = nk[0].trim().toInt()
		val k = nk[1].trim().toInt()
		val arr = scan.nextLine().split(" ").map { it.trim().toInt() }.toTypedArray()
		val result = pairs(k, arr)
		println(result)
	}
}
