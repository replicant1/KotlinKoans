package com.bailey.rod.hackerrank.stacksandqueues.twostacks

import org.junit.Test
import java.io.File
import java.util.*

fun process(numQueries: Int, queries: LinkedList<List<Int>>) {
	println("numQueries=${numQueries}, queries=${queries}")
}

class BalancedBrackets {
	@Test
	fun main() {
		val scan = Scanner(File("/Users/rodbailey/AndroidStudioProjects/KotlinKoans/app/src/test/java/com" +
				"/bailey/rod/hackerrank/stacksandqueues/twostacks/input/input00.txt"))
		val numQueries = scan.nextLine().trim().toInt()
		val queries = LinkedList<List<Int>>()

		for (q in 1..numQueries) {
			val ints: Array<Int> = scan.nextLine().split(" ").map { it.trim().toInt() }.toTypedArray()
			queries.add(ints.toList())
		}

		process(numQueries, queries)
	}
}
