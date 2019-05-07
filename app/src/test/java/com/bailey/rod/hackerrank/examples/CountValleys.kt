package com.bailey.rod.hackerrank.examples

import org.junit.Test

/**
 * @param baseStr The base string that is notionally repeated an infinite
 * number of times.
 * @param truncatedLength Number of characters in the repeated baseStr to consider when counting
 * character occurences.
 */
fun repeatedString(baseStr: String, truncatedLength: Long): Long {
	println("Into repeatedString with baseStr=${baseStr} and truncatedLength=${truncatedLength}")
	val baseStrLength = baseStr.length
	var result: Long = 0

	if (truncatedLength > baseStrLength) {
		println("Into truncatedLength > sLength clause")

		// Number of whole repetitions of baseStr that fit into truncatedLength
		val intBaseStrReps: Long = truncatedLength / baseStr.length

		// Number of chars to make up
		val charsRemaining: Long = truncatedLength - (intBaseStrReps * baseStrLength)

		println("intBaseStrReps=${intBaseStrReps}")
		println("charsRemaining=${charsRemaining}")

		val intBaseStrAs = numberOfAs(baseStr) * intBaseStrReps
		println("intBaseStrAs = ${intBaseStrAs}")

		val charsRemainingAs = numberOfAs(baseStr.substring(0..charsRemaining.toInt() - 1))
		println("charsRemainingAs=${charsRemainingAs}")

		result = intBaseStrAs + charsRemainingAs
	} else {
		println("Into truncatedLength <= sLength clause")
		result = numberOfAs(baseStr.substring(0..truncatedLength.toInt()))
	}

	return result
}

/**
 * @return Key = char in s, Value = Number of times char occurs in s
 */
fun numberOfAs(s: String): Long {
	println("Into numberOfAs with s=${s}")
	var result: Long = 0

	for (ch in s) {
		if (ch == 'a') {
			result++
		}
	} // for

	println("Returning from numberOfAs with result=${result}")
	return result
} // fun

class CountValleys {

	@Test
	fun main() {
//		val scan = Scanner(System.`in`)
//		val s = scan.nextLine()
//		val n = scan.nextLine().trim().toLong()

		val result = repeatedString("ababa", 3)
		println(result)
	}
}
