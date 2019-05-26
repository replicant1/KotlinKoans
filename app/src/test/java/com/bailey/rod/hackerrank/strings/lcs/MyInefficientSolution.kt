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
	private var charString: String = ""

	constructor(tile: Tile) : this() {
		tiles.add(tile)
		charString += tile.ch
	}

	fun add(other: TileList) {
		tiles.addAll(other.tiles)
		charString += other.charString
	}

	fun getCharString(): String {
		return charString
	}

	override fun toString(): String {
		return tiles.toString()
	}
}

class TileListArray() {

	val tileLists = ArrayList<TileList>()
	val tileListCharStrings = HashSet<String>()

	constructor(str: String) : this() {
		for ((i, ch) in str.withIndex()) {
			val tl = TileList(Tile(ch, i))
			tileLists.add(tl)
			tileListCharStrings.add(tl.getCharString())
		}
	}

	fun add(t: TileList) {
		tileLists.add(t)
		tileListCharStrings.add(t.getCharString())
	}

	fun contains(str: String): Boolean {
		return tileListCharStrings.contains(str)
	}

	fun findOneWayIntersection(otherTLA: TileListArray): TileListArray {
		val result = TileListArray()
		for (tileList in tileLists) {
			if (otherTLA.contains(tileList.getCharString())) {
				result.add(tileList)
			}
		}
		return result
	}

	override fun toString(): String {
		return tileLists.toString()
	}
}

fun commonChild(s1: String, s2: String): Int {
	if (debug)
		println("Into commonChild: s1.len=${s1.length}, s2.len=${s2.length}")

	// Create two arrays of tile lists. Each tile list contains a single tile,
	// corresponding to a single char in the string
	val onecharTLA1 = TileListArray(s1)
	val onecharTLA2 = TileListArray(s2)

	// Find tile lists in TLA1 whose string equiv is in TLA2
	val onecharTLA1Inter = onecharTLA1.findOneWayIntersection(onecharTLA2)
	val onecharTLA2Inter = onecharTLA2.findOneWayIntersection(onecharTLA1)

	if (debug) {
		println("onecharTLA1Inter: ${onecharTLA1Inter.tileLists.size}")
		println("onecharTLA2Inter: ${onecharTLA2Inter.tileLists.size}")
	}

	var n_1charTLA1Inter = onecharTLA1Inter
	var n_1charTLA2Inter = onecharTLA2Inter
	var n = 1

	while (!n_1charTLA1Inter.tileLists.isEmpty() && !n_1charTLA2Inter.tileLists.isEmpty()) {
		// Generate possible n-char substrings for each string
		val t0 = System.currentTimeMillis()
		val n_charTLA1 = crossProduct(n_1charTLA1Inter, onecharTLA1Inter)
		val t1 = System.currentTimeMillis()
		val n_charTLA2 = crossProduct(n_1charTLA2Inter, onecharTLA2Inter)

		if (debug) {
			println("n=${n}, TLA1=${n_charTLA1.tileLists.size}")
			println("n=${n}, TLA2=${n_charTLA2.tileLists.size}")
			// xproduct takes 4 times longer than intersection
			println("crossProduct time = ${t1 - t0}ms")
		}

		// Find intersection of n-char substrings
		val t2 = System.currentTimeMillis()
		val n_charTLA1Inter = n_charTLA1.findOneWayIntersection(n_charTLA2)
		val t3 = System.currentTimeMillis()
		val n_charTLA2Inter = n_charTLA2.findOneWayIntersection(n_charTLA1)

		if (debug) {
			println("n=${n}, TLA1Inter=${n_charTLA1Inter.tileLists.size}")
			println("n=${n}, TLA2Inter=${n_charTLA2Inter.tileLists.size}")
			println("onewayintersection time = ${t3 - t2}ms")
		}

		n++

		n_1charTLA1Inter = n_charTLA1Inter
		n_1charTLA2Inter = n_charTLA2Inter
	}

	return n - 1
}

/**
 * @param prefixTLA All prefixes. Each tilelist contains at least one tile.
 * @param suffixTLA All tilelists contain exactly one tile from the same tile set
 * that formed the prefixes.
 * @return A TLA where each TLA is formed by appending a suffix from [suffixTLA] to a
 * prefix from [prefixTLA]. THe last tile in a prefix must precede the first tile in a suffix
 * so as to preserve over all left->right ordering.
 */
fun crossProduct(prefixTLA: TileListArray, suffixTLA: TileListArray): TileListArray {
	val result = TileListArray()

	for (prefix in prefixTLA.tileLists) {
		val lastTileInPrefix = prefix.tiles.last()
		val suffixStartIdx = indexOf(lastTileInPrefix, suffixTLA) + 1

		if (suffixStartIdx <= (suffixTLA.tileLists.size - 1)) {
			for (s in suffixStartIdx..suffixTLA.tileLists.size - 1) {
				val suffix = suffixTLA.tileLists[s]
				result.add(concat(prefix, suffix))
			}
		}
	}
	return result
}

/**
 * @param tileListArray Assumed all tile lists have a single tile
 */
fun indexOf(tile: Tile, tileListArray: TileListArray): Int {
	var result = -1
	for ((index, tileList) in tileListArray.tileLists.withIndex()) {
		if (tileList.tiles[0].equals(tile)) {
			result = index
			break
		}
	}
	return result
}

fun concat(a: TileList, b: TileList): TileList {
	val result = TileList()
	result.add(a)
	result.add(b)
	return result
}

// input01.txt = 15 (13 seconds)
// input02.txt = 27 (out of memory)
// input14.txt = 3 (62ms)
class MyInefficientSolution {
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
