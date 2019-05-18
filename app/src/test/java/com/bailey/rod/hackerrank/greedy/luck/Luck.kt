package com.bailey.rod.hackerrank.greedy.luck

import org.junit.Test
import java.io.File
import java.util.*

const val debug = false

/**
 * @param k Number of 'important' contests you loose
 * @param contests Each row is a contest. [0] = points, [1] = 1 if important
 */
fun luckBalance(k: Int, contests: Array<Array<Int>>): Int {
	if (debug)
		println("k=${k}, #contests=${contests.size}")

	// Key = A number of points awarded for winning the contest
	// Value = Number of contests with 'key' number of points
	val impContestsPerPoints: MutableMap<Int, Int> = TreeMap<Int, Int>()

	// Tally of points for all unimportant contests. Only tally points for these
	// as we will definitely elect to lose them in order to maximise luck.
	var unimpPointsTally = 0

	for (contest in contests) {
		val points = contest[0]
		val important = contest[1]

		if (debug)
			println("---- contest: points=${points}, important=${important}")

		if (important == 1) {
			val count = impContestsPerPoints.getOrDefault(points, 0)
			impContestsPerPoints.put(points, count + 1)
			if (debug)
				println("That's ${count + 1} contests with ${points} points")
		} else {
			unimpPointsTally += points
			if (debug)
				println("unimpPointsTally increments by ${points} to ${unimpPointsTally}")
		}
	}

	if (debug) {
		println("unimpPointsTally=${unimpPointsTally}")
		println("impContestsPerPoints=${impContestsPerPoints}")
		println("**** Finished first pass over all contests ****")
	}

	val impPoints = impContestsPerPoints.keys

	if (debug)
		println("Before reversal, impPoints=${impPoints}")

	// Iteration order = most valuable to least valuable contests.
	val iter = impPoints.reversed().iterator()
	var contestsRemaining = k
	var impPointsLossTally = 0
	var impPointsGainTally = 0

	while (iter.hasNext()) {
		val points = iter.next()
		val contests = impContestsPerPoints.get(points) ?: 0

		if (debug)
			println("--- points=${points}, contests=${contests}, " +
					"contestsRemaining=${contestsRemaining}" +
					" ---")

		if (contests >= contestsRemaining) {
			if (debug)
				println("contests >= contestsRemaining")

			impPointsLossTally += (points * contestsRemaining)
			val newContests = contests - contestsRemaining
			impContestsPerPoints.put(points, newContests)
			contestsRemaining = 0
			impPointsGainTally += (points * newContests)
		} else {
			if (debug)
				println("contests < contestsRemaining")

			impPointsLossTally += (points * contests)
			impContestsPerPoints.remove(points)
			contestsRemaining -= contests
		}
	}

	return impPointsLossTally - impPointsGainTally + unimpPointsTally
}

// sample0.txt = 29
// sample1.txt = 10 (k == 2), 8 (k == 1)
class Luck {
	@Test
	fun main() {
		val scan = Scanner(File("/Users/rodbailey/AndroidStudioProjects/KotlinKoans/app/src/test/java/com" +
				"/bailey/rod/hackerrank/greedy/luck/sample1.txt"))
		val nk = scan.nextLine().split(" ")
		val n = nk[0].trim().toInt()
		val k = nk[1].trim().toInt()
		val contests = Array<Array<Int>>(n, { Array<Int>(2, { 0 }) })
		for (i in 0 until n) {
			contests[i] = scan.nextLine().split(" ").map { it.trim().toInt() }.toTypedArray()
		}
		val result = luckBalance(k, contests)

		println(result)
	}
}

