package com.bailey.rod.hackerrank.sorting.notifications

import org.junit.Test
import java.io.File
import java.util.*

const val debug = false

fun activityNotifications(spend: Array<Int>, windowSize: Int): Int {
	var numNotifications: Int = 0
	var windowFreqMap = TreeMap<Int, Int>()

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

		if (debug)
			println("medianIdx=${medianIdx},lMedianIdx=${lMedianIdx}," +
					"rMedianIdx=${rMedianIdx}")

		// Populate spendToCount from window
		for (i in 0..(windowSize - 1)) {
			incFreq(windowFreqMap, spend[i])
		}

		if (debug)
			println("At start, windowFreqMap=${windowFreqMap}")

		for (i in windowSize..(spend.size - 1)) {
			if (debug)
				println("-------------- i=${i} ------------------")

			if (i != windowSize) {
				advanceWindow(spend, i, windowSize, windowFreqMap)
			}

			val windowMedian =
					if (windowSizeEven)
						calcMedianForEvenSizeWindow(windowFreqMap, lMedianIdx, rMedianIdx)
					else
						calcMedianForOddSizeWindow(windowFreqMap, medianIdx)

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

fun incFreq(map: MutableMap<Int, Int>, toAdd: Int) {
	val oldValue = map.getOrDefault(toAdd, 0);
	map.put(toAdd, oldValue + 1)
}

fun decFreq(map: MutableMap<Int, Int>, toRemove: Int) {
	val oldValue = map.getOrDefault(toRemove, 0)
	val newValue = oldValue - 1
	if (newValue == 0) {
		map.remove(toRemove)
	} else {
		map.put(toRemove, newValue)
	}
}

// 'toPos'is in a new position. Now update 'spendToCount'
fun advanceWindow(spend: Array<Int>, toPos: Int, windowSize: Int,
				  spendToCount: MutableMap<Int, Int>) {
	val valueToRemove: Int = spend[toPos - windowSize - 1]
	val valueToAdd: Int = spend[toPos - 1]

	if (debug) {
		println("valueToRemove=${valueToRemove}")
		println("valueToAdd=${valueToAdd}")
	}

	decFreq(spendToCount, valueToRemove)
	incFreq(spendToCount, valueToAdd)
}

fun calcMedianForEvenSizeWindow(windowFreqMap: MutableMap<Int, Int>,
								leftIdx: Int, rightIdx: Int): Double {

	if (debug)
		println ("windowFreqMap=${windowFreqMap}, leftIdx=${leftIdx}, " +
				"rightIdx=${rightIdx}")

	var index = 0
	var leftValue:Int = 0
	var rightValue:Int = 0

	for (entry in windowFreqMap.entries) {
		index += entry.value
		if (index > leftIdx) {
			leftValue = entry.key
			break
		}
	}

	index = 0
	for (entry in windowFreqMap.entries) {
		index += entry.value
		if (index > rightIdx) {
			rightValue = entry.key
			break
		}
	}

	if (debug)
		println("leftValue=${leftValue}, rightValue=${rightValue}")

	return (leftValue + rightValue).toDouble() / 2.toDouble()
}

fun calcMedianForOddSizeWindow(windowFreqMap: MutableMap<Int, Int>,
							   medianIdx: Int): Double {
	var index = 0
	for (entry in windowFreqMap.entries) {
		index += entry.value
		if (index > medianIdx) {
			return entry.key.toDouble()
		}
	}
	return 0.toDouble()
}

class Notifications {
	@Test
	fun main() {
		val scan = Scanner(File("/Users/rodbailey/AndroidStudioProjects/KotlinKoans/app/src/test/java/com" +
				"/bailey/rod/hackerrank/sorting/notifications/input04.txt"))
		val nd = scan.nextLine().split(" ")
		val n = nd[0].trim().toInt()
		val d = nd[1].trim().toInt()
		val expenditure = scan.nextLine().split(" ").map { it.trim().toInt() }.toTypedArray()
		val result = activityNotifications(expenditure, d)
		println(result)
	}
}
