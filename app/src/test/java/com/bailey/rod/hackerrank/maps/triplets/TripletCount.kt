package com.bailey.rod.hackerrank.maps.triplets

import org.junit.Test
import java.io.File
import java.util.*

const val debug = false


/**
 * @param Numbers in monotonically increasing order
 * @param r Ratio between successive elements of triplets
 * @return Number of tripets of form (r^x, r^(x+1), r^(x+2)
 */
fun countTriplets(arr: Array<Long>, r: Long): Long {
	// If r^p = arr[i], powerToCount(p) += 1 (i is not stored)
	val powerToCount: MutableMap<Long, Long> = TreeMap()
	var result: Long = 0

	if (debug)
		println("countTriplets: r=${r}, arr=${arr.toList()}")

	// Populate the [powerToCount] map based on [arr] contents
	for (num in arr) {
		// log_r(num) = log_10(num) / log_10(r)
		val power = log(num, r)
		var count = powerToCount.getOrDefault(power, 0)
		count++
		powerToCount.put(power, count)

		if (debug)
			println("num=${num}, power=${power}, count=${count}")
	}

	if (debug)
		println("powerToCount=${powerToCount}")

	// Count the triplets (r^a, r^b, r^c) where a+1=b and b+1=c
	val powers:Array<Long> = powerToCount.keys.toTypedArray()

	if (debug)
		println("powers=${powers.toList()}")

	for (i in 0..(powers.size - 3)) {
		if (debug)
			println("powers[${i}]=${powers[i]}, powers[${i+1}]=${powers[i+1]}, powers[${i+2}]=${powers[i+2]}")

		if (((powers[i] + 1) == powers[i+1]) && ((powers[i+1] + 1) == powers[i+2])) {
			val count_i: Long = powerToCount[powers[i]] ?: 0
			val count_ip1: Long = powerToCount[powers[i + 1]] ?: 0
			val count_ip2: Long = powerToCount[powers[i + 2]] ?: 0
			val numTriplets = count_i * count_ip1 * count_ip2

			if (debug)
				println("triplet: [${i}]=${count_i}, [${i+1}]=${count_ip1}, [${i+2}]=${count_ip2}")

			result += numTriplets
		}
	}

	return result
}

fun log(x: Long, base: Long) : Long {
	val logx_10 = Math.log(x.toDouble())
	val logbase_10 = Math.log(base.toDouble())
	val resultDouble = (logx_10/ logbase_10)
	val resultLong = resultDouble.toLong()
	if (debug) {
		println("Log of ${x} base ${base}: logx_10=${logx_10}, logbase_10=${logbase_10}, " +
				"resultDouble=${resultDouble}, " +
				"resultLong=${resultLong}")
	}
	return resultLong
}

class TripletCount {
	@Test
	fun main() {
		// example01 = 2
		// example02 = 6
		// example03 = 4
		// input02 = 161700
		// input03 = 166661666700000
		// input04 = 0
		// input05 = 0
		// input06 = 2325652489
		// input09 = 0
		// input10 = 1339347780085
		// input11 = 1667018988625
		val scan = Scanner(File("/Users/rodbailey/AndroidStudioProjects/KotlinKoans/app/src/test/java/com" +
				"/bailey/rod/hackerrank/maps/triplets/example03.txt"))
		val nr = scan.nextLine().split(" ")
		val n = nr[0].trim().toInt()
		val r = nr[1].trim().toLong()
		val arr = scan.nextLine()!!.trimEnd().split(" ").map { it.toLong() }.toTypedArray()
		val ans = countTriplets(arr, r)

		if (debug)
			println("**** TOTAL NUMBER OF TRIPLETS ****")

		println(ans)
	}
}