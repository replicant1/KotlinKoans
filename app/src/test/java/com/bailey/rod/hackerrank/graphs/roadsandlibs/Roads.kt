package com.bailey.rod.hackerrank.graphs.roadsandlibs

import org.junit.Test
import java.io.File
import java.util.*
import kotlin.collections.ArrayList

const val debug = false

data class City(val index: Int, val others: ArrayList<City>, var visited: Boolean) {
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
	val cityPool = HashMap<Int, City>()

	if (debug)
		println("numCities=${numCities}, cost_lib=${cost_lib}, cost_road=${cost_road}, #roads=${roads.size}")

	if (cost_road >= cost_lib) {
		return (cost_lib * numCities).toLong()
	}

	for (i in 1..numCities) {
		if (debug)
			println("Creating city #${i}")
		cityPool[i] = City(i, ArrayList(), false)
	}

	for (road in roads) {
		val fromCity = cityPool[road[0]]
		val toCity = cityPool[road[1]]
		if ((fromCity != null) && (toCity != null)) {
			fromCity.others.add(toCity)
			toCity.others.add(fromCity)

			if (debug)
				println("Road from city ${fromCity.index} to ${toCity.index}")
		}
	}

	var numSubgraphs = 0
	var totalCost = 0.toLong()

	do {
		var root = firstUnvisitedOrNull(cityPool)

		if (debug) {
			println("Into 'do' loop: numSubgraphs = ${numSubgraphs}")
			println("First unvisited city is ${root}")
		}

		if (root != null) {
			numSubgraphs++
			var numCitiesInSubgraph = traverse(root)
			val cost = cost_lib + (cost_road * (numCitiesInSubgraph - 1))

			if (debug) {
				println("Num cities in subgraph = ${numCitiesInSubgraph}")
				println("Cost for this subgraph = ${cost}")
			}

			totalCost += cost
		}
	} while (root != null)

	return totalCost
}

fun firstUnvisitedOrNull(cityPool: HashMap<Int, City>): City? {
	for (cityNum in cityPool.keys) {
		val city = cityPool[cityNum]
		if ((city != null) && !city.visited)
			return city
	}
	return null
}

fun traverse(root: City): Int {
	if (debug)
		println("--- TRAVERSAL ON CITY ${root.index} BEGINS ---")
	root.visited = true
	var citiesVisited = 1
	for (other in root.others) {
		if (!other.visited) {
			citiesVisited += traverse(other)
		}
	}
	return citiesVisited
}

class Roads {
	@Test
	fun main() {
		val scan = Scanner(File("/Users/rodbailey/AndroidStudioProjects/KotlinKoans/app/src/test/java/com" +
				"/bailey/rod/hackerrank/graphs/roadsandlibs/input/input12.txt"))
		val q = scan.nextLine().trim().toInt()
		for (qItr in 1..q) {
			val nmC_libC_road = scan.nextLine().split(" ")
			val n = nmC_libC_road[0].trim().toInt()
			val m = nmC_libC_road[1].trim().toInt()
			val c_lib = nmC_libC_road[2].trim().toInt()
			val c_road = nmC_libC_road[3].trim().toInt()
			val cities = Array<Array<Int>>(m, { Array<Int>(2, { 0 }) })
			for (i in 0 until m) {
				cities[i] = scan.nextLine().split(" ").map { it.trim().toInt() }.toTypedArray()
			}
			val result = roadsAndLibraries(n, c_lib, c_road, cities)
			println(result)
		}
	}
}
