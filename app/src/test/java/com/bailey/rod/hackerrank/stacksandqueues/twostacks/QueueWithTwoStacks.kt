package com.bailey.rod.hackerrank.stacksandqueues.twostacks

import org.junit.Test
import java.io.File
import java.util.*

const val debug = false

/**
 * A queue is modelled with two stacks. One stack models the queue as
 * seen from the front, the other stack models the queue as seen from
 * the rear.
 */
fun process(numQueries: Int, queries: LinkedList<List<Int>>) {
	if (debug)
		println("numQueries=${numQueries}, queries=${queries}")

	val frontStack = ArrayList<Int>()
	val rearStack = ArrayList<Int>()

	for (query in queries) {
		when (query[0]) {
			// "1 x" Enqueue integer x
			1 -> {
				rearStack.add(0, query[1])
			}

			// "2" = Dequeue
			2 -> {
				if (frontStack.isEmpty())
					refillFrontStackFromRearStack(frontStack, rearStack)
				frontStack.removeAt(0)
			}

			// "3" = Print element at front of queue
			3 -> {
				if (frontStack.isEmpty())
					refillFrontStackFromRearStack(frontStack, rearStack)
				println("${frontStack[0]}")
			}
		} // when
	} // for
}

fun refillFrontStackFromRearStack(frontStack: ArrayList<Int>, rearStack: ArrayList<Int>) {
	while (!rearStack.isEmpty()) {
		val elt = rearStack[0]
		rearStack.removeAt(0)
		frontStack.add(0, elt)
	}
}


class QueueWithTwoStacks {
	@Test
	fun main() {
		val scan = Scanner(File("/Users/rodbailey/AndroidStudioProjects/KotlinKoans/app/src/test/java/com" +
				"/bailey/rod/hackerrank/stacksandqueues/twostacks/input/input17.txt"))
		val numQueries = scan.nextLine().trim().toInt()
		val queries = LinkedList<List<Int>>()

		for (q in 1..numQueries) {
			val ints: Array<Int> = scan.nextLine().split(" ").map { it.trim().toInt() }.toTypedArray()
			queries.add(ints.toList())
		}

		process(numQueries, queries)
	}
}
