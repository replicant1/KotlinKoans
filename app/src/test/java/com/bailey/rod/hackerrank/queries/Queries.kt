package com.bailey.rod.hackerrank.queries

import org.junit.Test
import java.io.File
import java.util.*
import kotlin.collections.HashMap

const val debug = true

/**
 * @param queries 2D array. Each row is an instruction. [q][0] = op code
 * [q][1] = operand
 * @return Output - each element is 0 or 1
 */
fun freqQuery(queries: Array<Array<Int>>): Array<Int> {
	// Key = Operand, Value = Number of times the operand occurs
	val operandToCount = HashMap<Int, Int>()

	// Key = Number of times an operand has occurred, Value = List of operands which have occurred [key] times
	val countToOperands = HashMap<Int, MutableSet<Int>>()

	val result = LinkedList<Int>()

	for (query in queries) {
		val opcode = query[0]
		val operand = query[1]

		if (debug) {
			println("-------------------------------------")
			println("opcode=${opcode}, operand=${operand}")
		}

		when (opcode) {
			1 -> {
				// '1' = Insert the operand
				// Update 'operandToCount' to have one more occurrence of the operand
				val oldOperandCount = operandToCount.getOrDefault(operand, 0)
				val newOperandCount = oldOperandCount + 1
				operandToCount.put(operand, newOperandCount)

				if (debug)
					println("oldOperandCount=${oldOperandCount}, newOperandCount=${newOperandCount}")

				// Update 'countToOperands' in two steps
				// 1) Remove operand from the set of operands that have the current count
				// 2) Add operand to the set of operands that have the current count + 1
				if (countToOperands.containsKey(oldOperandCount)) {
					countToOperands.get(oldOperandCount)?.remove(operand)
				}

				val operandsWithNewCount = countToOperands.getOrDefault(newOperandCount, HashSet<Int>())
				operandsWithNewCount.add(operand)
				countToOperands.put(newOperandCount, operandsWithNewCount)

				if (debug) {
					println("operandToCount=${operandToCount}")
					println("countToOperand=${countToOperands}")
				}
			}

			2 -> {
				// '2' = Delete the operand (if present).
				// Update 'operandToCount' to have one less occurrence of the operand
				val oldOperandCount = operandToCount.getOrDefault(operand, 0)
				val newOperandCount = oldOperandCount - 1
				if (newOperandCount == 0) {
					operandToCount.remove(operand)
				} else {
					operandToCount.put(operand, newOperandCount)
				}

				if (debug)
					println("oldOperandCount=${oldOperandCount}, newOperandCount=${newOperandCount}")

				// Update 'countToOperands' in two steps
				// 1) Remove operand from the set of operands that have the current count
				// 2) Add operand to the set of operands that have the current count - 1
				if (countToOperands.containsKey(oldOperandCount)) {
					countToOperands.get(oldOperandCount)?.remove(oldOperandCount)
				}

				val operandsWithNewCount = countToOperands.getOrDefault(newOperandCount, HashSet<Int>())
				operandsWithNewCount.add(operand)
				countToOperands.put(newOperandCount, operandsWithNewCount)

				if (debug) {
					println("operandToCount=${operandToCount}")
					println("countToOperands=${countToOperands}")
				}
			}

			3 -> {
				// '3' = Check if their is an integer present whose count is the operand
				// If yes, print 1 else 0
				if (countToOperands.containsKey(operand)) {
					result.add(1)
				} else {
					result.add(0)
				}
			}
		} // when opcode
	} // for query

	return result.toTypedArray()
}

class Queries {
	// Correct answers:
	// sample0.txt - 0 1
	// sample1.txt - 0 1
	// sample2.txt - 0 1 1
	@Test
	fun main() {
		val scan = Scanner(File("/Users/rodbailey/AndroidStudioProjects/KotlinKoans/app/src/test/java/com" +
				"/bailey/rod/hackerrank/queries/sample2.txt"))
		val q = scan.nextLine().trim().toInt()
		val queries = Array<Array<Int>>(q, { Array<Int>(2, { 0 }) })
		for (i in 0 until q) {
			queries[i] = scan.nextLine().trimEnd().split(" ").map { it.toInt() }.toTypedArray()
		}
		val ans = freqQuery(queries)

		println(ans.joinToString("\n"))
	}
}
