package kr.pah.rufimt.repository.oxQuiz

import kr.pah.rufimt.entity.oxQuiz.SinglePlayerGame
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface SinglePlayerGameRepository : JpaRepository<SinglePlayerGame, UUID>
