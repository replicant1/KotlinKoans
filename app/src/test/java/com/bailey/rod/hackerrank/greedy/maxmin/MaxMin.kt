package com.bailey.rod.hackerrank.greedy.maxmin

import org.junit.Test
import java.io.File
import java.util.*

const val debug = false

/**
 * @param subarrayLength Desired sub-array length
 * @param arr Source array from which sub-array is chosen
 * @return Minimum possible unfairness of a sub-array length subarrayLength
 */
fun maxMin(subarrayLength: Int, arr: Array<Int>): Int {

	if (debug) {
		println("subarrayLength=${subarrayLength}, arr=${arr.toList()}")
		// lookForRepetition(arr)
	}

	arr.sort()

	if (debug)
		println("After sorting, arr=${arr.toList()}")

	var minUnfairnesss = Integer.MAX_VALUE

	for (leftIdx in 0.. arr.size - subarrayLength) {
		val rightIdx = leftIdx + subarrayLength - 1
		val unfairness = arr[rightIdx] - arr[leftIdx]
		if (unfairness < minUnfairnesss) {
			minUnfairnesss = unfairness
		}
	}

	return minUnfairnesss
}

class MaxMin {
	@Test
	fun main() {
		val scan = Scanner(File("/Users/rodbailey/AndroidStudioProjects/KotlinKoans/app/src/test/java/com" +
				"/bailey/rod/hackerrank/greedy/maxmin/input/input16.txt"))
		val n = scan.nextLine().trim().toInt()
		val k = scan.nextLine().trim().toInt()
		val arr = Array<Int>(n, { 0 })
		for (i in 0 until n) {
			val arrItem = scan.nextLine().trim().toInt()
			arr[i] = arrItem
		}
		val result = maxMin(k, arr)
		println(result)
	}
}
