package com.bailey.rod.hackerrank.graphs.roadsandlibs

import org.junit.Test
import java.io.BufferedReader
import java.io.FileReader

const val debug = false

data class City(val index: Int, val others: ArrayList<City>, var visited: Boolean = false) {
	override fun toString(): String {
		val sb = StringBuilder("CITY $index (")
		for (i in 0..others.size - 1) {
			val other = others[i]
			sb.append("CITY ${other.index}")
			if (i != (others.size - 1))
				sb.append(", ")
		}
		sb.append(")")
		return sb.toString()
	}
}

val NULL_CITY = City(-1, ArrayList(), false)

/**
 * @param numCities Number of cities. Cities have a one-based index.
 * @param cost_lib Cost of building a library
 * @param cost_road Cost of reopening an obstructed road
 * @param roads Each element is a 2-element array [a,b], specifying an obstructed road from city a to city b
 */
fun roadsAndLibraries(numCities: Int, cost_lib: Int, cost_road: Int, roads: Array<Array<Int>>): Long {
	if (debug)
		println("numCities=$numCities, cost_lib=${cost_lib}, cost_road=${cost_road}, #roads=${roads.size}")

	// Special case - build a library for each city is cheapest, as roads are so expensive
	if (cost_road >= cost_lib) {
		if (debug)
			println("**** Special case of roads > libs cost ****")
		return cost_lib.toLong() * numCities.toLong()
	}

	var totalCost = 0.toLong()
	var unvisitedCitiesTallyMs: Long = 0
	var traverseTallyMs: Long = 0
	val unvisitedCityNums = LinkedHashSet<Int>()
	// Element [n] contains City with index n. Element [0] goes to waste and is NULL_CITY
	val cities: Array<City> = Array(numCities + 1) { NULL_CITY }

	val t0 = System.currentTimeMillis()
	for (i in 1..numCities) {
		cities[i] = City(i, ArrayList(), false)
		unvisitedCityNums.add(i)
	}
	val t1 = System.currentTimeMillis()

	if (debug)
		println("City creation in memory takes ${t1 - t0} ms")

	for (road in roads) {
		val fromCity = cities[road[0]]
		val toCity = cities[road[1]]
		fromCity.others.add(toCity)
		toCity.others.add(fromCity)
	}
	val t2 = System.currentTimeMillis()
	if (debug) {
		println("Road creation = ${t2 - t1} ms")
	}

	do {
		val t10 = System.currentTimeMillis()
		val rootCityNum = unvisitedCityNums.firstOrNull()
		val t11 = System.currentTimeMillis()
		unvisitedCitiesTallyMs += (t11 - t10)

		if (debug) {
			println("First unvisited city is ${rootCityNum}")
		}

		if (rootCityNum != null) {
			val t20 = System.currentTimeMillis()
			val root = cities[rootCityNum]
			var numCitiesInSubgraph = traverse(root, unvisitedCityNums)

			if (debug)
				println("numCitiesInSubgraph=${numCitiesInSubgraph}")

			val t21 = System.currentTimeMillis()

			traverseTallyMs += (t21 - t20)
			val cost = cost_lib + (cost_road * (numCitiesInSubgraph - 1))

			totalCost += cost
		}
	} while (rootCityNum != null)

	if (debug) {
		println("unvisitedCitiesTally = $unvisitedCitiesTallyMs ms")
		println("traverseTally = $traverseTallyMs ms")
	}

	return totalCost
}

fun traverse(root: City, unvisitedCityNums: HashSet<Int>): Int {
	// This is the only placd visited = true. Remove from 'unvisited' set/list/array here.
	root.visited = true
	unvisitedCityNums.remove(root.index)
	var citiesVisited = 1
	for (other in root.others) {
		if (!other.visited) {
			citiesVisited += traverse(other, unvisitedCityNums)
		}
	}
	return citiesVisited
}

// val bufReader = BufferedReader(InputStreamReader(System.`in`))
// Timeouts: 4,5,6,8,9,10
// input04 = 39.832 secs
class Roads {
	@Test
	fun main() {
		// Much time is being consumed in input. Use same solu'n as prev (buffers) to speed this up.
		val bufReader = BufferedReader(FileReader("/Users/rodbailey/AndroidStudioProjects/KotlinKoans/app/src/test" +
				"/java/com/bailey/rod/hackerrank/graphs/roadsandlibs/input/input12.txt"))
		val q = bufReader.readLine().trim().toInt()
		for (qItr in 1..q) {
			val s0 = System.currentTimeMillis()
			val nmC_libC_road = bufReader.readLine().split(" ")
			val n = nmC_libC_road[0].trim().toInt()
			val m = nmC_libC_road[1].trim().toInt()
			val c_lib = nmC_libC_road[2].trim().toInt()
			val c_road = nmC_libC_road[3].trim().toInt()
			val cities = Array<Array<Int>>(m, { Array<Int>(2, { 0 }) })
			for (i in 0 until m) {
				cities[i] = bufReader.readLine().split(" ").map { it.trim().toInt() }.toTypedArray()
			}
			val s1 = System.currentTimeMillis()
			if (debug)
				println("Input time is ${s1 - s0} ms")
			val result = roadsAndLibraries(n, c_lib, c_road, cities)
			val s2 = System.currentTimeMillis()
			if (debug)
				println("Total calculation time is ${s2 - s1} ms")
			println(result)
		}
	}
}
