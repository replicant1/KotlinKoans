package com.bailey.rod.kotlinkoans.iv_properties

import com.bailey.rod.kotlinkoans.util.TODO

class LazyProperty(val initializer: () -> Int) {
    var lazyIsInitialized: Boolean = false
    val lazy: Int = -1
        get() {
            if (!lazyIsInitialized) {
               // field = initializer()
                lazyIsInitialized = true
            }
            return field
        }



}

fun todoTask33(): Nothing = TODO(
    """
        Task 33.
        Add a custom getter to make the 'lazy' val really lazy.
        It should be initialized by the invocation of 'initializer()'
        at the moment of the first access.
        You can add as many additional properties as you need.
        Do not use delegated properties yet!
    """,
    references = { LazyProperty({ 42 }).lazy }
)
