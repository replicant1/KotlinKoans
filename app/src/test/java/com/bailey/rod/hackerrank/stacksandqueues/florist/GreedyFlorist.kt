package com.bailey.rod.hackerrank.stacksandqueues.florist

import java.io.File
import java.util.*

// Complete the getMinimumCost function below.
fun getMinimumCost(k: Int, c: Array<Int>): Int {

	return 0
}

fun main(args: Array<String>) {
	val scan = Scanner(File("/Users/rodbailey/AndroidStudioProjects/KotlinKoans/app/src/test/java/com" +
			"/bailey/rod/hackerrank/stacksandqueues/florist/input/input00.txt"))
	val nk = scan.nextLine().split(" ")
	val n = nk[0].trim().toInt()
	val k = nk[1].trim().toInt()
	val c = scan.nextLine().split(" ").map { it.trim().toInt() }.toTypedArray()
	val minimumCost = getMinimumCost(k, c)
	println(minimumCost)
}