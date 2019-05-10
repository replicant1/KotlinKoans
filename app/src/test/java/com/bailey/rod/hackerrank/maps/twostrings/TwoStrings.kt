package com.bailey.rod.hackerrank.maps.twostrings

import org.junit.Test
import java.io.File
import java.util.*
import kotlin.collections.HashSet

/**
 * @return "YES" if s1 and s2 have one or more substrings
 * in common; otherwise "NO"
 */
fun twoStrings(s1: String, s2: String): String {
	val charsNotFound: MutableSet<Char> = HashSet()
	for (s1SubstringStart in 0..(s1.length - 1)) {
		val ch = s1[s1SubstringStart]
		if (!charsNotFound.contains(ch)) {
			if (s2.contains(ch)) {
				return "YES"
			} else {
				charsNotFound.add(ch)
			}
		}
	}
	return "NO"
}

// input01:
// YES
// NO

// input05 answer:
//YES
//YES
//NO
//NO
//YES
//YES
//YES
//YES
//NO
//YES

// input04 correct answer:
// NO
//NO
//YES
//NO
//NO
//YES
//NO
//NO
//YES
//NO
class TwoStrings {
	@Test
	fun main() {
		val scan = Scanner(File("/Users/rodbailey/AndroidStudioProjects/KotlinKoans/app/src/test/java/com" +
				"/bailey/rod/hackerrank/maps/twostrings/input01.txt"))
		val q = scan.nextLine().trim().toInt()
		for (qItr in 1..q) {
			val s1 = scan.nextLine()
			val s2 = scan.nextLine()
//		val s1 = "hi"
//		val s2 = "world"
			val result = twoStrings(s1, s2)

			println(result)
		}
	}
}


