package com.bailey.rod.kotlinkoans.iii_conventions

import com.bailey.rod.kotlinkoans.util.TODO
import com.bailey.rod.kotlinkoans.util.doc26

fun todoTask27(): Nothing = TODO(
    """
        Task 27.
        Uncomment the commented code and make it compile.
        Make all the changes to the file MyDate.kt.

        Tips: To make '..' work implement a 'MyDate.rangeTo()' extension function returning 'DateRange'.
    """,
    documentation = doc26()
)

fun checkInRange2(date: MyDate, first: MyDate, last: MyDate): Boolean {
    return date in first..last
}
