package com.bailey.rod.hackerrank.strings.validstring

import org.junit.Test
import java.io.File
import java.util.*
import kotlin.collections.HashMap

const val debug = false

// Complete the isValid function below.
fun isValid(s: String): String {
	var valid = true
	val charFreq = Array<Int>(26, {0})

	// Count char frequencies in 's'
	for (ch in s) {
		val index = ch.toInt() - 'a'.toInt()
		charFreq[index] = charFreq[index] + 1
	}

	if (debug)
		println("charFreq=${charFreq.toList()}")

	// Check for ALL chars the same frequency
	for (i in 1..25) {
		if (charFreq[0] != charFreq[i]) {
			valid = false
			break
		}
	}

	if (debug)
		println("Check for all chars same freq = ${valid}")

	// Check for all chars the same frequency F except one,
	// which is frequency F+1
	if (!valid) {
		if (debug)
			println("Performing secondary validity checks")

		val freqToCount = HashMap<Int, Int>()

		// Create map of char frequency to number of times freq occurs
		for (i in 0..25) {
			val freq = charFreq[i]
			if (freq != 0) {
				var count = freqToCount.getOrDefault(freq, 0)
				count++
				freqToCount.put(freq, count)
			}
		}

		if (debug)
			println("freqToCount=${freqToCount}")

		if (freqToCount.keys.size == 1) {
			// valid = true. All chars occur with same freq
			valid = true
			if (debug)
				println("Conclude VALID because only one key in freqToCount")
		}
		else if (freqToCount.keys.size == 2) {
			if (debug)
				println("There are TWO keys, check for secondary validity")
			val iter = freqToCount.keys.iterator()
			val key1 = iter.next()
			val key2 = iter.next()
			val value1 = freqToCount[key1]
			val value2 = freqToCount[key2]

			if (debug) {
				println("key1=${key1}, value1=${value1}")
				println("key2=${key2}, value2=${value2}")
			}

			if ((value1 == 1) && ((key1 - key2) == 1)) {
				valid = true
				if (debug)
					println("VALID as key1 - key2 == 1 and value1 == 1")
			} else if ((value2 == 1) && ((key2 - key1) == 1)) {
				valid = true
				if (debug)
					println("VALID as key2 - key1 == 1 and value2 == 1")
			} else {
				valid = false
				if (debug)
					println("INVALID as final clause")
			}

		} else {
			valid = false
			if (debug)
				println("INVALID because too many different frequencies")
		}
	}

	return if (valid) "YES" else "NO"
}

class ValidString {
	@Test
	fun main() {
		val scan = Scanner(File("/Users/rodbailey/AndroidStudioProjects/KotlinKoans/app/src/test/java/com" +
				"/bailey/rod/hackerrank/strings/validstring/input/input01.txt"))
		val s = scan.nextLine()
		val result = isValid(s)
		println(result)
	}
}

