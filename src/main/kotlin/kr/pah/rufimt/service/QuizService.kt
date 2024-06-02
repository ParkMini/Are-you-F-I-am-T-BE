package kr.pah.rufimt.service.oxQuiz

import kr.pah.rufimt.dto.oxQuiz.AnswerCheckDto
import kr.pah.rufimt.dto.oxQuiz.QuizQuestionDto
import kr.pah.rufimt.dto.oxQuiz.QuizQuestionRequestDto
import kr.pah.rufimt.dto.oxQuiz.SinglePlayerGameDto
import kr.pah.rufimt.entity.oxQuiz.QuizQuestion
import kr.pah.rufimt.entity.oxQuiz.SinglePlayerGame
import kr.pah.rufimt.repository.oxQuiz.QuizQuestionRepository
import kr.pah.rufimt.repository.oxQuiz.SinglePlayerGameRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class QuizService(
    private val quizQuestionRepository: QuizQuestionRepository,
    private val singlePlayerGameRepository: SinglePlayerGameRepository
) {

    /**
     * 새로운 퀴즈 질문을 추가합니다.
     */
    fun addQuizQuestion(request: QuizQuestionRequestDto): QuizQuestion {
        val newQuestion = QuizQuestion(question = request.question, answer = request.answer)
        return quizQuestionRepository.save(newQuestion)
    }

    /**
     * 모든 퀴즈 질문을 반환합니다.
     */
    fun getAllQuizQuestions(): List<QuizQuestion> {
        return quizQuestionRepository.findAll()
    }

    /**
     * 주어진 ID에 해당하는 퀴즈 질문을 반환합니다.
     */
    fun getQuizQuestion(id: UUID): QuizQuestion {
        return quizQuestionRepository.findById(id).orElseThrow { IllegalArgumentException("퀴즈 질문을 찾을 수 없습니다.") }
    }

    /**
     * 5개의 랜덤한 질문으로 새로운 싱글 플레이어 게임을 생성합니다.
     * 각 질문의 usageCount를 증가시킵니다.
     */
    fun createSinglePlayerGame(): SinglePlayerGame {
        val questions = quizQuestionRepository.findAll().shuffled().take(5)

        // usageCount 증가 및 업데이트
        questions.forEach { question ->
            val updatedQuestion = question.copy(usageCount = question.usageCount + 1)
            quizQuestionRepository.save(updatedQuestion)
        }

        val game = SinglePlayerGame(
            question1 = questions[0],
            question2 = questions[1],
            question3 = questions[2],
            question4 = questions[3],
            question5 = questions[4]
        )
        return singlePlayerGameRepository.save(game)
    }

    /**
     * 주어진 싱글 플레이어 게임을 업데이트합니다.
     */
    fun updateSinglePlayerGame(game: SinglePlayerGame): SinglePlayerGame {
        return singlePlayerGameRepository.save(game)
    }

    /**
     * 주어진 ID에 해당하는 싱글 플레이어 게임을 반환합니다.
     */
    fun getSinglePlayerGame(id: UUID): SinglePlayerGameDto {
        val game = singlePlayerGameRepository.findById(id).orElseThrow { IllegalArgumentException("싱글 플레이어 게임을 찾을 수 없습니다.") }
        return game.toDto()
    }

    /**
     * 문제의 정답을 체크하고 결과를 반환합니다.
     * 정답이면 correctCount를 증가시키고, 오답이면 incorrectCount를 증가시킵니다.
     */
    fun checkAnswer(answerCheckDto: AnswerCheckDto): String {
        val (gameId, questionPosition, userAnswer) = answerCheckDto
        val game = singlePlayerGameRepository.findById(gameId).orElseThrow { IllegalArgumentException("싱글 플레이어 게임을 찾을 수 없습니다.") }
        val question: QuizQuestion = when (questionPosition) {
            "question1" -> game.question1
            "question2" -> game.question2
            "question3" -> game.question3
            "question4" -> game.question4
            "question5" -> game.question5
            else -> throw IllegalArgumentException("잘못된 질문 위치입니다.")
        }

        val isCorrect = question.answer.name == userAnswer
        if (isCorrect) {
            val updatedQuestion = question.copy(correctCount = question.correctCount + 1)
            quizQuestionRepository.save(updatedQuestion)

            // Update the game
            val updatedGame = when (questionPosition) {
                "question1" -> game.copy(question1Correct = true)
                "question2" -> game.copy(question2Correct = true)
                "question3" -> game.copy(question3Correct = true)
                "question4" -> game.copy(question4Correct = true)
                "question5" -> game.copy(question5Correct = true)
                else -> game
            }
            singlePlayerGameRepository.save(updatedGame)

            return "정답입니다."
        } else {
            val updatedQuestion = question.copy(incorrectCount = question.incorrectCount + 1)
            quizQuestionRepository.save(updatedQuestion)
            return "오답입니다."
        }
    }

    private fun SinglePlayerGame.toDto(): SinglePlayerGameDto {
        return SinglePlayerGameDto(
            id = this.id,
            question1 = this.question1.toDto(),
            question2 = this.question2.toDto(),
            question3 = this.question3.toDto(),
            question4 = this.question4.toDto(),
            question5 = this.question5.toDto(),
            question1Correct = this.question1Correct,
            question2Correct = this.question2Correct,
            question3Correct = this.question3Correct,
            question4Correct = this.question4Correct,
            question5Correct = this.question5Correct
        )
    }

    private fun QuizQuestion.toDto(): QuizQuestionDto {
        return QuizQuestionDto(
            id = this.id,
            question = this.question,
            answer = this.answer.name
        )
    }
}
