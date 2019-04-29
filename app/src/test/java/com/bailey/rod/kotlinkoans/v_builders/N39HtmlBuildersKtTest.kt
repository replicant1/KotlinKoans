package com.bailey.rod.kotlinkoans.v_builders

import org.junit.Test
import kotlin.test.assertTrue


class N39HtmlBuildersKtTest {
    @Test
    fun productTableIsFilled() {
        val result = renderProductTable()
        assertTrue(result.contains("cactus"), "Product table should contain corresponding data")
    }

    @Test
    fun productTableIsColored() {
        val result = renderProductTable()
        println("result = ${result}")
        assertTrue(result.contains("bgcolor"), "Product table should be colored")
    }
}
