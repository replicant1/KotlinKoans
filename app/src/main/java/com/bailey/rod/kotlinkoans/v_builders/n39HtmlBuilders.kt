package com.bailey.rod.kotlinkoans.v_builders

import com.bailey.rod.kotlinkoans.util.TODO
import com.bailey.rod.kotlinkoans.util.doc39

fun getTitleColor() = "#b9c9fe"
fun getCellColor(row: Int, column: Int) = if ((row + column) % 2 == 0) "#dce4ff" else "#eff2ff"

fun todoTask39(): Nothing = TODO(
		"""
        Task 39.
        1) Fill the table with the proper values from products.
        2) Color the table like a chess board (using getTitleColor() and getCellColor() functions above).
        Pass a color as an argument to functions 'tr', 'td'.
        You can call the 'main' function in the 'htmlDemo.kt' to see the rendered table.
    """,
		documentation = doc39()
)

fun renderProductTable(): String {
	return html {
		table {
			tr(color = getTitleColor()) {
				td {
					text("Product")
				}
				td {
					text("Price")
				}
				td {
					text("Popularity")
				}
			} // tr - title row

			val products = getProducts()
			products.forEachIndexed { rowNumber, product ->
				tr {
					td(color = getCellColor(rowNumber, 0)) {
						text(product.description)
					}
					td(color = getCellColor(rowNumber, 1)) {
						text(product.price)
					}
					td(color = getCellColor(rowNumber, 2)) {
						text(product.popularity)
					}
				}
			}
			// todoTask39()
		}
	}.toString()
}
