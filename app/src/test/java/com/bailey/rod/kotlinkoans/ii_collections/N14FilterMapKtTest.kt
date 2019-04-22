package N14FilterMapKtTest.kt

import com.bailey.rod.kotlinkoans.ii_collections.*
import org.junit.Test
import kotlin.test.assertEquals


class N14FilterMapKtTest {
    @Test
    fun testCitiesCustomersAreFrom() {
        assertEquals(setOf(Canberra, Vancouver, Budapest, Ankara, Tokyo), shop.getCitiesCustomersAreFrom())
    }

    /**
     * Returns the list of the customers who live in the city 'city'
     */
    @Test
    fun testCustomersFromCity() {
        assertEquals(listOf(customers[lucas], customers[cooper]), shop.getCustomersFrom(Canberra))
    }
}
