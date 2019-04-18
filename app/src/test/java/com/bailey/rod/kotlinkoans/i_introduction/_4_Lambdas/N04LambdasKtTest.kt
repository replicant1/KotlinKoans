package com.bailey.rod.kotlinkoans.i_introduction._4_Lambdas

import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import org.junit.Test


class N04LambdasKtTest {
    @Test
    fun contains() {
        assertTrue(task4(listOf(1, 2, 3)))
    }

    @Test
    fun notContains() {
        assertFalse(task4(listOf(1, 3, 5)))
    }
}