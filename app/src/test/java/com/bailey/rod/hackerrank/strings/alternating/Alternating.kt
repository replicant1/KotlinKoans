package com.bailey.rod.hackerrank.strings.alternating

import org.junit.Test
import java.io.File
import java.util.*

fun alternatingCharacters(s: String): Int {
	var numCharDeletions: Int = 0
	for ((index, ch) in s.withIndex()) {
		if (index < s.length - 1) {
			val nextCh = s[index + 1]
			if (ch == nextCh) {
				numCharDeletions++
			}
		}
	}
	return numCharDeletions
}

class Alternating {
	@Test
	fun main() {
		val scan = Scanner(File("/Users/rodbailey/AndroidStudioProjects/KotlinKoans/app/src/test/java/com" +
				"/bailey/rod/hackerrank/strings/alternating/sample0.txt"))
		val q = scan.nextLine().trim().toInt()
		for (qItr in 1..q) {
			val s = scan.nextLine()
			val result = alternatingCharacters(s)
			println(result)
		}
	}
}
