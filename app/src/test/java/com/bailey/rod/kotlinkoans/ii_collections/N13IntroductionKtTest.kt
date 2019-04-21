package com.bailey.rod.kotlinkoans.ii_collections

import org.junit.Test
import kotlin.test.assertEquals

class N13IntroductionKtTest {
    @Test
    fun testSetOfCustomers() {
        val rawCustomers: Set<Customer> = customers.values.toSet()
        val shopCustomers: Set<Customer> = shop.getSetOfCustomers()
        System.out.println("rawCustomers=${rawCustomers}")
        System.out.println("shopCustomers=${shopCustomers}")
        assertEquals(rawCustomers, shopCustomers)
    }
}
