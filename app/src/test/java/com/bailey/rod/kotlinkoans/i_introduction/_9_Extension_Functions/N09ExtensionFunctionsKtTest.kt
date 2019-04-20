package com.bailey.rod.kotlinkoans.i_introduction._9_Extension_Functions

import org.junit.Test
import kotlin.test.assertEquals


class N09ExtensionFunctionsKtTest {
    @Test
    fun testIntExtension() {
        assertEquals(RationalNumber(4, 1), 4.r(), "Rational number creation error: ")
    }

    @Test
    fun testPairExtension() {
        assertEquals(RationalNumber(2, 3), Pair(2, 3).r(), "Rational number creation error: ")
    }
}