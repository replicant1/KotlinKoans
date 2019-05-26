package com.bailey.rod.hackerrank.stacksandqueues.brackets

import org.junit.Test
import java.io.File
import java.util.*

const val debug = false

// Complete the isBalanced function below.
fun isBalanced(s: String): String {
	val stack = ArrayList<Char>()
	var index = 0
	var mismatchFound = false

	if (debug)
		println("String to test: \"${s}\"")

	while (index <= (s.length - 1) && !mismatchFound) {
		when (s[index]) {
			'{', '[', '(' -> stack.add(0, s[index])
			'}' -> {
				if (stack[0] == '{') {
					stack.removeAt(0)
				} else {
					mismatchFound = true
				}
			}
			']' -> {
				if (stack[0] == '[') {
					stack.removeAt(0)
				} else {
					mismatchFound = true
				}
			}
			')' -> {
				if (stack[0] == '(') {
					stack.removeAt(0)
				} else {
					mismatchFound = true
				}
			}
		} // when

		if (debug) {
			println("index=${index}, stack=${stack}")
		}

		index++
	} // while

	if (debug) {
		println("Exiting while: stack=${stack}, mismatchFound=${mismatchFound}")
	}

	return if ((stack.isEmpty() && !mismatchFound)) "YES" else "NO"
}

class BalancedBrackets {
	@Test
	fun main() {
		val scan = Scanner(File("/Users/rodbailey/AndroidStudioProjects/KotlinKoans/app/src/test/java/com" +
				"/bailey/rod/hackerrank/stacksandqueues/brackets/input/input20.txt"))
		val t = scan.nextLine().trim().toInt()
		for (tItr in 1..t) {
			val s = scan.nextLine()
			val result = isBalanced(s)
			println(result)
		}
	}
}
