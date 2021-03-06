package com.bailey.rod.kotlinkoans.v_builders

import org.junit.Test
import kotlin.test.assertEquals

class N38TheFunctionApplyKtTest {
    @Test
    fun testBuildString() {
        println("Into testBuildString")
        val expected = StringBuilder().apply {
            append("Numbers: ")
            for (i in 1..10) {
                append(i)
            }
        }.toString()
        println("Into testBuildString: expected=\"${expected}\"")
        val actual = buildString()
        println("Into testBuildString: actual=\"${actual}\"")
        assertEquals(expected, actual, "String should be built:")
    }

    @Test
    fun testBuildMap() {
        val expected = HashMap<Int, String>().apply {
            put(0, "0")
            for (i in 1..10) {
                put(i, "$i")
            }
        }
        val actual = buildMap()
        assertEquals(expected, actual, "Map should be filled with the right values")
    }
}
