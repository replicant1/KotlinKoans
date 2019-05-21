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

	val t0 = System.currentTimeMillis()
	aDeduped.sort()
	bDeduped.sort()
	cDeduped.sort()
	val t1 = System.currentTimeMillis()

	if (debug) {
		println("3 x sort = ${t1 - t0} ms")
		println("aDeduped=${aDeduped}")
		//println("bDeduped=${bDeduped}")
	}

	var tripletTally: Long = 0
	var firstLTETime: Long = 0
	var secondLTETime: Long = 0

	var indexA = 0
	var indexC = 0

	for (q in bDeduped) {
		if (debug)
			println("\n****** q=${q} ******")

		if (debug)
			println(">>> Calling indexOfLTE for A array")
		indexA = searchAhead(aDeduped, q, indexA)

		if (debug)
			println(">>> Calling indexOfLTE for C array")

		if (indexA > 0) {
			indexC = searchAhead(cDeduped, q, indexC)
		}

		if ((indexA > 0) && (indexC > 0)) {
			val numTripletsAboutQ = indexA.toLong() * indexC.toLong()
			tripletTally += numTripletsAboutQ
			if (debug)
				println("${numTripletsAboutQ} triplets found about q=${q}")
		}
		else {
			if (debug)
				println("No triplets around q=${q}: " +
						"indexA=${indexA}, " +
						"indexC=${indexC}")
		}
	}

	val t2 = System.currentTimeMillis()

	if (debug) {
		println("for q: ${t2 - t1} ms")
		println("firstLTE = ${firstLTETime} ms, secondLTE = ${secondLTETime} " +
				"ms")
	}

	return tripletTally
}

/**
 * @return Zero-based index into arrAscending indicating the index at
 * which the next search should begin.
 */
fun searchAhead(arrAscending: ArrayList<Int>,
				target: Int,
				searchStartIndex: Int): Int {
	var result = 0

	if (debug) {
		println("arr size=${arrAscending.size}, target=${target}, " +
				"searchStartIndex=${searchStartIndex}")
	}

	for (index in searchStartIndex..arrAscending.size) {
		result = index

		if (index == arrAscending.size) {
			break
		}

		val elt = arrAscending[index]

		if (debug)
			println("arr[${index}] = ${elt}")

		if (elt > target) {
			break
		}
	}

	if (debug) {
		println("<<<< Leaving indexOfLTE with result=${result}")
	}

	return result
}

// sample0.txt = 8
// sample1.txt = 5
// sample2.txt = 12
// input03.txt = 17747701952583 (6.68 sec)
// input04.txt = 145333908482693 (20.14 sec)
// input07.txt = 33995655366754 (17.35 sec)
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
