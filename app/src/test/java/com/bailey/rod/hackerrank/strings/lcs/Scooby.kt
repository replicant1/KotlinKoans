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

	constructor(str: String): this() {
		for ((i, ch) in str.withIndex()) {
			tiles.add(Tile(ch, i))
			builder.append(ch)
		}
	}

	constructor(list: List<Tile>): this() {
		tiles.addAll(list)
		for (tile in list) {
			builder.append(tile.ch)
		}
	}

	constructor(tile: Tile): this() {
		tiles.add(tile)
		builder.append(tile.ch)
	}

	fun add(t: Tile) {
		tiles.add(t)
		builder.append(t.ch)
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

	fun getCharString() : String {
		return builder.toString()
	}

	override fun toString(): String {
		return tiles.toString()
	}
}

class TileListArray() {

	val tileLists = ArrayList<TileList>()

	constructor(str:String): this(){
		for ((i, ch) in str.withIndex()) {
			tileLists.add(TileList(Tile(ch, i)))
		}
	}

	fun add(t: TileList) {
		tileLists.add(t)
	}

	fun contains(str: String): Boolean {
		for (tileList in tileLists) {
			if (tileList.equals(str))  {
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
	val s1TLA = TileListArray(s1)
	val s2TLA = TileListArray(s2)

	// Find tile lists in s1 whose string equiv is also in s2
	val s1ins2TLA = s1TLA.findOneWayIntersection(s2TLA)
	val s2ins1TLA = s2TLA.findOneWayIntersection(s1TLA)

	if (debug) {
		println("s1ins2TLA: ${s1ins2TLA}")
		println("s2ins1TLA: ${s2ins1TLA}")
	}

	// Generate next tile list from intersect_1
	val twochars1 = generateNext(s1ins2TLA)
	val twochars2 = generateNext(s2ins1TLA)

	if (debug) {
		println("twochars1: ${twochars1}")
		println("twochars_2: ${twochars2}")
	}

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
