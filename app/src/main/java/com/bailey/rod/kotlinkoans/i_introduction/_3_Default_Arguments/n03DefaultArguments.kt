package com.bailey.rod.kotlinkoans.i_introduction._3_Default_Arguments

import com.bailey.rod.kotlinkoans.util.TODO
import com.bailey.rod.kotlinkoans.util.doc2


fun todoTask3(): Nothing = TODO(
    """
        Task 3.
        Several overloaded 'foo' functions in the class 'JavaCode3' can be replaced with one function in Kotlin.
        Change the declaration of the function 'foo' in a way that makes the code using 'foo' compile.
        You have to add 'foo' parameters and implement its body.
        Uncomment the commented code and make it compile.
    """,
    documentation = doc2(),
    references = { name: String -> JavaCode3().foo(name); foo(name) })

fun foo(name: String, number: Int = 42, toUpperCase: Boolean = false): String {
    val str: String = name + number.toString()
    return if (toUpperCase) str.toUpperCase() else str
}

fun task3(): String {
    return (foo("a") +
            foo("b", number = 1) +
            foo("c", toUpperCase = true) +
            foo(name = "d", number = 2, toUpperCase = true))
}