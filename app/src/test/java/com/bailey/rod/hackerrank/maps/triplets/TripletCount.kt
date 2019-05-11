package com.bailey.rod.hackerrank.maps.triplets

import org.junit.Test
import java.io.File
import java.util.*
import kotlin.collections.HashMap

const val debug = true

/**
 * @return The number of triplets (A,B,C) in 'arr' where A,B and C are at increasing indexes in 'arr' and
 * form a geometric progress with a common 'ratio'.
 */
fun countTriplets(arr: Array<Long>, ratio: Long): Long {
	// key = the second element of a triplet that is 'wanted' by some first element we have found in [arr]
	// value = the number of first elements waiting on [key] to be their second element in a triplet
	// e.g. 9 -> 4 means that there are 4 partial, single element triplets waiting on a '9' to appear so
	// that they can use it as their second element
	val secondElementsWanted = HashMap<Long, Long>()

	// key = the third element of a triplet that is 'wanted' by some first+second element pair already found in [arr]
	// value = the number of first+second element pairs waiting on [key] to be their third element in a triplet
	// e.g. 27 -> 2 means that there are 2 partial, two element triplets waiting on a '27' to appear in 'arr'
	// so that they can use it as their third and final element.
	val thirdElementsWanted = HashMap<Long, Long>()

	var numTriplets: Long = 0

	// Iterate through each number 'a' in 'arr' asking "what if 'a' is A, B or C in the triplet (A,B,C)?"
	for (a in arr) {
		// Could this be the third and final element of any triplets? If so, we can add *all* of those triplets
		// to the total list of triplets found in 'arr'.
		if (thirdElementsWanted.containsKey(a)) {
			numTriplets += thirdElementsWanted.getOrDefault(a, 0)
		}

		// Could this be the second element of any triplets? If so we only need the third element of the triplet to
		// complete *all* the pending, 2/3 complete triplets
		if (secondElementsWanted.containsKey(a)) {
			val old: Long = thirdElementsWanted.getOrDefault(a * ratio, 0)
			thirdElementsWanted.put(a * ratio, old + secondElementsWanted.getOrDefault(a, 0))
		}

		// Any element can be the first element of a triplet
		secondElementsWanted.put(a * ratio, secondElementsWanted.getOrDefault(a * ratio, 0) + 1)
	}

	return numTriplets
}

class TripletCount {
	@Test
	fun main() {
		// example01 = 2
		// example02 = 6
		// example03 = 4
		// example04 = 3
		// input02 = 161700
		// input03 = 166661666700000
		// input04 = 0
		// input05 = 0
		// input06 = 2325652489
		// input09 = 0
		// input10 = 1339347780085
		// input11 = 1667018988625
		val scan = Scanner(File("/Users/rodbailey/AndroidStudioProjects/KotlinKoans/app/src/test/java/com" +
				"/bailey/rod/hackerrank/maps/triplets/input11.txt"))
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