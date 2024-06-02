package kr.pah.rufimt.dto.oxQuiz

import java.util.UUID

data class AnswerCheckDto(
    val gameId: UUID,
    val questionPosition: String,
    val answer: String
)
