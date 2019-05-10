package com.bailey.rod.hackerrank.arrays

import org.junit.Test
import java.io.File
import java.util.*


fun arrayManipulation(n: Int, operations: Array<Array<Int>>): Long {
	var maxDataValue: Long = 0
	// 'n+2' is because indexes in each op are 1-based
	val diffs: Array<Long> = Array(n + 2, { 0.toLong() })

	for (op in operations) {
		// op[0] and op[1] are 1-based.
		val lowIndex: Int = op[0]
		val highIndex: Int = op[1]
		val toAdd: Int = op[2]

		diffs[lowIndex] = diffs[lowIndex] + toAdd
		diffs[highIndex + 1] = diffs[highIndex + 1] - toAdd

		//println("After op (${lowIndex}, ${highIndex}, ${toAdd}) diffs=${diffs.toList()}")
	}

	var runningTotal: Long = 0
	for (diff in diffs) {
		runningTotal += diff
		if (runningTotal > maxDataValue) {
			maxDataValue = runningTotal
		}
	}

	return maxDataValue
}

class ArrayManipulation {


	@Test
	fun main() {
		// input01 = 882
		// input02 = 10
		// input03 = 200
		// input04 = 7542539201
		// input05 = 7496167325
		// input06 = 7515267971
		// input07 = 2497169732
		// input08 = 2484930878
		// input09 = 2501448788
		// input10 = 2510535321
		// input11 = 2506721627

		val scan = Scanner(File("/Users/rodbailey/AndroidStudioProjects/KotlinKoans/app/src/test/java/com" +
				"/bailey/rod/hackerrank/arrays/input11.txt"))
		val nm = scan.nextLine().split(" ")
		val n = nm[0].trim().toInt()
		val m = nm[1].trim().toInt()
		val queries = Array<Array<Int>>(m, { Array<Int>(3, { 0 }) })
		for (i in 0 until m) {
			queries[i] = scan.nextLine().split(" ").map { it.trim().toInt() }.toTypedArray()
		}

//		val n = 10
//		val queries = arrayOf(
//				arrayOf(1,5,3),
//				arrayOf(4,8,7),
//				arrayOf(6,9,1))
		val result = arrayManipulation(n, queries)

		println(result)
	}
}