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

	var result = 0.toLong()

	// Search for substrings of odd length with a different char in the middle
	// eg. "aabaa", "aca", "x", "aaxaa"
	for (i in 0..(n - 1)) {
		if (debug)
			println("----- i=${i} -----")

		var offset = 1
		val centre_ch = s[i]
		var palins_i = 0
		var non_centre_ch = ' '

		while (((i - offset) >= 0) && ((i + offset) <= (n - 1))) {
			val left_ch = s[i - offset]
			val right_ch = s[i + offset]

			if (debug)
				println("i=${i} w=${offset} left_ch=${left_ch} " +
						"right_ch=${right_ch}")

			if ((left_ch == right_ch) && (left_ch != centre_ch)) {
				if (offset == 1) {
					non_centre_ch = left_ch
				}
				else if (left_ch != non_centre_ch) {
					break
				}

				if (debug)
					println("P:${s.substring(i - offset, i + offset + 1)}")

				palins_i++
			}
			offset++
		}  // while

		if (debug)
			println("Found ${palins_i} palindromes about i=${i}")

		result += palins_i
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
