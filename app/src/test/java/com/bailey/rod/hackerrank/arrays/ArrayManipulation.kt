package com.bailey.rod.hackerrank.arrays

import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import org.junit.Test
import java.io.File
import java.util.*

fun arrayManipulation(n: Int, operations: Array<Array<Int>>): Long {
	val runs: MutableSet<Run> = HashSet()

	// Array is filled with zeroes to begin with
	val initialRun: Run = Run(Range(1, n), 0)
	runs.add(initialRun)

	println("Initial (zero) run = ${initialRun}")

	for (op in operations) {
		println("-------------------------------------------------------")
		println("Processing operation: ${op.toList()}")
		val opRange = Range(op[0], op[1])
		val runsInOpRange: Set<Run> = findRunsInRange(runs, opRange)
		println("runsInOpRange=${runsInOpRange}")

		val runsOutOfOpRange = HashSet<Run>()
		runsOutOfOpRange.addAll(runs)
		runsOutOfOpRange.removeAll(runsInOpRange)
		println("runsOutOfOpRange=${runsOutOfOpRange}")

		// Apply operation to all runs in range of the operation
		val operatedRuns = applyOpToRuns(op, runsInOpRange)

		runs.clear()
		runs.addAll(runsOutOfOpRange)
		runs.addAll(operatedRuns)
	}

	return findMaxRunValue(runs)
}

/**
 * Find the largest value amongst all the given [runs]
 *
 * @param All the runs to look through
 * @return The largest value of Run.value out of all [runs]
 */
fun findMaxRunValue(runs: Set<Run>): Long {
	var maxValue: Long = 0
	for (run in runs) {
		if (run.value > maxValue) {
			maxValue = run.value
		}
	}
	return maxValue
}

/**
 * Apply a given operation to all the given runs.
 *
 * @param op The operation to apply
 * @param runsInOpRange The runs to apply the [op] to
 * @return Set of runs that should have the operation applied to them.
 * These runs have been previously determined to be within the range
 * of the given [op].
 */
fun applyOpToRuns(op: Array<Int>, runsInOpRange: Set<Run>): Set<Run> {
	val opRange: Range = Range(op[0], op[1])
	for (run in runsInOpRange) {
		println("Applying op to run ${run}")
		if (run.range.contains(opRange)) {
			println("Contains entire op range")
		} else if (run.range.isContainedBy(opRange)) {
			println("Is contained by op range")
		} else if (run.range.containsLow(opRange)) {
			println("Conains lower end point of op range")
		} else if (run.range.containsHigh(opRange)) {
			println("Contains upper end point of op range")
		}
	}
	return runsInOpRange
}

/**
 * Find those elements of the given set of [runs] that overlap the given [range]
 *
 * @param runs All the runs currently in the data array
 * @param range The range over which some operation operates. This range
 * may overlap some subset of the Runs in [runs]
 * @return All elements of [runs] that overlap the given [range] by one
 * element or more. These are the Runs that will be effected by an operation
 * whose range is [range]
 */
fun findRunsInRange(runs: Set<Run>, range: Range): Set<Run> {
	println("Finding runs in range ${range}. There are ${runs.size} runs to select from.")
	val result: MutableSet<Run> = HashSet()
	for (run in runs) {
		println("  Examining run ${run}")
		if (run.range.intersects(range)) {
			result.add(run)
		}
	}
	println("Returning: Runs in operation range: ${result}")

	return result
}

/**
 * A pair of 1-based indexes into a single dimension / array / list.
 * Together they specify a continuous interval with inclusive end points.
 *
 * @param low The lower bound on the range (inclusive)
 * @param high The upper bound on the range (inclusive)
 *
 * [low] must be [high]
 */
data class Range(val low: Int, val high: Int) {
	fun containsLow(other: Range): Boolean {
		val result = (other.low >= low) && (other.low <= high)
		println("Does ${this} containsLow of ${other} is ${result}")
		return result
	}

	fun containsHigh(other: Range): Boolean {
		val result = (other.high >= low) && (other.high <= high)
		println("Does ${this} containUpperEndPoint of ${other} is ${result}")
		return result
	}

	fun contains(other: Range): Boolean {
		val result = containsLow(other) && containsHigh(other)
		println("Does ${this} contain ${other} is ${result}")
		return result
	}

	fun isContainedBy(other: Range): Boolean {
		val result = other.contains(this)
		println("Is ${this} contained by ${other} is ${result}")
		return result
	}

	fun intersects(other: Range): Boolean {
		val result = containsLow(other) || containsHigh(other)
				|| contains(other) || isContainedBy(other);
		println("Does ${this} intersect ${other} is ${result}")
		return result
	}
}

data class Run(val range: Range, val value: Long)

class ArrayManipulation {
	companion object {
		val oneOne = Range(1, 1)
		val oneThree = Range(1, 3)
		val oneFive = Range(1, 5)
		val oneSeven = Range(1, 7)
		val twoTwo = Range(2, 2)
		val twoFour = Range(2, 4)
		val threeFive = Range(3, 5)
		val threeSeven = Range(3, 7)
		val fiveSeven = Range(5, 7)
		val fiveNine = Range(5, 9)
		val sevenFive = Range(7, 5)
		val sevenEight = Range(7, 8)
		val sevenNine = Range(7, 9)
		val oneTen = Range(1, 10)
		val threeNine = Range(3, 9)
	}

	@Test
	fun testContains() {
		assertTrue(oneThree.contains(oneOne))
		assertTrue(oneTen.contains(twoFour))
		assertFalse(oneOne.contains(oneThree))
		assertFalse(fiveNine.contains(oneFive))
	}

	@Test
	fun testContainsLow() {
		assertTrue(oneOne.containsLow(oneOne))
		assertTrue(oneOne.containsLow(oneThree))
		assertTrue(oneThree.containsLow(twoTwo))
		assertTrue(oneThree.containsLow(twoFour))
		assertFalse(oneThree.containsLow(fiveSeven))
		assertFalse(threeFive.containsLow(oneThree))
		assertFalse(sevenFive.containsLow(oneTen))
	}

	@Test
	fun testContainsHigh() {
		assertTrue(oneOne.containsHigh(oneOne))
		assertTrue(oneThree.containsHigh(oneOne))
		assertTrue(oneTen.containsHigh(threeNine))
		assertTrue(fiveSeven.containsHigh(threeSeven))
		assertTrue(threeFive.containsHigh(oneThree))
		assertFalse(twoFour.containsHigh(oneOne))
		assertFalse(sevenEight.containsHigh(sevenNine))
	}

	//@Test
	fun main() {
		// Correct answer = 7542539201
		//                  7542539201
		val scan = Scanner(File("/Users/rodbailey/AndroidStudioProjects/KotlinKoans/app/src/test/java/com" +
				"/bailey/rod/hackerrank/arrays/input02.txt"))
		val nm = scan.nextLine().split(" ")
		val n = nm[0].trim().toInt()
		val m = nm[1].trim().toInt()
		val queries = Array<Array<Int>>(m, { Array<Int>(3, { 0 }) })
		for (i in 0 until m) {
			queries[i] = scan.nextLine().split(" ").map { it.trim().toInt() }.toTypedArray()
		}

//		val n = 10
//		val queries = arrayOf(
//				arrayOf(1,5,3),
//				arrayOf(4,8,7),
//				arrayOf(6,9,1))
		val result = arrayManipulation(n, queries)

		println(result)
	}
}