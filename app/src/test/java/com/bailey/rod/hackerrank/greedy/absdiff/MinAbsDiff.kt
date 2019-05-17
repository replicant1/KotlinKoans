package com.bailey.rod.hackerrank.greedy.absdiff

import org.junit.Test
import java.io.File
import java.util.*

const val debug = false

// Complete the minimumAbsoluteDifference function below.
fun minimumAbsoluteDifference(arr: Array<Int>): Int {
	if (debug)
		println("Into minimumAbsoluteDifference with arr=${arr.toList()}")

	var leftIdx = 0
	var rightIdx = arr.size - 1

	arr.sort()

	if (debug)
		println("After sorting, arr=${arr.toList()}")

	while (leftIdx != (rightIdx - 1)) {
		if (debug)
			println("while: leftIdx=${leftIdx}, rightIdx=${rightIdx}")

		val leftStep = absDiff(arr, leftIdx, leftIdx + 1)
		val rightStep = absDiff(arr, rightIdx, rightIdx - 1)

		if (leftStep >= rightStep) {
			leftIdx += 1
		} else {
			rightIdx -= 1
		}
	}

	return absDiff(arr, leftIdx, rightIdx)
}

fun absDiff(arr: Array<Int>, index1:Int, index2:Int): Int {
	return Math.abs(arr[index1] - arr[index2])
}

// sample0.txt = 3
// sample1.txt = 1
// sample2.txt = 3
class MinAbsDiff {
	@Test
	fun main() {
		val scan = Scanner(File("/Users/rodbailey/AndroidStudioProjects/KotlinKoans/app/src/test/java/com" +
				"/bailey/rod/hackerrank/greedy/absdiff/sample2.txt"))
		val n = scan.nextLine().trim().toInt()
		val arr = scan.nextLine().split(" ").map { it.trim().toInt() }.toTypedArray()
		if (debug)
			println("In main(), n=${n}, arr=${arr}")
		val result = minimumAbsoluteDifference(arr)
		println(result)
	}
}