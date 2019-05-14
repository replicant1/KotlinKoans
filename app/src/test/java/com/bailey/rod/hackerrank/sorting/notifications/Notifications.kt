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

	var windowSizeEven = if (windowSize % 2 == 0) true else false
	var medianIdx = -1
	var lMedianIdx = -1
	var rMedianIdx = -1

	if (windowSizeEven) {
		lMedianIdx = (windowSize / 2) - 1
		rMedianIdx = lMedianIdx + 1
	} else {
		medianIdx = ((windowSize + 1) / 2) - 1
	}

	var copyWindowMs : Double = 0.toDouble()
	var findMedianMs : Double = 0.toDouble()

	if (spend.size > windowSize) {
		for (i in windowSize..(spend.size - 1)) {
			if (debug)
				println("-------------- i=${i} ------------------")

			val t0 = System.currentTimeMillis()
			val window: Array<Int> = spend.copyOfRange(i - windowSize, i)
			val t1 = System.currentTimeMillis()
			val windowMedian =
					if (windowSizeEven)
						medianEven(window, lMedianIdx, rMedianIdx)
					else
						medianOdd(window, medianIdx)
			val t2 = System.currentTimeMillis()

			if (spend[i] >= (2 * windowMedian)) {
				numNotifications++
				println("NOTIF at [${i}] $${spend[i]} >= (2 * " +
						"$${windowMedian}) = ${numNotifications}")

			}

			copyWindowMs += (t1 - t0)
			findMedianMs += (t2 - t1)

		}

		println("copyWindowMs=${copyWindowMs}, findMedianMs=${findMedianMs}")
	}

	return numNotifications
}


fun medianEven(window: Array<Int>, leftIdx: Int, rightIdx: Int): Double {
	window.sort()
	return (window[leftIdx] + window[rightIdx]) / 2.toDouble()
}

fun medianOdd(window: Array<Int>, medianIdx: Int): Double {
	window.sort()
	return window[medianIdx].toDouble()
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
