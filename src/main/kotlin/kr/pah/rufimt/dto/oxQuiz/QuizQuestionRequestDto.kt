package kr.pah.rufimt.dto.oxQuiz

import kr.pah.rufimt.entity.oxQuiz.Answer

data class QuizQuestionRequestDto(
    val question: String,
    val answer: Answer,
    val explanation: String
)
