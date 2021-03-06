package com.bailey.rod.kotlinkoans.vi_generics

import com.bailey.rod.kotlinkoans.util.TODO


fun task41(): Nothing = TODO(
		"""
        Task41.
        Add a 'partitionTo' function that splits a collection into two collections according to a predicate.
        Uncomment the commented invocations of 'partitionTo' below and make them compile.

        There is a 'partition()' function in the standard library that always returns two newly created lists.
        You should write a function that splits the collection into two collections given as arguments.
        The signature of the 'toCollection()' function from the standard library may help you.
    """,
		references = { l: List<Int> ->
			l.partition { it > 0 }
			l.toCollection(HashSet<Int>())
		}
)

fun List<String>.partitionWordsAndLines(): Pair<List<String>, List<String>> {
	return partitionTo(ArrayList<String>(), ArrayList()) { s -> !s.contains(" ") }
}

fun Set<Char>.partitionLettersAndOtherSymbols(): Pair<Set<Char>, Set<Char>> {
	return partitionTo(HashSet<Char>(), HashSet()) { c -> c in 'a'..'z' || c in 'A'..'Z' }
}

//fun List<String>.partitionTo(array1: MutableList<String> ,array2 : MutableList<String>, p_func: (String) ->
//Boolean) : Pair<List<String>,List<String>> {
//	val (partition1, partition2) = this.partition(p_func);
//    partition1.toCollection(array1)
//	partition2.toCollection(array2)
//	return Pair(partition1, partition2)
//}
//
//fun Set<Char>.partitionTo(set1: MutableSet<Char>, set2: MutableSet<Char>, p_func: (Char) -> Boolean) :
//		Pair<Set<Char>, Set<Char>> {
//	val (partition1, partition2) = this.partition(p_func)
//	partition1.toCollection(set1)
//	partition2.toCollection(set2)
//	return Pair(set1, set2)
//}

fun <T, C : kotlin.collections.Collection<T>, M : kotlin.collections.MutableCollection<T>> C.partitionTo(
		c1: M, c2: M, p_func: (T) -> Boolean): Pair<C, C> {
	val (p1, p2) = this.partition(p_func)
	p1.toCollection(c1)
	p2.toCollection(c2)
	return Pair(c1 as C, c2 as C)
}



