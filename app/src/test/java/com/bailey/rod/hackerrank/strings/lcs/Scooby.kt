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
	private val builder = StringBuilder()

	constructor(str: String) : this() {
		for ((i, ch) in str.withIndex()) {
			tiles.add(Tile(ch, i))
			builder.append(ch)
		}
	}

	constructor(list: List<Tile>) : this() {
		tiles.addAll(list)
		for (tile in list) {
			builder.append(tile.ch)
		}
	}

	constructor(tile: Tile) : this() {
		tiles.add(tile)
		builder.append(tile.ch)
	}

	fun add(t: Tile) {
		tiles.add(t)
		builder.append(t.ch)
	}

	fun add(other: TileList) {
		tiles.addAll(other.tiles)
		builder.append(other.builder.toString())
	}

	fun contains(ch: Char): Boolean {
		for (tile in tiles) {
			if (tile.ch == ch) {
				return true
			}
		}
		return false
	}

	fun equals(str: String): Boolean {
		return builder.toString().equals(str)
	}

	fun getCharString(): String {
		return builder.toString()
	}

	override fun toString(): String {
		return tiles.toString()
	}
}

class TileListArray() {

	val tileLists = ArrayList<TileList>()

	constructor(str: String) : this() {
		for ((i, ch) in str.withIndex()) {
			tileLists.add(TileList(Tile(ch, i)))
		}
	}

	fun add(t: TileList) {
		tileLists.add(t)
	}

	fun add(other: TileListArray) {
		tileLists.addAll(other.tileLists)
	}

	fun contains(str: String): Boolean {
		for (tileList in tileLists) {
			if (tileList.equals(str)) {
				return true
			}
		}
		return false
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
		println("onecharTLA1Inter: ${onecharTLA1Inter}")
		println("onecharTLA2Inter: ${onecharTLA2Inter}")
	}

	// Generate TLAs for two character generation
	val twocharTLA1 = crossProduct(onecharTLA1Inter, onecharTLA1Inter)
	val twocharTLA2 = crossProduct(onecharTLA2Inter, onecharTLA2Inter)

	if (debug) {
		println("twocharsTLA1: ${twocharTLA1}")
		println("twocharsTLA2: ${twocharTLA2}")
	}

	// Fine tile lists in TLA1 whose string equiv is in TLA2
	val twocharTLA1Inter = twocharTLA1.findOneWayIntersection(twocharTLA2)
	val twocharTLA2Inter = twocharTLA2.findOneWayIntersection(twocharTLA1)

	if (debug) {
		println("twocharTLA1Inter: ${twocharTLA1Inter}")
		println("twocharTLA2Inter: ${twocharTLA2Inter}")
	}

	// Generate TLAs for three character generation
	val threecharTLA1 = crossProduct(twocharTLA1Inter, onecharTLA1Inter)
	val threecharTLA2 = crossProduct(twocharTLA2Inter, onecharTLA2Inter)

	if (debug) {
		println("threecharTLA1: ${threecharTLA1}")
		println("threecharTLA2: ${threecharTLA2}")
	}

	// Find tile lists in TLA1 whose string equiv is in TLA2
	val threecharTLA1Inter = threecharTLA1.findOneWayIntersection(threecharTLA2)
	val threecharTLA2Inter = threecharTLA2.findOneWayIntersection(threecharTLA1)

	if (debug) {
		println("threecharTLA1Inter: ${threecharTLA1Inter}")
		println("threecharTLA2Inter: ${threecharTLA2Inter}")
	}

	// Generate TLAs for four character generation
	val fourcharTLA1 = crossProduct(threecharTLA1Inter, onecharTLA1Inter)
	val fourcharTLA2 = crossProduct(threecharTLA2Inter, onecharTLA2Inter)

	if (debug) {
		println("fourcharTLA1: ${fourcharTLA1}")
		println("fourcharTLA2: ${fourcharTLA2}")
	}

	// Find tile lists in TLA1 whose strinfg equiv is in TLA2
	val fourcharTLA1Inter = fourcharTLA1.findOneWayIntersection(fourcharTLA2)
	val fourcharTLA2Inter = fourcharTLA2.findOneWayIntersection(fourcharTLA1)

	if (debug) {
		println("fourcharTLA1Inter: ${fourcharTLA1Inter}")
		println("fourcharTLA2Inter: ${fourcharTLA2Inter}")
	}


	return 0
}

// All pairwise combinations
fun crossProduct(prefixTLA: TileListArray, suffixTLA: TileListArray): TileListArray {
	if (debug)
		println("crossProduct: ${prefixTLA} and ${suffixTLA}")

	val result = TileListArray()

	for (p in 0..prefixTLA.tileLists.size - 1) {
		val prefix = prefixTLA.tileLists[p]
		val lastTileInPrefix = prefix.tiles.last()
		val suffixStartIdx = indexOf(lastTileInPrefix, suffixTLA) + 1

		if (suffixStartIdx <= (suffixTLA.tileLists.size - 1)) {
			for (s in suffixStartIdx..suffixTLA.tileLists.size - 1) {
				val suffix = suffixTLA.tileLists[s]
				result.add(concat(prefix, suffix))
			}
		}

//		val tile_i = prefixTLA.tileLists[i].tiles.indexOf(lastTileInPrefix)

//		for (j in i + 1..prefixTLA.tileLists.size - 1) {
//			val tile_j = prefixTLA.tileLists[j].tiles[0]
//			val ilist = TileList()
//			ilist.add(tile_i)
//			ilist.add(tile_j)
//			result.add(ilist)
//		}
	}
	return result
}

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

fun concat(a:TileList, b: TileList): TileList {
	val result = TileList()
	result.add(a)
	result.add(b)
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
