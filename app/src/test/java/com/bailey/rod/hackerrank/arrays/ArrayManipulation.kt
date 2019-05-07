package com.bailey.rod.hackerrank.arrays

import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import org.junit.Test
import java.io.File
import java.util.*
import kotlin.collections.HashSet

fun arrayManipulation(n: Int, operations: Array<Array<Int>>): Long {
	val runs: MutableSet<Run> = HashSet()

	// Array is filled with zeroes to begin with
	val initialRun: Run = Run(Range(1, n), 0)
	runs.add(initialRun)

	//println("Initial (zero) run = ${initialRun}")

	for ((opIndex, opInts) in operations.withIndex()) {
		//println("-------------------------------------------------------")
		val op = Operation(Range(opInts[0], opInts[1]), opInts[2])
		//println("OP ${opIndex} / ${operations.size}: ${op}")

		// Find those runs that overlap the operation's range
		val runsInOpRange: Set<Run> = findRunsInRange(runs, op.range)
		//println("runsInOpRange: ${runsInOpRange}")

		// Find those runs that DON'T overlap the operations range
		val runsOutOfOpRange = HashSet<Run>()
		runsOutOfOpRange.addAll(runs)
		runsOutOfOpRange.removeAll(runsInOpRange)
		//println("runsOutOfOpRange: ${runsOutOfOpRange}")

		// Apply operation to all runs in range of the operation
		val operatedRuns = applyOpToRuns(op, runsInOpRange)

		// Combine the modified and unmodified runs
		runs.clear()
		runs.addAll(runsOutOfOpRange)
		runs.addAll(operatedRuns)
	}

	println(runs)

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
fun applyOpToRuns(op: Operation, runsInOpRange: Set<Run>): Set<Run> {
	val appliedRuns: MutableSet<Run> = HashSet()

	for (run in runsInOpRange) {
		//println("APPLYING ${op} TO ${run}")
		if (run.range.contains(op.range)) {
			appliedRuns.addAll(applyWhenRunContainsOp(run, op))
		} else if (run.range.isContainedBy(op.range)) {
			appliedRuns.addAll(applyWhenRunIsContainedByOp(run, op))
		} else if (run.range.containsLow(op.range)) {
			appliedRuns.addAll(applyWhenRunContainsOpLowOnly(run, op))
		} else if (run.range.containsHigh(op.range)) {
			appliedRuns.addAll(applyWhenRunContainsOpHighOnly(run, op))
		}
	}

	return appliedRuns
}

fun applyWhenRunIsContainedByOp(run: Run, op: Operation): Set<Run> {
	//println("Enter applyWhenRunIsContainedByOp with run=${run} and op=${op}")
	var result: MutableSet<Run> = HashSet()

	// Run within the run.range
	result.add(Run(run.range, run.value + op.toAdd))

	//println("Exit applyWhenRunIsContainedByOp with ${result}")
	return result
}

fun applyWhenRunContainsOpHighOnly(run: Run, op: Operation): Set<Run> {
	//println("Enter applyWhenRunContainsOpHighOnly with run=${run} and op=${op}")
	val result: MutableSet<Run> = HashSet()

	// Run after the op.range ends
	if (op.range.high < run.range.high) {
		result.add(Run(Range(op.range.high + 1, run.range.high), run.value))
	}

	// Run within the op.range
	result.add(Run(Range(run.range.low, op.range.high), run.value + op.toAdd))

	//println("Exit applyWhenRunContainsOpHighOnly with ${result}")

	return result
}

fun applyWhenRunContainsOpLowOnly(run: Run, op: Operation): Set<Run> {
	//println("Enter applyWhenRunCongtainsOpLowOnly with run=${run} and op=${op}")
	val result: MutableSet<Run> = HashSet()

	// Run before the op.range starts
	if (run.range.low < op.range.low) {
		result.add(Run(Range(run.range.low, op.range.low - 1), run.value))
	}

	// Run within the op.range
	result.add(Run(Range(op.range.low, run.range.high), run.value + op.toAdd))

	//println("Exit applyWhenRunContainsOpLowOnly with ${result}")

	return result
}

fun applyWhenRunContainsOp(run: Run, op: Operation): Set<Run> {
	//println("Enter applyWhenRunContainsOp because ${run} contains ${op}")
	val result: MutableSet<Run> = HashSet()

	// Run before the op.range
	if (run.range.low < op.range.low) {
		result.add(Run(Range(run.range.low, op.range.low - 1), run.value))
	}

	// Run after the op.range
	if (run.range.high > op.range.high) {
		result.add(Run(Range(op.range.high + 1, run.range.high), run.value))
	}

	// Run within the op.range
	result.add(Run(op.range, run.value + op.toAdd))

	//println("Exit applyWhenRunContainsOp returns ${result}")

	return result
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
	val result: MutableSet<Run> = HashSet()
	for (run in runs) {
		if (run.range.intersects(range)) {
			result.add(run)
		}
	}
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

	override fun toString(): String {
		return "[${low}-${high}]"
	}

	fun containsLow(other: Range): Boolean {
		val result = (other.low >= low) && (other.low <= high)
		return result
	}

	fun containsHigh(other: Range): Boolean {
		val result = (other.high >= low) && (other.high <= high)
		return result
	}

	fun contains(other: Range): Boolean {
		val result = containsLow(other) && containsHigh(other)
		return result
	}

	fun isContainedBy(other: Range): Boolean {
		val result = other.contains(this)
		return result
	}

	fun intersects(other: Range): Boolean {
		val result = containsLow(other) || containsHigh(other)
				|| contains(other) || isContainedBy(other);
		return result
	}
}

data class Run(val range: Range, val value: Long) {
	override fun toString(): String {
		return "${range}=${value}"
	}
}

data class Operation(val range: Range, val toAdd: Int) {
	override fun toString(): String {
		return "${range}+${toAdd}"
	}
}

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
		val sevenEight = Range(7, 8)
		val sevenNine = Range(7, 9)
		val oneTen = Range(1, 10)
		val threeNine = Range(3, 9)
	}

	//@Test
	fun testIntersects() {
		assertTrue(oneFive.intersects(threeSeven))
		assertTrue(threeSeven.intersects(oneFive))
		assertTrue(oneFive.intersects(threeSeven))
		assertTrue(oneSeven.intersects(sevenEight))
		assertTrue(oneTen.intersects(twoTwo))
		assertFalse(oneFive.intersects(sevenEight))
		assertFalse(sevenEight.intersects(oneFive))
	}

	//@Test
	fun testIsContainedBy() {
		assertTrue(oneOne.isContainedBy(oneThree))
		assertTrue(twoFour.isContainedBy(oneTen))
		assertFalse(oneThree.isContainedBy(oneOne))
		assertFalse(oneFive.isContainedBy(fiveNine))
	}

	//@Test
	fun testContains() {
		assertTrue(oneThree.contains(oneOne))
		assertTrue(oneTen.contains(twoFour))
		assertFalse(oneOne.contains(oneThree))
		assertFalse(fiveNine.contains(oneFive))
	}

	//@Test
	fun testContainsLow() {
		assertTrue(oneOne.containsLow(oneOne))
		assertTrue(oneOne.containsLow(oneThree))
		assertTrue(oneThree.containsLow(twoTwo))
		assertTrue(oneThree.containsLow(twoFour))
		assertFalse(oneThree.containsLow(fiveSeven))
		assertFalse(threeFive.containsLow(oneThree))
	}

	//@Test
	fun testContainsHigh() {
		assertTrue(oneOne.containsHigh(oneOne))
		assertTrue(oneThree.containsHigh(oneOne))
		assertTrue(oneTen.containsHigh(threeNine))
		assertTrue(fiveSeven.containsHigh(threeSeven))
		assertTrue(threeFive.containsHigh(oneThree))
		assertFalse(twoFour.containsHigh(oneOne))
		assertFalse(sevenEight.containsHigh(sevenNine))
	}

	@Test
	fun main() {
		// input01 = 882
		// input04  = 7542539201
		// input05  = 7496167325
		// input06  = 7515267971
		// input07  = 2497169732
		// input08 = 2484930878
		// input09 = 2501448788
		// input10 = 2510535321
		// input11 = 2506721627
		val scan = Scanner(File("/Users/rodbailey/AndroidStudioProjects/KotlinKoans/app/src/test/java/com" +
				"/bailey/rod/hackerrank/arrays/input06.txt"))
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