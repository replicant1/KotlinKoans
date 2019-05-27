package com.bailey.rod.hackerrank.greedy.maxmin

import org.junit.Test
import java.io.File
import java.util.*

const val debug = true

/**
 * @param subarrayLength Desired sub-array length
 * @param arr Source array from which sub-array is chosen
 * @return Minimum possible unfairness of a sub-array length subarrayLength
 */
fun maxMin(subarrayLength: Int, arr: Array<Int>): Int {

	if (debug)
		println("subarrayLength=${subarrayLength}, arr=${arr.toList()}")

	arr.sort()

	var leftIdx = 0
	var rightIdx = arr.size - 1

	while ((rightIdx - leftIdx + 1) > subarrayLength) {
		val diffLeft = arr[rightIdx] - arr[leftIdx + 1]
		val diffRight = arr[rightIdx - 1] - arr[leftIdx]

		if (diffRight < diffLeft) {
			// Move right ptr one to the left, to rightIdx - 1
			rightIdx = rightIdx - 1
		} else {
			// Move left ptr one to the right, to leftIdx + 1
			leftIdx = leftIdx + 1
		}
	}

	return arr[rightIdx] - arr[leftIdx]
}

class MaxMin {
	@Test
	fun main() {
		val scan = Scanner(File("/Users/rodbailey/AndroidStudioProjects/KotlinKoans/app/src/test/java/com" +
				"/bailey/rod/hackerrank/greedy/maxmin/input/input15.txt"))
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
