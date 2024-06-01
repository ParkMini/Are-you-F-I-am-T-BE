package kr.pah.rufimt.entity.oxQuiz

import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name = "quiz_question")
data class QuizQuestion(
    @Id
    @GeneratedValue
    val id: UUID = UUID.randomUUID(),

    @Column(nullable = false)
    val usageCount: Int = 0,

    @Column(nullable = false)
    val question: String,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val answer: Answer,

    @Column(nullable = false)
    val correctCount: Int = 0,

    @Column(nullable = false)
    val incorrectCount: Int = 0
)

enum class Answer {
    O, X
}
