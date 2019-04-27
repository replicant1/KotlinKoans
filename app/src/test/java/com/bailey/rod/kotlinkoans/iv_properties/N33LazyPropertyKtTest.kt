package com.bailey.rod.kotlinkoans.iv_properties

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue


class N33LazyPropertyKtTest {
    @Test
    fun testLazy() {
        var initialized = false
        val lazyProperty = LazyProperty { initialized = true; 42 }
        assertFalse(initialized, "Property shouldn't be initialized before access")
        val result: Int = lazyProperty.lazy
        assertTrue(initialized, "Property should be initialized after access")
        assertEquals(42, result)
    }

    @Test
    fun initializedOnce() {
        var initialized = 0
        val lazyProperty = LazyProperty({ initialized++; 42 })
        lazyProperty.lazy
        lazyProperty.lazy
        assertEquals(1, initialized, "Lazy property should be initialized once")

    }
}
