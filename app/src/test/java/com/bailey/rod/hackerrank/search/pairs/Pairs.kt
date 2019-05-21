package com.bailey.rod.hackerrank.search.pairs

import org.junit.Test
import java.io.File
import java.util.*

fun pairs(k: Int, arr: Array<Int>): Int {
	var i = 0
	var pairCount = 0

	arr.sort()

	while (i <= (arr.size - 2)) {
		var j = i + 1
		while (j <= (arr.size) - 1) {
			if (Math.abs(arr[i] - arr[j]) == k) {
				pairCount++
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
class Pairs {
	@Test
	fun main() {
		val scan = Scanner(File("/Users/rodbailey/AndroidStudioProjects/KotlinKoans/app/src/test/java/com" +
				"/bailey/rod/hackerrank/search/pairs/sample1.txt"))
		val nk = scan.nextLine().split(" ")
		val n = nk[0].trim().toInt()
		val k = nk[1].trim().toInt()
		val arr = scan.nextLine().split(" ").map { it.trim().toInt() }.toTypedArray()
		val result = pairs(k, arr)
		println(result)
	}
}
