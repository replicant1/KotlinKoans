package com.bailey.rod.kotlinkoans.v_builders

import com.bailey.rod.kotlinkoans.util.questions.Answer
import junit.framework.Assert.fail
import org.junit.Test

class N40BuildersHowItWorksKtTest {
    @Test
    fun testBuildersQuiz() {
        val answers = task40()
        if (answers.values.toSet() == setOf(null)) {
            fail("Please specify your answers!")
        }
        val correctAnswers = mapOf(22 - 20 to Answer.b, 1 + 3 to Answer.c, 11 - 8 to Answer.b, 79 - 78 to Answer.c)
        if (correctAnswers != answers) {
            val incorrect = (1..4).filter { answers[it] != correctAnswers[it] }
            fail("Your answers are incorrect! $incorrect")
        }
    }
}