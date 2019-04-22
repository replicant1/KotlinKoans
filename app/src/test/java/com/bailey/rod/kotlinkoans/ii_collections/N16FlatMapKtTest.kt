package com.bailey.rod.kotlinkoans.ii_collections

import junit.framework.Assert.assertEquals
import org.junit.Test


class N16FlatMapKtTest {
    @Test
    fun testGetOrderedProductsSet() {
        assertEquals(setOf(idea), customers[reka]!!.orderedProducts)
    }

    @Test
    fun testGetAllOrderedProducts() {
        assertEquals(orderedProducts, shop.allOrderedProducts)
    }
}
