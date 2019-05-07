package com.bailey.rod.hackerrank.arrays

import org.junit.Test

/**
 * @param a Array of Ints to be rotated left
 * @param d Number of times to rotate left
 */
fun rotLeft(a: Array<Int>, d: Int): Array<Int> {
	if (a.size > 0) {

		val splitPoint = d % a.size
		println("splitPoint=${splitPoint}")

		val preSplit = a.copyOfRange(0, splitPoint).toList()
		println("preSplit=${preSplit}")

		val postSplit = a.copyOfRange(splitPoint, a.size).toList()
		println("postSplit=${postSplit}")

		val result = postSplit + preSplit
		println("result=${result}")

		return result.toTypedArray()
	}
	return a
}

//fun rotLeft1(a: Array<Int>): Array<Int> {
//	val result: Array<Int> = Array(a.size, { 0 })
//
//	if (a.isNotEmpty()) {
//		val leftMostInt: Int = a[0]
//		for (i in 1..(a.size - 1)) {
//			result[i - 1] = a[i]
//		}
//		result[a.size - 1] = leftMostInt
//	}
//
//	return result
//}

class RotateLeft {

	@Test
	fun main() {
//		val scan = Scanner(System.`in`)
//		val nd = scan.nextLine().split(" ")
//		val n = nd[0].trim().toInt()
//		val d = nd[1].trim().toInt()
//		val a = scan.nextLine().split(" ").map { it.trim().toInt() }.toTypedArray()

//		val result = rotLeft(a, d)
		val result = rotLeft(emptyArray(), 3)

		println(result.joinToString(" "))
	}
}