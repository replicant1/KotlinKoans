package com.bailey.rod.kotlinkoans.iii_conventions

import com.bailey.rod.kotlinkoans.util.TODO

class Invokable {
	var numInvocations: Int = 0
	operator fun invoke(): Invokable {
		numInvocations++
		return this
	}

	fun getNumberOfInvocations(): Int = numInvocations
}

fun todoTask31(): Nothing = TODO(
		"""
        Task 31.
        Change the class 'Invokable' to count the number of invocations:
        for 'invokable()()()()' it should be 4.
    """,
		references = { invokable: Invokable -> })

fun task31(invokable: Invokable): Int {
	// todoTask31()
	return invokable()()()().getNumberOfInvocations()
}
