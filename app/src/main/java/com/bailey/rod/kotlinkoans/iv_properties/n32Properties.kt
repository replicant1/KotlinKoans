package com.bailey.rod.kotlinkoans.iv_properties

import com.bailey.rod.kotlinkoans.util.TODO
import com.bailey.rod.kotlinkoans.util.doc32


class PropertyExample() {
    var counter = 0
    var propertyWithCounter: Int? = 0
    set(value) {
        counter++
        field = value
    }
}

fun todoTask32(): Nothing = TODO(
    """
        Task 32.
        Add a custom setter to 'PropertyExample.propertyWithCounter' so that
        the 'counter' property is incremented every time 'propertyWithCounter' is assigned to.
    """,
    documentation = doc32(),
    references = { PropertyExample() }
)
