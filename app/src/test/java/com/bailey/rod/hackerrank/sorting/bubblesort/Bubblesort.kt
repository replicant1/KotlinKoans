package com.bailey.rod.hackerrank.sorting.bubblesort

import org.junit.Test
import java.io.File
import java.util.*

/**
 * @param a Array to be bubble sorted
 */
fun countSwaps(a: Array<Int>): Unit {
	var numSwaps: Long = 0
	var temp: Int = 0

	if (a.size >= 2) {
		for (i in 0..(a.size - 1)) {
			for (j in 0..(a.size - 2)) {
				if (a[j] > a[j + 1]) {
					// Swap a[j] and a[j+1]
					temp = a[j]
					a[j] = a[j + 1]
					a[j + 1] = temp
					numSwaps++
				}
			}
		}
	}

	println("Array is sorted in ${numSwaps} swaps.")
	println("First Element: ${a[0]}")
	println("Last Element: ${a[a.size - 1]}")
}

class Bubblesort {
	@Test
	fun main() {
//	val scan = Scanner(System.`in`)
		val scan = Scanner(File("/Users/rodbailey/AndroidStudioProjects/KotlinKoans/app/src/test/java/com" +
				"/bailey/rod/hackerrank/sorting/bubblesort/sample2.txt"))
		val n = scan.nextLine().trim().toInt()
		val a = scan.nextLine().split(" ").map { it.trim().toInt() }.toTypedArray()
		countSwaps(a)
	}
}