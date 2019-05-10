package com.bailey.rod.hackerrank.maps.triplets

import org.junit.Test
import java.io.File
import java.util.*

const val debug = true


/**
 * @param Numbers in monotonically increasing order
 * @param r Ratio between successive elements of triplets
 * @return Number of tripets of form (r^x, r^(x+1), r^(x+2)
 */
fun countTriplets(arr: Array<Long>, r: Long): Long {
	val powerToCount: MutableMap<Long, Long> = TreeMap()

	for (num in arr) {
		// log_r(num) = log_10(num) / log_10(r)
		val power = (Math.log10(num.toDouble()) / Math.log10(r.toDouble())).toLong()
		var count = powerToCount.getOrDefault(power, 0)
		count++
		powerToCount.put(power, count)
	}

	if (debug) {
		println("powerToCount=${powerToCount}")
	}

	return 0
}

class TripletCount {
	@Test
	fun main() {
		val scan = Scanner(File("/Users/rodbailey/AndroidStudioProjects/KotlinKoans/app/src/test/java/com" +
				"/bailey/rod/hackerrank/maps/triplets/example02.txt"))
		val nr = scan.nextLine().split(" ")
		val n = nr[0].trim().toInt()
		val r = nr[1].trim().toLong()
		val arr = scan.nextLine()!!.trimEnd().split(" ").map { it.toLong() }.toTypedArray()
		val ans = countTriplets(arr, r)

		println(ans)
	}
}