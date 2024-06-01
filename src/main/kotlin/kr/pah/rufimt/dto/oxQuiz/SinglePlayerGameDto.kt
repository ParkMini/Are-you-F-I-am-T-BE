package kr.pah.rufimt.dto.oxQuiz

import kr.pah.rufimt.entity.oxQuiz.QuizQuestion
import java.util.UUID

data class SinglePlayerGameDto(
    val id: UUID,
    val question1: QuizQuestion,
    val question2: QuizQuestion,
    val question3: QuizQuestion,
    val question4: QuizQuestion,
    val question5: QuizQuestion,
    val question1Correct: Boolean,
    val question2Correct: Boolean,
    val question3Correct: Boolean,
    val question4Correct: Boolean,
    val question5Correct: Boolean
)
