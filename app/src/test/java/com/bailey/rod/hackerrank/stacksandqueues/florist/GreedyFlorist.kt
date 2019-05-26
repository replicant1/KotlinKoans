package com.bailey.rod.hackerrank.stacksandqueues.florist

import org.junit.Test
import java.io.File
import java.util.*

const val debug = false

// Complete the getMinimumCost function below.
fun getMinimumCost(numFriends: Int, costs: Array<Int>): Int {
	var multiplier = 1
	var costsSorted = ArrayList<Int>()
	var totalCostAllRounds = 0

	costsSorted.addAll(costs)
	costsSorted.sort()
	costsSorted.reverse()

	if (debug) {
		println("Before sorting: ${costs.toList()}")
		println("After sorting: ${costsSorted.toList()}")
	}

	while (!costsSorted.isEmpty()) {
		val numFriendsPurchasing = Math.min(numFriends, costsSorted.size)
		var costThisRound = 0
		for (i in 1..numFriendsPurchasing) {
			costThisRound += costsSorted[0]
			costsSorted.removeAt(0)
		}
		totalCostAllRounds += (costThisRound * multiplier)
		multiplier++
	}

	return totalCostAllRounds
}

class GreedyFlorist {
	@Test
	fun main() {
		val scan = Scanner(File("/Users/rodbailey/AndroidStudioProjects/KotlinKoans/app/src/test/java/com" +
				"/bailey/rod/hackerrank/stacksandqueues/florist/input/input11.txt"))
		val nk = scan.nextLine().split(" ")
		val n = nk[0].trim().toInt()
		val k = nk[1].trim().toInt()
		val c = scan.nextLine().split(" ").map { it.trim().toInt() }.toTypedArray()
		val minimumCost = getMinimumCost(k, c)
		println(minimumCost)
	}
}