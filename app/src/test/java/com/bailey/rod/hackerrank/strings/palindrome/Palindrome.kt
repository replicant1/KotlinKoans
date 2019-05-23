package com.bailey.rod.hackerrank.strings.palindrome

import org.junit.Test
import java.io.File
import java.util.*

const val debug = true

/**
 * @param n Length of string [s]
 * @param s String to search for special palindrome substrings
 */
fun substrCount(n: Int, s: String): Long {
	if (debug)
		println("Into substrCount with n=${n}, s=\"${s}\"")

	var result = findMiddleCharDiffPalindromes(n, s)
	//result += findAllCharsSamePalindromes(n, s)

	return result
}

/**
 * Search for substrings where all chars are same e.g. "a", "aa", "aaa"
 */
fun findAllCharsSamePalindromes(n: Int, s: String): Long {
	var result = 0.toLong()

	for (i in 0..(n - 1)) {
		if (debug)
			println("==== i=${i} ====")

		var offset = 0
		var startCh = s[i]
		var palinCount = 0
		var endOfPalin = false

		while (!endOfPalin && ((i + offset) <= (n - 1))) {
			if (debug)
				println("endOfPalin=${endOfPalin} i=${i} offset=${offset}")

			if (s[i + offset] == startCh) {
				palinCount++
				if (debug)
					println("P:${s.substring(i, i + offset + 1)}")
			} else {
				endOfPalin = true
			}

			offset++
		}

		result += palinCount
	}

	return result
}

/**
 * Search for substrings of odd length with a different char in the middle
 * eg. "aabaa", "aca", "x", "aaxaa"
 */
fun findMiddleCharDiffPalindromes(n: Int, s: String): Long {
	var result = 0.toLong()

	for (i in 0..(n - 1)) {
		var offset = 1
		val centreCh = s[i]
		var offsetCh = ' '
		var stopLooking = false

		while (((i - offset) >= 0) && ((i + offset) <= (n - 1)) && !stopLooking) {
			val leftCh = s[i - offset]
			val rightCh = s[i + offset]

			if (leftCh != rightCh) {
				stopLooking = true
				break
			}

			if (leftCh == centreCh) {
				stopLooking = true
				break
			}

			if (offset == 1) {
				offsetCh = leftCh
			}

			if (leftCh != offsetCh) {
				stopLooking = true
				break
			}

			if (debug)
				println("i=${i}, P:${s.substring(i - offset, i + offset + 1)}")

			result++
			offset++
		}  // while
	} // for i

	return result
}

class Palindrome {
	@Test
	fun main() {
		val scan = Scanner(File("/Users/rodbailey/AndroidStudioProjects/KotlinKoans/app/src/test/java/com" +
				"/bailey/rod/hackerrank/strings/palindrome/input/input02.txt"))
		val n = scan.nextLine().trim().toInt()
		val s = scan.nextLine()
		val result = substrCount(n, s)
		println(result)
	}
}
