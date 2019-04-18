package com.bailey.rod.kotlinkoans.i_introduction._4_Lambdas

import com.bailey.rod.kotlinkoans.util.TODO
import com.bailey.rod.kotlinkoans.util.doc4


fun example() {

	val sum = { x: Int, y: Int -> x + y }
	val square: (Int) -> Int = { x -> x * x }

	sum(1, square(2)) == 5
}

fun todoTask4(collection: Collection<Int>): Nothing = TODO(
		"""
        Task 4.
        Rewrite 'JavaCode4.task4()' in Kotlin using lambdas:
        return true if the collection contains an even number.
        You can find the appropriate function to call on 'Collection' by using code completion.
        Don't use the class 'Iterables'.
    """,
		documentation = doc4(),
		references = { JavaCode4().task4(collection) })

val evenPredicate = { a: Int -> a %2 == 0 }

fun task4(collection: Collection<Int>): Boolean
		// Full syntactic for of a lambda expression is:
		// val sum = { x: Int, y: Int -> x + y }

		// Full syntax of filter, which takes a single argument, which is a predicate (Int) -> Boolean
		// = collection.filter({ a: Int -> a % 2 == 0 }).isNotEmpty()

		// Convention - if last arg is predicate, move outside of brackets. If not other args, elim brackets
		// = collection.filter{a:Int -> a % 2 ==0 }.isNotEmpty()

		// Use implicit 'it' reference to single parameter
		//= collection.filter { it % 2 == 0 }.isNotEmpty()

		= collection.filter(evenPredicate).isNotEmpty()