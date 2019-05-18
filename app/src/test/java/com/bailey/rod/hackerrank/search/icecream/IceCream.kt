package com.bailey.rod.hackerrank.search.icecream

import org.junit.Test
import java.io.File
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

const val debug = true

fun whatFlavors(costs: Array<Int>, money: Int): Unit {
	val costToIndexes = HashMap<Int, MutableList<Int>>()

	// Create map from cost to list of indexes
	for ((index, cost) in costs.withIndex()) {
		addCostToMap(costToIndexes, cost, index)
	}

	if (debug)
		println("After seeding, costToIndexes=${costToIndexes}")

	var cost1:Int = 0
	var cost2:Int = 0
	var indexCost1: Int = 0
	var indexCost2: Int = 0

	for (cost in costs.sortedArray()) {
		if ((cost < money) && (costToIndexes.containsKey(cost))) {
			cost1 = cost
			indexCost1 = removeCostFromMap(costToIndexes, cost1)

			if (debug)
				println("cost1=${cost1}, indexCost1=${indexCost1}")

			val remainingMoney = money - cost
			if (costToIndexes.containsKey(remainingMoney)) {
				cost2 = remainingMoney
				indexCost2 = removeCostFromMap(costToIndexes, cost2)

				if (debug)
					println("cost2=${cost2}, indexCost2=${indexCost2}")

				break
			} else {
				addCostToMap(costToIndexes, cost1, indexCost1)
			}
		}
	}

	indexCost1++
	indexCost2++

	if (indexCost1 <= indexCost2) {
		println("${indexCost1} ${indexCost2}")
	} else {
		println("${indexCost2} ${indexCost1}")
	}
}

fun addCostToMap(costToIndexes: HashMap<Int, MutableList<Int>>,
				 cost: Int, index: Int) {
	if (!costToIndexes.containsKey(cost)) {
		costToIndexes.put(cost, mutableListOf(index))
	} else {
		val indexes = costToIndexes.get(cost)
		indexes!!.add(index)
		costToIndexes.put(cost, indexes)
	}
}

/**
 * @return Index of removed cost in original array
 */
fun removeCostFromMap(costToIndexes: HashMap<Int, MutableList<Int>>,
					  cost: Int): Int {
	var removed:Int = -1
	if (costToIndexes.containsKey(cost)) {
		val indexes: MutableList<Int> = costToIndexes.get(cost) ?: ArrayList()
		removed = indexes.removeAt(0)
		if (indexes.isEmpty()) {
			costToIndexes.remove(cost)
		}
	}
	return removed
}

// sample0.txt =
// 1 4
// 1 2
class IceCream {
	@Test
	fun main() {
		val scan = Scanner(File("/Users/rodbailey/AndroidStudioProjects/KotlinKoans/app/src/test/java/com" +
				"/bailey/rod/hackerrank/search/icecream/sample0.txt"))
		val t = scan.nextLine().trim().toInt()
		for (tItr in 1..t) {
			val money = scan.nextLine().trim().toInt()
			val n = scan.nextLine().trim().toInt()
			val cost = scan.nextLine().split(" ").map { it.trim().toInt() }.toTypedArray()
			whatFlavors(cost, money)
		}
	}
}
