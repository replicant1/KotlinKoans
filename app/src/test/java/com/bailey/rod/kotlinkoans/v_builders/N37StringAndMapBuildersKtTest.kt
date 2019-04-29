package com.bailey.rod.kotlinkoans.v_builders

import org.junit.Test

import java.util.*
import kotlin.test.assertEquals

class N37StringAndMapBuildersKtTest {
    @Test
    fun testBuildMap() {
        val map = task37()
        val expected = HashMap<Int, String>()
        for (i in 0..10) {
            expected[i] = "$i"
        }
        assertEquals(expected, map, "Map should be filled with the right values")
    }
}