package com.bailey.rod.hackerrank.maps.anagrams

import org.junit.Test
import java.io.File
import java.util.*
import kotlin.collections.HashMap

// Complete the sherlockAndAnagrams function below.
fun sherlockAndAnagrams(s: String): Int {
	var numPairsAllLengths = 0

	for (ssLength in 1..(s.length-1)) {
		println("---- Substring length ${ssLength} ----")
		val substrings = findAllSubstringsOfLength(s, ssLength)
		println("Found ${substrings.size} substrings of length ${ssLength}")
		val sssToCount: MutableMap<String, Int> = HashMap() // sss = sorted substring

		for (i in 0..(substrings.size - 1)) {
			println("substring[${i}] = \"${substrings[i]}\"")
			val sortedSubstring = String(substrings[i].toCharArray().sortedArray())
			incrementStringCount(sortedSubstring, sssToCount)
		}

		println("Sorted substring map ${sssToCount}")

		sssToCount.forEach({
			val sortedSubstring = it.key
			val sortedSubstringCount = it.value
			if (sortedSubstringCount > 0) {
				val numPairs = nChoose2(sortedSubstringCount)
				println("\"${sortedSubstring}\" => ${sortedSubstringCount}. #pairs=${numPairs}")
				numPairsAllLengths += numPairs
			}
		})

	}

	return numPairsAllLengths
}

/**
 * @return nC2 ("n choose 2") is the number of pair-wise selections from [n]
 */
fun nChoose2(n:Int) : Int {
	return n * (n - 1) / 2
}

/**
 * @return Map in which the value against the key [str] is incremented by 1
 */
fun incrementStringCount(str: String, strToCount: MutableMap<String, Int>) {
	var count = strToCount.getOrDefault(str, 0)
	count++
	strToCount.put(str,  count)
}

/**
 * @return All substrings of [s] of length [len] chars. Ordered by
 * ascending start position (0-based - an index into [s])
 */
fun findAllSubstringsOfLength(s: String, len: Int): List<String> {
	var result: MutableList<String> = LinkedList()

	for (i in 0..(s.length - len)) {
		var substring = s.substring(i, i + len)
		result.add(substring)
	}

	return result
}

class Sherlock {
	// Correct answers:
	// mom.txt : mom=2
	// abba.txt : abba=4, abcd=0
	// ifail.txt : ifailuhkqq = 3, kkkk = 10
	// cdcd.txt : cdcd= 5

	@Test
	fun main() {
		val scan = Scanner(File("/Users/rodbailey/AndroidStudioProjects/KotlinKoans/app/src/test/java/com" +
				"/bailey/rod/hackerrank/maps/anagrams/cdcd.txt"))
		val q = scan.nextLine().trim().toInt()

		for (qItr in 1..q) {
			val s = scan.nextLine()
			val result = sherlockAndAnagrams(s)
			println("**** NUMBER OF ANAGRAMATIC PAIRS IN \"${s}\" ****")
			println(result)
		}
	}
}