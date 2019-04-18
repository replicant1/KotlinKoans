package com.bailey.rod.kotlinkoans.i_introduction._2_Named_Arguments

import junit.framework.Assert.assertEquals
import org.junit.Test


class N02NamedArgumentsKtTest {

    @Test
    fun testJoinToString() {
        assertEquals("{1, 2, 3, 42, 555}", task2(listOf(1, 2, 3, 42, 555)))
    }

}
