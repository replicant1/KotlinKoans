package com.bailey.rod.hackerrank.arrays

import org.junit.Test
import java.util.*

fun minimumSwaps(nums: Array<Int>): Int {
	var swaps: Int = 0
	val indexMap = createIndexMap(nums)

	for ((index, value) in nums.withIndex()) {
		// Is the value in it's correct position, considering that
		// values are 1-based and indexes are 0-based?
		val expectedValue = index + 1
		if (value != expectedValue) {
			swaps++
			val expectedValueIndex = indexMap.getValue(expectedValue)
			// Swap the elements at [index] and [expectedValueIndex]
			swapIndexMapValues(indexMap, value, expectedValue)
			swapArrayElements(nums, index, expectedValueIndex)
		}
	}

	return swaps
}

fun swapIndexMapValues(map : MutableMap<Int, Int>, key1: Int, key2: Int) {
	val value1:Int = map.getValue(key1)
	val value2:Int = map.getValue(key2)
	map.put(key1, value2)
	map.put(key2, value1)
}


fun swapArrayElements(nums:Array<Int>, index1: Int, index2: Int) {
	val value1:Int = nums[index1]
	val value2:Int = nums[index2]
	nums[index1] = value2
	nums[index2] = value1
}

/**
 * Builds the indexes array given the nums array.
 *
 * @param nums contains 1-based consecutive integers in unknown order
 * @return Map from the element at nums[i] to i, for all i.
 */
fun createIndexMap(nums: Array<Int>): MutableMap<Int, Int> {
	val map: MutableMap<Int, Int> = HashMap<Int, Int>()
	for ((index, value) in nums.withIndex()) {
		map.put(value, index)
	}
	return map
}

class MinSwaps2 {
	@Test
	fun main() {
//		val scan = Scanner(System.`in`)
//		val n = scan.nextLine().trim().toInt()
//		val arr = scan.nextLine().split(" ").map { it.trim().toInt() }.toTypedArray()
		val arr = arrayOf(1,3,5,2,4,6,7)
		val res = minimumSwaps(arr)
		println(res)
	}
}
