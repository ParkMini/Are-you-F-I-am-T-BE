package kr.pah.rufimt.repository.oxQuiz

import kr.pah.rufimt.entity.oxQuiz.QuizQuestion
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface QuizQuestionRepository : JpaRepository<QuizQuestion, UUID>
