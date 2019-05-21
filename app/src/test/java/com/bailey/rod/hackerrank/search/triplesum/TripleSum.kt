package com.bailey.rod.hackerrank.search.triplesum

import org.junit.Test
import java.io.File
import java.util.*

const val debug = false

fun triplets(a: Array<Int>, b: Array<Int>, c: Array<Int>): Long {
	if (debug)
		println("Raw: ${a.size},${b.size},${c.size}")

	val aDeduped = ArrayList<Int>(HashSet<Int>(a.asList()))
	val bDeduped = ArrayList<Int>(HashSet<Int>(b.asList()))
	val cDeduped = ArrayList<Int>(HashSet<Int>(c.asList()))

	if (debug)
		println("Deduped: ${aDeduped.size},${bDeduped.size},${cDeduped.size}")

	aDeduped.sort()
	bDeduped.sort()
	cDeduped.sort()

	var tripletTally = 0.toLong()

	for (q in bDeduped) {
		val qIndexInA = indexOfLastElementLTE(aDeduped, q)
		val qIndexInC = indexOfLastElementLTE(cDeduped, q)
		val numTripletsAboutQ = (qIndexInA + 1) * (qIndexInC + 1)
		tripletTally += numTripletsAboutQ
	}

	return tripletTally
}

fun indexOfLastElementLTE(arrAscending: ArrayList<Int>, target: Int): Int {
	var result = -1

	for ((index, elt) in arrAscending.withIndex()) {
		if (elt > target) {
			break
		} else {
			// elt <= target
			result = index
		}
	}

	return result
}



// sample0.txt = 8
// sample1.txt = 5
// sample2.txt = 12
class TripleSum {
	@Test
	fun main() {
		val scan = Scanner(File("/Users/rodbailey/AndroidStudioProjects/KotlinKoans/app/src/test/java/com" +
				"/bailey/rod/hackerrank/search/triplesum/sample0.txt"))
		val lenaLenbLenc = scan.nextLine().split(" ")
		val lena = lenaLenbLenc[0].trim().toInt()
		val lenb = lenaLenbLenc[1].trim().toInt()
		val lenc = lenaLenbLenc[2].trim().toInt()
		val arra = scan.nextLine().split(" ").map { it.trim().toInt() }.toTypedArray()
		val arrb = scan.nextLine().split(" ").map { it.trim().toInt() }.toTypedArray()
		val arrc = scan.nextLine().split(" ").map { it.trim().toInt() }.toTypedArray()
		val ans = triplets(arra, arrb, arrc)
		println(ans)
	}
}
