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
    return partitionTo(HashSet<Char>(), HashSet()) { c -> c in 'a'..'z' || c in 'A'..'Z'}
//	return Pair(emptySet(), emptySet())
}

fun List<String>.partitionTo(array1: ArrayList<String> ,array2 : ArrayList<String>, p_func: (String) -> Boolean) :
		Pair<List<String>,List<String>> {
	val (partition1, partition2) = this.partition(p_func);
    partition1.toCollection(array1)
	partition2.toCollection(array2)
	return Pair(partition1, partition2)
}

fun Set<Char>.partitionTo(set1: HashSet<Char>, set2: HashSet<Char>, p_func: (Char) -> Boolean) :
		Pair<Set<Char>, Set<Char>> {
	val (partition1, partition2) = this.partition(p_func)
	partition1.toCollection(set1)
	partition2.toCollection(set2)
	return Pair(set1, set2)
}





