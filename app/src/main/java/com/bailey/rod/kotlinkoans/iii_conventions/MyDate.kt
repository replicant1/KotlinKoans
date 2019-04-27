package com.bailey.rod.kotlinkoans.iii_conventions

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {
    override fun compareTo(other: MyDate): Int {
        var result: Int = year - other.year
        if (result == 0) {
            result = month - other.month
            if (result == 0) {
                result = dayOfMonth - other.dayOfMonth
            }
        }
       return result
    }
}

operator fun MyDate.rangeTo(other: MyDate): DateRange {
    return DateRange(this, other)
}

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

class DateRange(val start: MyDate, val endInclusive: MyDate) {
    operator fun contains(d: MyDate) : Boolean {
        return (start.compareTo(d) <= 0) && (endInclusive.compareTo(d) >= 0)
    }
}
