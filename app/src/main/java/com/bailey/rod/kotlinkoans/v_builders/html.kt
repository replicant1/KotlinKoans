package com.bailey.rod.kotlinkoans.v_builders

import java.util.*

/**
 * An Html tag which has a list of child Tags and a list of attributes.
 */
open class Tag(val name: String) {
	val children: MutableList<Tag> = ArrayList()
	val attributes: MutableList<Attribute> = ArrayList()

	override fun toString(): String {
		return "<$name" +
				(if (attributes.isEmpty()) "" else attributes.joinToString(separator = " ", prefix = " ")) + ">" +
				(if (children.isEmpty()) "" else children.joinToString(separator = "")) +
				"</$name>"
	}
}

/**
 * An attribute of an HTML Tag.
 */
class Attribute(val name: String, val value: String) {
	override fun toString() = """$name="$value""""
}

/**
 * Extension function for Tag or any subclass of Tag
 */
fun <T : Tag> T.set(name: String, value: String?): T {
	if (value != null) {
		attributes.add(Attribute(name, value))
	}
	return this
}

/**
 * Extension function for Tag or any subclass of Tag
 */
fun <T : Tag> Tag.doInit(tag: T, init: T.() -> Unit): T {
	tag.init()
	children.add(tag)
	return tag
}

/**
 * Subclasses of "Tag" for <html>, <table>, <center>, <tr>, <td>, <b>
 */
class Html : Tag("html")
class Table : Tag("table")
class Center : Tag("center")
class TR : Tag("tr")
class TD : Tag("td")
class Text(val text: String) : Tag("b") {
	override fun toString() = text
}

/**
 * Builder extension functions - each Tag is built by its parent Tag.
 * <pre>
 * <html> has no parent tag an so is at root level
 * <html> tag creates <table> and <center>
 * <tr> and <td> tags are created by <table>
 * <text> is created by anything.
 * </pre>
 */
fun html(init: Html.() -> Unit): Html = Html().apply(init)
fun Html.table(init: Table.() -> Unit) = doInit(Table(), init)
fun Html.center(init: Center.() -> Unit) = doInit(Center(), init)
fun Table.tr(color: String? = null, init: TR.() -> Unit) = doInit(TR(), init).set("bgcolor", color)
fun TR.td(color: String? = null, align: String = "left", init: TD.() -> Unit) = doInit(TD(), init).set("align", align).set("bgcolor", color)
fun Tag.text(s: Any?) = doInit(Text(s.toString()), {})

