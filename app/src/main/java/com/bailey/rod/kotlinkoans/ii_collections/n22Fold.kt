package com.bailey.rod.kotlinkoans.ii_collections

fun example9() {
	val result = listOf(1, 2, 3, 4).fold(1, { partResult, element -> element * partResult })
	result == 24
}

// The same as
fun whatFoldDoes(): Int {
	var result = 1
	listOf(1, 2, 3, 4).forEach { element -> result = element * result }
	return result
}

/**
 * @return Every product P in this set was ordered by every customer C known to the shop.
 */
fun Shop.getSetOfProductsOrderedByEveryCustomer(): Set<Product> {
	// Return the set of products that were ordered by every
	System.out.println("allOrderedProducts=${allOrderedProducts}")
	System.out.println("customers=${customers}")
	return customers.fold(allOrderedProducts, { orderedByAll, customer ->
		System.out.println("======")
		System.out.println("customer=${customer}")
		System.out.println("customer.orderedProducts=${customer.orderedProducts}")
		System.out.println("orderedByAll=${orderedByAll}")
		System.out.println("======")
		customer.orderedProducts.intersect(orderedByAll)
	})
}
