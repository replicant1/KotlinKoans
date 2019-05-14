package com.bailey.rod.hackerrank.sorting.notifications

import org.junit.Test
import java.io.File
import java.util.*

const val debug = false

fun activityNotifications(spend: Array<Int>, windowSize: Int): Int {
	var numNotifications: Int = 0
	var sortedWindow: Array<Int>

	if (debug) {
		println("spend=${spend.toList()}")
		println("spend.size=${spend.size}, windowSize=${windowSize}")
	}

	if (spend.size > windowSize) {
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

		sortedWindow = spend.copyOfRange(0, windowSize)

		if (debug)
			println("Before sorting, sortedWindow=${sortedWindow.toList()}")

		sortedWindow.sort()

		if (debug)
			println("After sorting, sortedWindow=${sortedWindow.toList()}")

		for (i in windowSize..(spend.size - 1)) {
			if (debug)
				println("-------------- i=${i} ------------------")

			if (i != windowSize) {
				updateWindow(spend, i, sortedWindow)
			}

			val windowMedian =
					if (windowSizeEven)
						medianEven(sortedWindow, lMedianIdx, rMedianIdx)
					else
						medianOdd(sortedWindow, medianIdx)

			if (spend[i] >= (2 * windowMedian)) {
				numNotifications++
				if (debug)
					println("NOTIF at [${i}] $${spend[i]} >= (2 * " +
							"$${windowMedian}) = ${numNotifications}")

			}
		}
	}

	return numNotifications
}

// 'i'is in a new position. Now update 'sortedWindow'
fun updateWindow(spend: Array<Int>, i: Int, sortedWindow: Array<Int>) {
	if (debug)
		println("Into updateWindow: i=${i}, sortedWindow=${sortedWindow.toList()}")

	val valueToRemove: Int = spend[i - sortedWindow.size - 1]
	val valueToAdd: Int = spend[i-1]

	if (debug) {
		println("valueToRemove=spend[${i -sortedWindow.size - 1}] " +
				"=${valueToRemove}")
		println("valueToAdd=${valueToAdd}")
	}

	val indexOfRemove = sortedWindow.indexOf(valueToRemove)
	sortedWindow[indexOfRemove] = valueToAdd

	if (debug)
		println("After replacement, before sorting, sortedWindow=${sortedWindow
				.toList()}")

	sortedWindow.sort()

	if (debug)
		println("After replacement, after sorting, " +
				"sortredWindow=${sortedWindow.toList()}")
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
// input07.txt = 1 <---
class Notifications {
	@Test
	fun main() {
		val scan = Scanner(File("/Users/rodbailey/AndroidStudioProjects/KotlinKoans/app/src/test/java/com" +
				"/bailey/rod/hackerrank/sorting/notifications/input05.txt"))
		val nd = scan.nextLine().split(" ")
		val n = nd[0].trim().toInt()
		val d = nd[1].trim().toInt()
		val expenditure = scan.nextLine().split(" ").map { it.trim().toInt() }.toTypedArray()
		val result = activityNotifications(expenditure, d)
		println(result)
	}
}
