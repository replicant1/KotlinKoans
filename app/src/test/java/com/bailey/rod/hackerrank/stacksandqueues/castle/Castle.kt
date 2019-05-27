package com.bailey.rod.hackerrank.stacksandqueues.castle

import org.junit.Test
import java.io.File
import java.util.*

const val debug = false

data class Square(val row: Int, val col: Int, val depth:Int) {
	override fun toString(): String {
		return "(${row},${col})@${depth}"
	}
}

fun minimumMoves(grid: Array<String>, startRow: Int, startCol: Int, goalRow: Int, goalCol: Int): Int {
	if (debug) {
		println("grid  ${grid.size} rows x ${grid[0].length} cols")
		println("start (${startRow},${startCol})")
		println("goal  (${goalRow},${goalCol})")
	}

	// Element = square index = (row * col) + col, where row and col and zero-based
	val queue = LinkedList<Square>()
	val start = Square(startRow, startCol, 0)
	var depth = 0

	queue.add(start)

	while (!queue.isEmpty()) {
		val head = queue.get(0)

		if (debug)
			println("--- Head: ${head} ---")

		queue.removeAt(0)

		if ((head.row == goalRow) && (head.col == goalCol)) {
			if (debug)
				println("FOUND GOAL")
			depth = head.depth
			break
		}

		val childDepth = head.depth + 1
		queue.addAll(movesNorth(head, grid, childDepth)) // North
		queue.addAll(movesSouth(head, grid, childDepth)) // South
		queue.addAll(movesEast(head, grid, childDepth)) // East
		queue.addAll(movesWest(head, grid, childDepth)) // West

	}
	return depth
}

fun movesEast(from: Square, grid: Array<String>, depth: Int): List<Square> {
	val result = LinkedList<Square>()
	var toCol = from.col + 1
	while (toCol < grid[0].length) {
		if (grid[from.row][toCol] == '.')
			result.add(Square(from.row, toCol, depth))
		else
			break
		toCol++
	}
	if (debug)
		println("movesEast from ${from} = ${result.toList()}")
	return result
}

fun movesWest(from: Square, grid: Array<String>, depth: Int): List<Square> {
	val result = LinkedList<Square>()
	var toCol = from.col - 1
	while (toCol >= 0) {
		if (grid[from.row][toCol] == '.')
			result.add(Square(from.row, toCol, depth))
		else
			break
		toCol--
	}
	if (debug)
		println("movesWest from ${from} = ${result.toList()}")
	return result
}

fun movesNorth(from: Square, grid: Array<String>, depth: Int): List<Square> {
	val result = LinkedList<Square>()
	var toRow = from.row - 1
	while (toRow >= 0) {
		if (grid[toRow][from.col] == '.')
			result.add(Square(toRow, from.col, depth))
		else
			break
		toRow--
	}
	if (debug)
		println("movesNorth from ${from} = ${result.toList()}")
	return result
}

fun movesSouth(from: Square, grid: Array<String>, depth: Int): List<Square> {
	val result = LinkedList<Square>()
	var toRow = from.row + 1
	while (toRow < grid.size) {
		if (grid[toRow][from.col] == '.')
			result.add(Square(toRow, from.col, depth))
		else
			break
		toRow++
	}
	if (debug)
		println("movesSouth from ${from} = ${result.toList()}")
	return result
}

class Castle {
	@Test
	fun main() {
		val scan = Scanner(File("/Users/rodbailey/AndroidStudioProjects/KotlinKoans/app/src/test/java/com" +
				"/bailey/rod/hackerrank/stacksandqueues/castle/input/input13.txt"))
		val n = scan.nextLine().trim().toInt()
		val grid = Array<String>(n, { "" })
		for (i in 0 until n) {
			val gridItem = scan.nextLine()
			grid[i] = gridItem
		}
		val startXStartY = scan.nextLine().split(" ")
		val startX = startXStartY[0].trim().toInt()
		val startY = startXStartY[1].trim().toInt()
		val goalX = startXStartY[2].trim().toInt()
		val goalY = startXStartY[3].trim().toInt()
		val result = minimumMoves(grid, startX, startY, goalX, goalY)
		println(result)
	}
}
