package kr.pah.rufimt.dto.oxQuiz

import java.util.UUID

data class SinglePlayerGameDto(
    val id: UUID,
    val question1: QuizQuestionDto,
    val question2: QuizQuestionDto,
    val question3: QuizQuestionDto,
    val question4: QuizQuestionDto,
    val question5: QuizQuestionDto,
    val question1Correct: Boolean,
    val question2Correct: Boolean,
    val question3Correct: Boolean,
    val question4Correct: Boolean,
    val question5Correct: Boolean
)
