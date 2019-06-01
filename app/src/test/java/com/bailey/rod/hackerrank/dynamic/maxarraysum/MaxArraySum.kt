package com.bailey.rod.hackerrank.dynamic.maxarraysum

import java.io.File
import java.util.*

const val debug = false

fun maxSubsetSum(arr: Array<Int>): Int {
	if (debug)
		println("maxSubsetSum invoked with arr.size = " + arr.size)

	val max:Array<Int> = Array(arr.size, {0})
	var maxSoFar = Integer.MIN_VALUE
	max[0] = arr[0]
	max[1] = Math.max(arr[0], arr[1])

	if (debug)
		println("max[0]=${max[0]}, max[1]=${max[1]}")

	for (i in 2..arr.size - 1) {
		if (debug)
			println("index=$i arr[i]=${arr[i]}")

		val option1 = max[i - 1]
		val option2 = max[i - 2]
		val option3 = max[i - 2] + arr[i]

		maxSoFar = Math.max(Math.max(option1, option2), option3)
		max[i] = maxSoFar

		if (debug)
			println("max so far=" + maxSoFar)
	}

	return maxSoFar
}

fun main(args: Array<String>) {
	val scan = Scanner(File("/Users/rodbailey/AndroidStudioProjects/KotlinKoans/app/src/test/java/com" +
			"/bailey/rod/hackerrank/dynamic/maxarraysum/input/input32.txt"))
	val n = scan.nextLine().trim().toInt()
	val arr = scan.nextLine().split(" ").map{ it.trim().toInt() }.toTypedArray()
	val res = maxSubsetSum(arr)
	println(res)
}
