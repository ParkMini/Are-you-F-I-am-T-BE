package kr.pah.rufimt.entity.oxQuiz

import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name = "single_player_game")
data class SinglePlayerGame(
    @Id
    @GeneratedValue
    val id: UUID = UUID.randomUUID(),

    @ManyToOne
    @JoinColumn(name = "question1_id")
    val question1: QuizQuestion,

    @ManyToOne
    @JoinColumn(name = "question2_id")
    val question2: QuizQuestion,

    @ManyToOne
    @JoinColumn(name = "question3_id")
    val question3: QuizQuestion,

    @ManyToOne
    @JoinColumn(name = "question4_id")
    val question4: QuizQuestion,

    @ManyToOne
    @JoinColumn(name = "question5_id")
    val question5: QuizQuestion,

    @Column(nullable = false)
    val question1Correct: Boolean = false,

    @Column(nullable = false)
    val question2Correct: Boolean = false,

    @Column(nullable = false)
    val question3Correct: Boolean = false,

    @Column(nullable = false)
    val question4Correct: Boolean = false,

    @Column(nullable = false)
    val question5Correct: Boolean = false
)
