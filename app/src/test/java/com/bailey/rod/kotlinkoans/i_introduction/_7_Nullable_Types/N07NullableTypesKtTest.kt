package com.bailey.rod.kotlinkoans.i_introduction._7_Nullable_Types

import org.junit.Test
import kotlin.test.assertEquals

class N07NullableTypesKtTest {
	private fun testSendMessageToClient(
			client: Client?,
			message: String?,
			email: String? = null,
			shouldBeInvoked: Boolean = false
	) {
		var invoked = false
		sendMessageToClient(client, message, object : Mailer {
			override fun sendMessage(actualEmail: String, actualMessage: String) {
				invoked = true
				assertEquals(message, actualMessage, "message=${message},actualMessage=${actualMessage}")
				assertEquals(email, actualEmail, "The email is not as expected:")
			}
		})
		assertEquals(shouldBeInvoked, invoked,
				"The function 'sendMessage' should${if (shouldBeInvoked) "" else "n't"} be invoked")
	}

	@Test
	fun everythingIsOk() {
		testSendMessageToClient(Client(PersonalInfo("bob@gmail.com")),
				"Hi Bob! We have an awesome proposition for you...",
				"bob@gmail.com",
				true)
	}

	@Test
	fun noMessage() {
		testSendMessageToClient(Client(PersonalInfo("bob@gmail.com")), null)
	}

	@Test
	fun noEmail() {
		testSendMessageToClient(Client(PersonalInfo(null)), "Hi Bob! We have an awesome proposition for you...")
	}

	@Test
	fun noPersonalInfo() {
		testSendMessageToClient(Client(null), "Hi Bob! We have an awesome proposition for you...")
	}

	@Test
	fun noClient() {
		testSendMessageToClient(null, "Hi Bob! We have an awesome proposition for you...")
	}
}