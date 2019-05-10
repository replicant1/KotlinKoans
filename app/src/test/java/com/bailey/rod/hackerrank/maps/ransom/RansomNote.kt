package com.bailey.rod.hackerrank.maps.ransom

import org.junit.Test
import java.io.File
import java.util.*

/**
 * Prints "Yes" if the note can be formed using the magazine, otherwise "No".
 *
 * @param magazine Words in the magazine that we an cut out to make
 * the ransom note (case sensitive)
 * @param note Desired words in the ransom note.
 */
fun checkMagazine(magazine: Array<String>, note: Array<String>) {
	val magWordToCount:MutableMap<String, Int> = HashMap()

	// Create map of word->count, which means the given word appears count
	// number of times.
	for (magWord in magazine) {
		var count = magWordToCount.getOrDefault(magWord, 0)
		count++
		magWordToCount.put(magWord, count)
	}

	//println("magWordtoCount=${magWordToCount}")

	var aWordIsMissing = false
	for (noteWord in note) {
		var count = magWordToCount.getOrDefault(noteWord, 0)

		if (count == 0) {
			println("Count of word ${noteWord} is 0")
			aWordIsMissing = true
			break
		} else {
			count -= 1
			magWordToCount.put(noteWord, count)
		}
	}

	println(if (aWordIsMissing) "No" else "Yes")
}

class RansomNote {

	// mom = 2
	// abba = 4, 0
	// ifail = 2, 0

	@Test
	fun doTest() {
//		val magazine = arrayOf("give", "me", "one", "grand", "today", "night")
//		val note = arrayOf("give", "one", "grand", "today")

		val scan = Scanner(File("/Users/rodbailey/AndroidStudioProjects/KotlinKoans/app/src/test/java/com" +
				"/bailey/rod/hackerrank/maps/ransom/input01.txt"))
		val mn = scan.nextLine().split(" ")
		val m = mn[0].trim().toInt()
		val n = mn[1].trim().toInt()
		val magazine = scan.nextLine().split(" ").toTypedArray()
		val note = scan.nextLine().split(" ").toTypedArray()

		checkMagazine(magazine, note)
	}
}
