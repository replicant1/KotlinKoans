package com.bailey.rod.hackerrank.maps.queries

import org.junit.Test
import java.io.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import kotlin.collections.HashSet


const val debug = false

/**
 * @param queries 2D array. Each row is an instruction. [q][0] = operation code
 * [q][1] = operand ('op')
 * @return Output - each element is 0 or 1
 */
fun freqQuery(queries: Array<Array<Int>>): Array<Int> {
	// Key = Operand, Value = Number of times the operand occurs
	val opToCount = HashMap<Int, Int>()

	// Key = Number of times an operand has occurred,
	// Value = List of operands which have occurred [key] times
	val countToOps = HashMap<Int, MutableSet<Int>>()

	val result = ArrayList<Int>()

	for (query in queries) {
		val op = query[1]

		if (debug) {
			println("-------------------------------------")
			println("opcode=${query[0]}, operand=${op}")
		}

		when (query[0]) {
			1 -> {
				// '1' = Insert the operand
				// Update 'operandToCount' to have +1 occurrence of  operand
				val oldOpCount = opToCount.getOrDefault(op, 0)
				val newOpCount = oldOpCount + 1
				opToCount.put(op, newOpCount)

				// Update 'countToOps' in two steps
				// 1) Remove op from the set of ops with oldOpCount
				// 2) Add op to set of ops with newOpCount
				if (countToOps.containsKey(oldOpCount)) {
					val oldOperands = countToOps.get(oldOpCount)
					oldOperands!!.remove(op)
					if (oldOperands.isEmpty()) {
						countToOps.remove(oldOpCount)
					}
				}

				val opsWithNewCount =
						countToOps.getOrDefault(newOpCount, HashSet())
				opsWithNewCount.add(op)
				// Only necessary first time
				countToOps.put(newOpCount, opsWithNewCount)

				if (debug) {
					println("operandToCount=${opToCount}")
					println("countToOperand=${countToOps}")
				}
			}

			2 -> {
				// '2' = Delete the operand (if present).
				// Update 'operandToCount' to have +1 occurrence of op
				if (opToCount.containsKey(op)) {
					val oldOperandCount = opToCount.getOrDefault(op, 0)
					val newOperandCount = oldOperandCount - 1
					if (newOperandCount == 0) {
						opToCount.remove(op)
					} else {
						opToCount.put(op, newOperandCount)
					}

					if (debug)
						println("oldOperandCount=${oldOperandCount}, newOperandCount=${newOperandCount}")

					// Update 'countToOperands' in two steps
					// 1) Remove operand from the set of operands that have the current count
					// 2) Add operand to the set of operands that have the current count - 1
					if (countToOps.containsKey(oldOperandCount)) {
						val oldOperands = countToOps.get(oldOperandCount) ?: HashSet()
						oldOperands.remove(op)
						if (oldOperands.isEmpty()) {
							countToOps.remove(oldOperandCount)
						}
					}

					val operandsWithNewCount = countToOps.getOrDefault(newOperandCount, HashSet())
					operandsWithNewCount.add(op)
					countToOps.put(newOperandCount, operandsWithNewCount) // Only necessary first time

					if (debug) {
						println("operandToCount=${opToCount}")
						println("countToOperands=${countToOps}")
					}
				} else {
					if (debug) {
						println("Operand ${op} is not present")
					}
				}
			}

			3 -> {
				// '3' = Check if their is an integer present whose count is the operand
				// If yes, print 1 else 0
				if (countToOps.containsKey(op)) {
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
//		val bufReader = BufferedReader(InputStreamReader(System.`in`))
		val bufReader = BufferedReader(FileReader("/Users/rodbailey/AndroidStudioProjects/KotlinKoans/app/src/test/java/com" +
				"/bailey/rod/hackerrank/maps/queries/input09.txt"))
		val numQueries = bufReader.readLine().trim().toInt()
		val queries = Array<Array<Int>>(numQueries, { Array<Int>(2, { 0 }) })

		var lineStrings:List<String>
		for (i in 0 until numQueries) {
			lineStrings = bufReader.readLine().split(" ")
			queries[i][0] = lineStrings[0].toInt()
			queries[i][1] = lineStrings[1].toInt()
		}

		val ans = freqQuery(queries)
		val ansStr = ans.joinToString("\n")

		val sw = StringWriter()
		val bw = BufferedWriter(sw)
		bw.write(ansStr)
		bw.flush()
		val sb = sw.getBuffer()
		println(sb)
	}
}
