package com.bailey.rod.kotlinkoans.ii_collections

import junit.framework.Assert.assertEquals
import org.junit.Test


class N20GroupByKtTest {
    @Test
    fun testGroupCustomersByCity() {
        assertEquals(groupedByCities, shop.groupCustomersByCity())
    }
}
