package com.bailey.rod.hackerrank.sorting.toys

import org.junit.Test
import java.io.File
import java.util.*

/**
 * @param prices The prices of individual toys in unspecified order, in $
 * @param budget Total budget in $
 * @return The maximum number of toys that can be bought with $budget
 */
fun maximumToys(prices: Array<Int>, budget: Int): Int {
	var moneySpent = 0
	var numToysPurchased = 0

	prices.sort()

	for (price in prices) {
		if ((moneySpent + price) > budget) {
			break
		}
		moneySpent += price
		numToysPurchased++
	}

	return numToysPurchased
}

class Toys {
	@Test
	fun main() {
//	val scan = Scanner(System.`in`)
		val scan = Scanner(File(
				"/Users/rodbailey/AndroidStudioProjects/KotlinKoans/" +
						"app/src/test/java/com" +
						"/bailey/rod/hackerrank/sorting/toys/input01.txt"))
		val nk = scan.nextLine().split(" ")
		val n = nk[0].trim().toInt()
		val k = nk[1].trim().toInt()
		val prices = scan.nextLine().split(" ").map { it.trim().toInt() }.toTypedArray()
		val result = maximumToys(prices, k)
		println(result)
	}
}

