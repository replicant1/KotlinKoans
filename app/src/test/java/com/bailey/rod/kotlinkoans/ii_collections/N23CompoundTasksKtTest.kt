package com.bailey.rod.kotlinkoans.ii_collections

import junit.framework.Assert.assertEquals
import org.junit.Test
import kotlin.test.assertEquals


class N23CompoundTasksKtTest {
    @Test
    fun testGetCustomersWhoOrderedProduct() {
        assertEquals(setOf(customers[reka], customers[asuka]), shop.getCustomersWhoOrderedProduct(idea))
    }

    @Test
    fun testMostExpensiveDeliveredProduct() {
        val testShop = shop("test shop for 'most expensive delivered product'",
            customer(lucas, Canberra,
                order(idea, isDelivered = false),
                order(reSharper)
            )
        )
        assertEquals(reSharper, testShop.customers[0].getMostExpensiveDeliveredProduct())
    }

    @Test
    fun testNumberOfTimesEachProductWasOrdered() {
        assertEquals(4, shop.getNumberOfTimesProductWasOrdered(idea))
    }

    @Test
    fun testNumberOfTimesEachProductWasOrderedForRepeatedProduct() {
        assertEquals(3, shop.getNumberOfTimesProductWasOrdered(reSharper), "A customer may order a product for several times")
    }

    @Test
    fun testNumberOfTimesEachProductWasOrderedForRepeatedInOrderProduct() {
        assertEquals(3, shop.getNumberOfTimesProductWasOrdered(phpStorm), "An order may contain a particular product more than once")
    }
}
