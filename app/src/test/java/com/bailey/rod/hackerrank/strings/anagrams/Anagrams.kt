package com.bailey.rod.hackerrank.strings.anagrams

import org.junit.Test
import java.io.File
import java.util.*

const val debug = false

/**
 * @param a First string
 * @param b Second string
 * @return Number of character deletions needed until a and b are anagrams
 */
fun makeAnagram(a: String, b: String): Int {
	if (debug)
		println("makeAnagram: a=\"${a}\", b=\"${b}\"")

	val aMap = toFreqMap(a)
	val bMap = toFreqMap(b)
	var numCharsDeleted = 0

	if (debug) {
		println("aMap:${aMap}")
		println("bMap:${bMap}")
	}

	val aIter = aMap.iterator()
	while (aIter.hasNext()) {
		val aEntry = aIter.next()

		if (debug)
			println("aEntry:${aEntry}")

		if (bMap.containsKey(aEntry.key)) {
			if (debug)
				println("${aEntry.key} is a key in bMap")

			// bMap contains the same char once or more. Make freq's the same
			// for this char in aMap and bMap
			val aValue:Int = aEntry.value
			val bValue:Int = bMap.get(aEntry.key) ?: 0

			if (aValue > bValue) {
				numCharsDeleted += (aValue - bValue)
				aMap.put(aEntry.key, bValue)
			} else if (bValue > aValue) {
				numCharsDeleted += (bValue - aValue)
				bMap.put(aEntry.key, aValue)
			} else {
				// Same freq for this char so no deletion required
			}
		} else {
			// bMap doesn't contain the same char. Remove char from aMap.
			numCharsDeleted += aEntry.value
			aIter.remove()
		}
	}

	val bIter = bMap.iterator()
	while (bIter.hasNext()) {
		val bEntry = bIter.next()

		if (debug)
			println("bEntry:${bEntry}")

		if (!aMap.containsKey(bEntry.key)) {
			numCharsDeleted += bEntry.value
			bIter.remove()
		}
	}

	// What about char in bMap but NOT in aMap

	return numCharsDeleted
}

fun toFreqMap(str:String):MutableMap<Char, Int> {
	val freqMap = HashMap<Char, Int>()
	for (ch in str) {
		val oldFreq = freqMap.getOrDefault(ch, 0)
		freqMap.put(ch, oldFreq + 1)
	}
	return freqMap
}


// sample0.txt = 4
// sample1.txt = 2
// sample3.txt = 2
class Anagrams {
	@Test
	fun main() {
		val scan = Scanner(File("/Users/rodbailey/AndroidStudioProjects/KotlinKoans/app/src/test/java/com" +
				"/bailey/rod/hackerrank/strings/sample1.txt"))
		val a = scan.nextLine()
		val b = scan.nextLine()
		val res = makeAnagram(a, b)
		println(res)
	}
}
