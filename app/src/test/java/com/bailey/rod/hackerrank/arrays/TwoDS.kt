package com.bailey.rod.hackerrank.arrays

import org.junit.Test
import java.util.*

/**
 * @param arr 2D array of dimensions 6x6
 * @return The maximum sum of any hourglass
 */
fun maxHourglassSum(arr: Array<Array<Int>>): Int {
	var maxSum = 0
	for (row in 0..3) {
		for (col in 0..3) {
			val thisSum = hourglassSum(arr, row, col)
			if ((row == 0 && col == 0) || (thisSum > maxSum)) {
				maxSum = thisSum
			}
		}
	}
	return maxSum
}

/**
 * Calculates the numeric sum of all elements in an hourglass shape
 * at a given coordinate in the given array.
 *
 * @param arr The array containing the hourglass.
 * @param col The zero-based index of the col in arr containing the
 * top left corner of the hourglass.
 * @param row the zero-based index of the row in arr containing the
 * top left corner of the hourglass
 * @return the sum of the elements in the hourglass whose top left
 * is at [row, col] in the array 'arr'.
 */
fun hourglassSum(arr: Array<Array<Int>>, row: Int, col: Int) : Int {
	var result : Int = 0

	for (c in col..col+2) {
		result += arr[row][c]
		result += arr[row + 2][c]
	}
	result += arr[row + 1][col + 1]

	return result
}

class TwoDS {

	@Test
	fun main() {
		val scan = Scanner(System.`in`)

		// Array is 6 x 6 with each element set to zero
		val arr = Array<Array<Int>>(6, { Array<Int>(6, { 0 }) })

		for (i in 0 until 6) {
			arr[i] = scan.nextLine().split(" ").map { it.trim().toInt() }.toTypedArray()
		}

//		val arr = arrayOf(
//				arrayOf(-9, -9, -9,  1, 1, 1),
//				arrayOf( 0,  9,  0,  4, 3 ,2),
//				arrayOf(-9, -9, -9,  1, 2, 3),
//				arrayOf( 0,  0,  8,  6, 6, 0),
//				arrayOf( 0,  0,  0, -2, 0, 0),
//				arrayOf( 0,  0,  1,  2, 4, 0))

		val result = maxHourglassSum(arr)

		println(result)
	}
}