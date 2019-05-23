package com.bailey.rod.hackerrank.strings.lcs

import org.junit.Test
import java.io.File
import java.util.*

const val debug = true

data class Tile(val ch: Char, val idx: Int) {
	override fun toString(): String {
		return "${ch}@${idx}"
	}
}

fun commonChild(s1: String, s2: String): Int {
	if (debug)
		println("Into commonChild: s1.len=${s1.length}, s2.len=${s2.length}")

	// Create tile list

	val s1Array = LinkedList<List<Tile>>()
	val s2Array = LinkedList<List<Tile>>()

	for ((index, ch) in s1.withIndex())
		s1Array.add(listOf(Tile(ch, index)))

	for ((index, ch) in s2.withIndex())
		s2Array.add(listOf(Tile(ch, index)))

	// Intersection

	val strList1 = tileListToStringList(s1Array)
	val strList2 = tileListToStringList(s2Array)

	val intersect_1 = LinkedList<List<Tile>>()
	for ((idx1, str1) in strList1.withIndex()) {
		if (strList2.contains(str1)) {
			intersect_1.add(s1Array[idx1])
		}
	}

	if (debug)
		println("intersect_1: ${intersect_1}")

	val intersect_2 = LinkedList<List<Tile>>()
	for ((idx2, str2) in strList2.withIndex()) {
		if (strList1.contains(str2)) {
			intersect_2.add(s2Array[idx2])
		}
	}

	if (debug)
		println("intersect_2: ${intersect_2}")

	// Generate next tile list from intersect_1
	val twochars_1 = generateNext(intersect_1)

	if (debug)
		println("twochars_1: ${twochars_1}")

	val twochars_2 = generateNext(intersect_2)

	if (debug)
		println("twochars_2: ${twochars_2}")

	return 0
}

// All pairwise combinations
fun generateNext(prev:LinkedList<List<Tile>>): List<List<Tile>> {
	val result = LinkedList<List<Tile>>()
	for (i in 0..prev.size-1) {

		val tile_i = prev[i][0]
		for (j in i+1..prev.size - 1) {
			val tile_j = prev[j][0]
			val ilist = LinkedList<Tile>()
			ilist.add(tile_i)
			ilist.add(tile_j)
			result.add(ilist)
		}
	}
	return result
}

fun tileListToStringList(tiles: List<List<Tile>>): ArrayList<String> {
	val result = ArrayList<String>()

	for (tileList in tiles) {
		val builder = StringBuilder()
		for (tile in tileList) {
			builder.append(tile.ch)
		}
		result.add(builder.toString())
	}
	return result
}


class Scooby {
	@Test
	fun main() {
		val scan = Scanner(File("/Users/rodbailey/AndroidStudioProjects/KotlinKoans/app/src/test/java/com" +
				"/bailey/rod/hackerrank/strings/lcs/input/input14.txt"))
		val s1 = scan.nextLine()
		val s2 = scan.nextLine()
		val result = commonChild(s1, s2)
		println(result)
	}
}
