package com.bailey.rod.kotlinkoans.ii_collections

import junit.framework.Assert.assertEquals
import org.junit.Test


class N22FoldKtTest {
    @Test
    fun testGetProductsOrderedByAllCustomers() {
        val testShop = shop("test shop for 'fold'",
            customer(lucas, Canberra,
                order(idea),
                order(webStorm)
            ),
            customer(reka, Budapest,
                order(idea),
                order(youTrack)
            )
        )
        assertEquals(setOf(idea), testShop.getSetOfProductsOrderedByEveryCustomer())
    }
}
