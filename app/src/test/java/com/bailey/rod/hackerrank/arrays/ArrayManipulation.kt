package com.bailey.rod.hackerrank.arrays

import org.junit.Test
import java.io.File
import java.util.*

fun arrayManipulation(n: Int, operations: Array<Array<Int>>): Long {
	val runs : MutableSet<Run> = HashSet()
	val initialRun : Run = Run(OneBasedRange(1, n), 0)

	runs.add(initialRun)

	println("Initial run = ${initialRun}")

	for (op in operations) {
		println("-------")
		println("Processing operation: ${op.toList()}")
		val opRange = OneBasedRange(op[0], op[1])
		val runsInOpRange : Set<Run> = findRunsInRange(runs, opRange)

	}

	return 0
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
fun findRunsInRange(runs:Set<Run>, range: OneBasedRange) : Set<Run> {
	println("Finding runs in range ${range}, selecting from ${runs.size} runs")
	val result : MutableSet<Run> = HashSet()
	for (run in runs) {
		println("Examining run ${run}")
		if (run.range.intersects(range)) {
			result.add(run)
		}
	}
	println("Runs in range: ${result}")
	return result
}

data class OneBasedRange(val lowIndex: Int, val highIndex : Int) {
	fun containsEndPoint(other : OneBasedRange): Boolean {
		println("Does ${this} intersect ${other}")
		val lowIntersects = (lowIndex > other.lowIndex) && (lowIndex < other.highIndex)
		val highIntersects = (highIndex < other.highIndex) && (highIndex > other.lowIndex)
		val result = lowIntersects || highIntersects
		println("Answer to containsEndPoint=${result}")
		return result
	}

	fun intersects(other: OneBasedRange): Boolean {
		val result = this.containsEndPoint(other) || other.containsEndPoint(this)
		println("result of intersects=${result}")
		return result
	}
}

data class Run(val range: OneBasedRange, val value:Int)

//fun arrayManipulation(n: Int, operations: Array<Array<Int>>): Long {
//	var maxDataValue:Long = 0
//	val data:Array<Long> = Array(n, {0.toLong()})
//
//	for (op in operations) {
//		val lowIndex:Int = op[0] - 1
//		val highIndex:Int  = op[1] - 1
//		val toAdd: Int = op[2]
//
//		for (i in lowIndex..highIndex) {
//			data[i] = data[i] + toAdd.toLong()
//			if (data[i] > maxDataValue) {
//				maxDataValue = data[i]
//			}
//		}
//	}
//
//	return maxDataValue
//}

class ArrayManipulation {

	@Test
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