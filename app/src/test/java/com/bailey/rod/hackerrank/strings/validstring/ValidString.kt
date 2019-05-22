package com.bailey.rod.hackerrank.strings.validstring

import org.junit.Test
import java.io.File
import java.util.*

const val debug = false

// Complete the isValid function below.
fun isValid(s: String): String {
	var valid: Boolean
	val charFreq = Array<Int>(26, { 0 })

	// Count char frequencies in 's'
	for (ch in s) {
		val index = ch.toInt() - 'a'.toInt()
		charFreq[index] = charFreq[index] + 1
	}

	if (debug)
		println("charFreq=${charFreq.toList()}")

	// Check for all chars the same frequency F except one,
	// which is frequency F+1
	if (debug)
		println("Performing secondary validity checks")

	val freqToCount = TreeMap<Int, Int>()

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

	when (freqToCount.keys.size) {
		1 -> {
			if (debug)
				println("Only ONE key, therefore primary valid");
			valid = true
		}
		2 -> {
			if (debug)
				println("TWO keys, checking for secondary validity.")

			if (freqToCount.containsKey(1) && (freqToCount[1] == 1)) {
				// Special case - isolated char
				valid = true
			} else {
				val i = freqToCount.keys.iterator()
				val loFreqKey = i.next()
				val hiFreqKey = i.next()
				val loFreqValue = freqToCount[loFreqKey]
				val hiFreqValue = freqToCount[hiFreqKey]

				if (debug) {
					println("loFreqKey=${loFreqKey}, loFreqValue=${loFreqValue}")
					println("hiFreqKey=${hiFreqKey}, hiFreqValue=${hiFreqValue}")
				}

				valid = (hiFreqValue == 1) && (hiFreqKey - loFreqKey == 1)
			}
		}
		else -> {
			valid = false
			if (debug)
				println("INVALID - too many frequencies")
		}
	}

	return if (valid) "YES" else "NO"
}

class ValidString {
	@Test
	fun main() {
		val scan = Scanner(File("/Users/rodbailey/AndroidStudioProjects/KotlinKoans/app/src/test/java/com" +
				"/bailey/rod/hackerrank/strings/validstring/input/input18.txt"))
		val s = scan.nextLine()
		val result = isValid(s)
		println(result)
	}
}

