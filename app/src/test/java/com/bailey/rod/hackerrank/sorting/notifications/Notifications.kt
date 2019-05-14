package com.bailey.rod.hackerrank.sorting.notifications

import org.junit.Test
import java.io.File
import java.util.*

const val debug = false

/**
 * @param spend daily expenditures in increasing time order
 * @param windowSize number of lookback days used to calculate median
 */
fun activityNotifications(spend: Array<Int>, windowSize: Int): Int {
	var numNotifications: Int = 0

	if (debug) {
		println("spend=${spend.toList()}")
		println("spend.size=${spend.size}, windowSize=${windowSize}")
	}

	if (spend.size > windowSize) {
		for (i in windowSize..(spend.size - 1)) {
			if (debug)
				println("-------------- i=${i} ------------------")

			val window: Array<Int> = spend.copyOfRange(i - windowSize, i)
			val windowMedian = median(window)

			if (spend[i] >= (2 * windowMedian)) {
				numNotifications++
					println("NOTIF at [${i}] $${spend[i]} >= (2 * " +
							"$${windowMedian}) = ${numNotifications}")

			}

		}
	}

	return numNotifications
}

/**
 * @param arr already sorted array, ascending
 */
fun median(arr: Array<Int>): Double {
	if (debug)
		println("Into median: ${arr.toList()}")

	arr.sort()

	var result = 0.toDouble()
	val arrSize = arr.size

	if (arrSize % 2 == 1) {
		// Odd number of elements - median = middle element
		val medianIndex = ((arrSize + 1) / 2) - 1
		result = arr[medianIndex].toDouble()

		if (debug)
			println("When array size is ${arrSize} idx of median = " +
					"${medianIndex}," +
					" median=${result}")
	} else {
		// Even number of elements - mediam = av. of two middle elements
		val leftMiddleIndex = (arrSize / 2) - 1
		val rightMiddleIndex = leftMiddleIndex + 1
		result = (arr[leftMiddleIndex] + arr[rightMiddleIndex]) / 2.toDouble()

		if (debug)
			println("When array sie is ${arrSize} ,leftIdx=${leftMiddleIndex}, " +
					"rightIdx=${rightMiddleIndex}")
	}

	return result
}

// sample0.txt = 2
// sample1.txt = 0
// input01.txt = 633
// input02.txt = 770
// input03.txt = 1026
// input04.txt = 492
// input05.txt = 926
// input07.txt = 1
class Notifications {
	@Test
	fun main() {
		val scan = Scanner(File("/Users/rodbailey/AndroidStudioProjects/KotlinKoans/app/src/test/java/com" +
				"/bailey/rod/hackerrank/sorting/notifications/input01.txt"))
		val nd = scan.nextLine().split(" ")
		val n = nd[0].trim().toInt()
		val d = nd[1].trim().toInt()
		val expenditure = scan.nextLine().split(" ").map { it.trim().toInt() }.toTypedArray()
		val result = activityNotifications(expenditure, d)
		println(result)
	}
}
