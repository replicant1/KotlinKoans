package com.bailey.rod.kotlinkoans.v_builders

import org.junit.Test
import kotlin.test.assertEquals

class N36ExtensionFunctionLiteralsKtTest {
    @Test
    fun testIsOddAndIsEven() {
        val result = task36()
        assertEquals(listOf(false, true, true), result, "The functions 'isOdd' and 'isEven' should be implemented correctly")
    }
}