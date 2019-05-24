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

class TileList() {
	val tiles = ArrayList<Tile>()

	constructor(list: List<Tile>): this() {
		tiles.addAll(list)
	}

	constructor(tile: Tile): this() {
		tiles.add(tile)
	}

	fun add(t: Tile) {
		tiles.add(t)
	}

	override fun toString(): String {
		return tiles.toString()
	}
}

class TileListArray {

	val tileLists = ArrayList<TileList>()

	fun add(t: TileList) {
		tileLists.add(t)
	}

	override fun toString(): String {
		return tileLists.toString()
	}
}

fun commonChild(s1: String, s2: String): Int {
	if (debug)
		println("Into commonChild: s1.len=${s1.length}, s2.len=${s2.length}")

	// Create tile list

	val s1Array = TileListArray()
	val s2Array = TileListArray()

	for ((index, ch) in s1.withIndex())
		s1Array.add(TileList(Tile(ch, index)))

	for ((index, ch) in s2.withIndex())
		s2Array.add(TileList(Tile(ch, index)))

	// Intersection

	val strList1 = tileListToStringList(s1Array)
	val strList2 = tileListToStringList(s2Array)

	val intersect_1 = TileListArray()
	for ((idx1, str1) in strList1.withIndex()) {
		if (strList2.contains(str1)) {
			intersect_1.add(s1Array.tileLists[idx1])
		}
	}

	if (debug)
		println("intersect_1: ${intersect_1}")

	val intersect_2 = TileListArray()
	for ((idx2, str2) in strList2.withIndex()) {
		if (strList1.contains(str2)) {
			intersect_2.add(s2Array.tileLists[idx2])
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
fun generateNext(prev:TileListArray): TileListArray {
	val result = TileListArray()
	for (i in 0..prev.tileLists.size-1) {
		val tile_i = prev.tileLists[i].tiles[0]
		for (j in i+1..prev.tileLists.size - 1) {
			val tile_j = prev.tileLists[j].tiles[0]
			val ilist = TileList()
			ilist.add(tile_i)
			ilist.add(tile_j)
			result.add(ilist)
		}
	}
	return result
}

fun tileListToStringList(arr: TileListArray): List<String> {
	val result = ArrayList<String>()
	for (tileList in arr.tileLists) {
		val builder = StringBuilder()
		for (tile in tileList.tiles) {
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
