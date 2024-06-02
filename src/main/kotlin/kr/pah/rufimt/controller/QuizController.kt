package kr.pah.rufimt.controller

import kr.pah.rufimt.dto.oxQuiz.AnswerCheckDto
import kr.pah.rufimt.dto.oxQuiz.QuizQuestionRequestDto
import kr.pah.rufimt.service.oxQuiz.QuizService
import kr.pah.rufimt.util.Result
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/ox-quiz")
class QuizController(
    private val quizService: QuizService
) {

    /**
     * 새로운 퀴즈 질문을 추가합니다. (관리자 전용)
     */
    @PostMapping("/question")
    fun addQuizQuestion(
        @AuthenticationPrincipal userDetails: UserDetails,
        @RequestBody request: QuizQuestionRequestDto
    ): ResponseEntity<Map<String, Any>> {
        if (userDetails.authorities.any { it.authority == "ROLE_ADMIN" }) {
            val question = quizService.addQuizQuestion(request)
            return Result.created("퀴즈 질문이 성공적으로 추가되었습니다.")
        }
        return Result.forbidden("퀴즈 질문을 추가할 권한이 없습니다.")
    }

    /**
     * 모든 퀴즈 질문을 반환합니다. (관리자 전용)
     */
    @GetMapping("/questions")
    fun getAllQuizQuestions(
        @AuthenticationPrincipal userDetails: UserDetails
    ): ResponseEntity<Map<String, Any>> {
        if (userDetails.authorities.any { it.authority == "ROLE_ADMIN" }) {
            val questions = quizService.getAllQuizQuestions()
            return Result.ok(questions)
        }
        return Result.forbidden("퀴즈 질문을 조회할 권한이 없습니다.")
    }

    /**
     * 새로운 싱글 플레이어 게임을 생성합니다.
     */
    @PostMapping("/single-player-game")
    fun createSinglePlayerGame(): ResponseEntity<Map<String, Any>> {
        val game = quizService.createSinglePlayerGame()
        return Result.created(game)
    }

    /**
     * 주어진 ID에 해당하는 싱글 플레이어 게임을 반환합니다.
     */
    @GetMapping("/single-player-game/{id}")
    fun getSinglePlayerGame(@PathVariable id: UUID): ResponseEntity<Map<String, Any>> {
        val game = quizService.getSinglePlayerGame(id)
        return Result.ok(game)
    }

    /**
     * 사용자의 정답을 체크하고 결과를 반환합니다.
     */
    @PostMapping("/single-player-game/check-answer")
    fun checkAnswer(
        @RequestBody answerCheckDto: AnswerCheckDto
    ): ResponseEntity<Map<String, Any>> {
        val resultMessage = quizService.checkAnswer(answerCheckDto)
        return Result.ok(resultMessage)
    }
}
