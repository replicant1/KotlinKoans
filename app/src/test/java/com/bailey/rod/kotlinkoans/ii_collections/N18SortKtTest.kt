package com.bailey.rod.kotlinkoans.ii_collections

import junit.framework.Assert.assertEquals
import org.junit.Test


class N18SortKtTest {
    @Test
    fun testGetCustomersSortedByNumberOfOrders() {
        assertEquals(sortedCustomers, shop.getCustomersSortedByNumberOfOrders())
    }
}
