package com.bailey.rod.kotlinkoans.iii_conventions

import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import org.junit.Test


class N30DestructuringDeclarationsKtTest {
    @Test
    fun testIsLeapDay() {
        assertTrue(isLeapDay(MyDate(2016, 1, 29)))
        assertFalse(isLeapDay(MyDate(2015, 1, 29)))
    }
}
