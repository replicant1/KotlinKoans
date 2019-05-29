package com.bailey.rod.hackerrank.graphs.roadsandlibs

import org.junit.Test
import java.io.File
import java.util.*
import kotlin.collections.ArrayList

const val debug = false

data class City(val index: Int, val others: ArrayList<City>) {
	override fun toString(): String {
		return "City ${index}"
	}
}

/**
 * @param numCities Number of cities. Cities have a one-based index.
 * @param cost_lib Cost of building a library
 * @param cost_road Cost of reopening an obstructed road
 * @param roads Each element is a 2-element array [a,b], specifying an obstructed road from city a to city b
 */
fun roadsAndLibraries(numCities: Int, cost_lib: Int, cost_road: Int, roads: Array<Array<Int>>): Long {
	// Element [n] contains City with index n. Element [0] goes to waste.
	val cities:Array<City?> = Array<City?>(numCities + 1, {null})
	val unvisitedCityIndexes = HashSet<Int>()

	if (debug)
		println("numCities=$numCities, cost_lib=${cost_lib}, cost_road=${cost_road}, #roads=${roads.size}")

	// Special case - build a library for each city is cheapest, as roads are so expensive
	if (cost_road >= cost_lib) {
		return cost_lib.toLong() * numCities.toLong()
	}

	val t0 = System.currentTimeMillis()
	for (i in 1..numCities) {
//		if (debug)
//			println("Creating city $i")
		cities[i] = City(i, ArrayList())
		unvisitedCityIndexes.add(i)
	}
	val t1 = System.currentTimeMillis()
//	if (debug)
//		println("City creation = ${t1 - t0} ms")

	for (road in roads) {
		val fromCity = cities[road[0]]
		val toCity = cities[road[1]]
		if ((fromCity != null) && (toCity != null)) {
			fromCity.others.add(toCity)
			toCity.others.add(fromCity)

//			if (debug)
//				println("Road from city ${fromCity.index} to ${toCity.index}")
		}
	}
	val t2 = System.currentTimeMillis()
	if (debug)
		println("City creation = ${t2 - t1} ms")

	var numSubgraphs = 0
	var totalCost = 0.toLong()
	var firstUnvisitedTally:Long = 0
	var traverseTally:Long = 0

	do {
		val t10 = System.currentTimeMillis()
		val iter = unvisitedCityIndexes.iterator()

		var rootIndex = iter.next()
		val root = cities[rootIndex]
		iter.remove()
		val t11 = System.currentTimeMillis()
		firstUnvisitedTally += (t11 - t10)


		if (debug) {
			println("Into 'do' loop: numSubgraphs = ${numSubgraphs}")
			println("First unvisited city is ${root}")
		}

		if (root != null) {
			val t20 = System.currentTimeMillis()
			numSubgraphs++
			var numCitiesInSubgraph = traverse(root, unvisitedCityIndexes)
			val t21 = System.currentTimeMillis()
			traverseTally += (t21 - t20)
			val cost = cost_lib + (cost_road * (numCitiesInSubgraph - 1))

//			if (debug) {
//				println("Num cities in subgraph = ${numCitiesInSubgraph}")
//				println("Cost for this subgraph = ${cost}")
//			}

			totalCost += cost
		}
	} while ((root != null) && unvisitedCityIndexes.isNotEmpty())

	if (debug) {
		println("firstUnvisitedTally = $firstUnvisitedTally")
		println("traverseTally = $traverseTally")
	}

	return totalCost
}

//fun firstUnvisitedOrNull(cities: Array<City>, unvisitedCityIndexes: HashSet<Int>): City? {
//	for (city in cities) {
//		if ((city != null) && !city.visited)
//			return city
//	}
//	return null
//}

fun traverse(root: City, unvisitedCityIndexes:HashSet<Int>): Int {
//	if (debug)
//		println("--- TRAVERSAL ON CITY ${root.index} BEGINS ---")
//	root.visited = true
	unvisitedCityIndexes.remove(root.index)
	var citiesVisited = 1
	for (other in root.others) {
		if (unvisitedCityIndexes.contains(other.index)) {
			citiesVisited += traverse(other, unvisitedCityIndexes)
		}
	}
	return citiesVisited
}

class Roads {
	@Test
	fun main() {
		val scan = Scanner(File("/Users/rodbailey/AndroidStudioProjects/KotlinKoans/app/src/test/java/com" +
				"/bailey/rod/hackerrank/graphs/roadsandlibs/input/input11.txt"))
		val q = scan.nextLine().trim().toInt()
		for (qItr in 1..q) {
			val s0 = System.currentTimeMillis()
			val nmC_libC_road = scan.nextLine().split(" ")
			val n = nmC_libC_road[0].trim().toInt()
			val m = nmC_libC_road[1].trim().toInt()
			val c_lib = nmC_libC_road[2].trim().toInt()
			val c_road = nmC_libC_road[3].trim().toInt()
			val cities = Array<Array<Int>>(m, { Array<Int>(2, { 0 }) })
			for (i in 0 until m) {
				cities[i] = scan.nextLine().split(" ").map { it.trim().toInt() }.toTypedArray()
			}
			val s1 = System.currentTimeMillis()
			if (debug)
				println("Input time is ${s1 - s0} ms")
			val result = roadsAndLibraries(n, c_lib, c_road, cities)
			val s2 = System.currentTimeMillis()
			if (debug)
				println("Figuring time is ${s2 - s1} ms")
			println(result)
		}
	}
}
