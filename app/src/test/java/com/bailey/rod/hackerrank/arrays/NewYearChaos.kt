package com.bailey.rod.hackerrank.arrays

import org.junit.Test

/**
 * Prints minimum number of bribes required to achieve the
 *
 * @param Queue of people. 1-based original position in the queue
 * before bribes.
 */
fun minimumBribes(q: Array<Int>): Unit {
	var isSorted = false
	var isTooChaotic = false
	var sumBribes = 0

	if (q.size < 2) {
		return
	}

	// Key = Person's original position in queue (1-based)
	// Value = Number of bribes made
	var bribesPerPerson: MutableMap<Int, Int> = HashMap()

	do {
		isSorted = true

		for (i in 0..(q.size - 2)) {
			if (q[i] > q[i + 1]) {
				isSorted = false

				// Swap q[i] and q[i+1]
				val temp = q[i]
				q[i] = q[i + 1]
				q[i + 1] = temp

				// Increment bribe count for person q[i+1]
				val currentBribes: Int = bribesPerPerson.get(q[i + 1]) ?: 0
				when (currentBribes) {
					0, 1 -> {
						bribesPerPerson.put(q[i + 1], currentBribes + 1)
						sumBribes++
					}
					else -> isTooChaotic = true
				}
			}
		} // for i

	} while ((!isSorted) && (!isTooChaotic))

	if (isSorted) {
		println(sumBribes)
	} else {
		// assert: isTooChaotic == true
		println("Too chaotic")
	}
}


class NewYearChaos {

	@Test
	fun main() {
		minimumBribes(arrayOf(2, 5, 1, 3, 4))

	}
}