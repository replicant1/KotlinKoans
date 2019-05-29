package com.bailey.rod.hackerrank.graphs.roadsandlibs

import org.junit.Test
import java.io.File
import java.util.*
import kotlin.collections.ArrayList

const val debug = true

data class City(val index: Int, val others: ArrayList<City>, var visited: Boolean)

/**
 * @param numCities Number of cities. Cities have a one-based index.
 * @param cost_lib Cost of building a library
 * @param cost_road Cost of reopening an obstructed road
 * @param roads Each element is a 2-element array [a,b], specifying an obstructed road from city a to city b
 */
fun roadsAndLibraries(numCities: Int, cost_lib: Int, cost_road: Int, roads: Array<Array<Int>>): Long {
	// Element [n] contains City with index n. Element [0] goes to waste.
	val cityPool = ArrayList<City>()

	if (debug)
		println("numCities=${numCities}, cost_lib=${cost_lib}, cost_road=${cost_road}, #roads=${roads.size}")

	for (road in roads) {
		val fromCity = getOrCreateCity(road[0], cityPool)
		val toCity = getOrCreateCity(road[1], cityPool)
		fromCity.others.add(toCity)
		toCity.others.add(fromCity)

		if (debug)
			println("Road from city${fromCity.index} to ${toCity.index}")
	}

	var numSubgraphs = 0

	do {
		numSubgraphs++

		if (debug)
			println("Into 'do' loop: numSubgraphs incremented to ${numSubgraphs}")

		var root = firstUnvisitedOrNull(cityPool)

		if (debug)
			println("First unvisited city is ${root}")

		if (root != null) {
			depthFirst(root)
		}
	} while (root != null)

	if (debug)
		println("numSubgraphs=${numSubgraphs}")


	return if (cost_road >= cost_lib) {
		(cost_lib * numCities).toLong()
	} else {
		val costPerSubgraph = cost_lib + (cost_road * (numCities - 1))
		(numSubgraphs * costPerSubgraph).toLong()
	}
}

fun firstUnvisitedOrNull(cityPool: ArrayList<City>): City? {
	for (city in cityPool) {
		if (!city.visited)
			return city
	}
	return null
}

fun depthFirst(root: City) {
	if (debug)
		println("depthFirst on city: ${root}")
	for (other in root.others) {
		depthFirst(other)
	}
	root.visited = true
}

fun getOrCreateCity(cityNum: Int, cityPool: ArrayList<City>): City {
	val city = cityPool.getOrNull(cityNum)
	if (city == null) {
		val newCity = City(cityNum, ArrayList(), false)
		cityPool[cityNum] = newCity
		return newCity
	} else {
		return city
	}
}

class Roads {
	@Test
	fun main() {
		val scan = Scanner(File("/Users/rodbailey/AndroidStudioProjects/KotlinKoans/app/src/test/java/com" +
				"/bailey/rod/hackerrank/graphs/roadsandlibs/input/input00.txt"))
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
