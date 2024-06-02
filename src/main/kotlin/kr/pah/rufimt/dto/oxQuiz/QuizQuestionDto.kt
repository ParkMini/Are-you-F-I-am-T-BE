package kr.pah.rufimt.dto.oxQuiz

import java.util.UUID

data class QuizQuestionDto(
    val id: UUID,
    val question: String,
    val answer: String
)
