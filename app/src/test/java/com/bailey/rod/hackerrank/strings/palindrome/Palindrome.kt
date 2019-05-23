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

	return result
}

/**
 * Search for substrings of odd length with a different char in the middle
 * eg. "aabaa", "aca", "x", "aaxaa"
 */
fun findMiddleCharDiffPalindromes(n: Int, s: String): Long {
	var result = 0.toLong()

	for (i in 0..(n - 1)) {
		if (debug)
			println("----- i=${i} -----")

		var offset = 1
		val centreCh = s[i]
		var palinCount = 0
		var nonCentreCh = ' '

		while (((i - offset) >= 0) && ((i + offset) <= (n - 1))) {
			val leftCh = s[i - offset]
			val rightCh = s[i + offset]

			if (debug)
				println("i=${i} w=${offset} left_ch=${leftCh} " +
						"right_ch=${rightCh}")

			if ((leftCh == rightCh) && (leftCh != centreCh)) {
				if (offset == 1) {
					nonCentreCh = leftCh
				} else if (leftCh != nonCentreCh) {
					break
				}

				if (debug)
					println("P:${s.substring(i - offset, i + offset + 1)}")

				palinCount++
			}
			offset++
		}  // while

		if (debug)
			println("Found ${palinCount} palindromes about i=${i}")

		result += palinCount
	} // for i
	return result
}

class Palindrome {
	@Test
	fun main() {
		val scan = Scanner(File("/Users/rodbailey/AndroidStudioProjects/KotlinKoans/app/src/test/java/com" +
				"/bailey/rod/hackerrank/strings/palindrome/input/input00.txt"))
		val n = scan.nextLine().trim().toInt()
		val s = scan.nextLine()
		val result = substrCount(n, s)
		println(result)
	}
}
