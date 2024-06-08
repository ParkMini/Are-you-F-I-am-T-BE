package kr.pah.rufimt.config

import kr.pah.rufimt.entity.point.PointProduct
import kr.pah.rufimt.entity.oxQuiz.QuizQuestion
import kr.pah.rufimt.entity.oxQuiz.Answer
import kr.pah.rufimt.repository.point.PointProductRepository
import kr.pah.rufimt.repository.oxQuiz.QuizQuestionRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.annotation.Transactional

@Configuration
class DataInitializer(
    private val pointProductRepository: PointProductRepository,
    private val quizQuestionRepository: QuizQuestionRepository
) {

    /**
     * 포인트 충전 상품 초기화
     */
    @Bean
    @Transactional
    fun initializePointProducts() {
        if (pointProductRepository.count() == 0L) {
            val products = listOf(
                PointProduct(name = "5천원 - 100포인트", price = 5000, points = 100, bonusPercentage = 2.5),
                PointProduct(name = "1만원 - 200포인트", price = 10000, points = 200, bonusPercentage = 5.0),
                PointProduct(name = "2만원 - 400포인트", price = 20000, points = 400, bonusPercentage = 5.0),
                PointProduct(name = "5만원 - 1000포인트", price = 50000, points = 1000, bonusPercentage = 7.5),
                PointProduct(name = "10만원 - 2000포인트", price = 100000, points = 2000, bonusPercentage = 7.5)
            )
            pointProductRepository.saveAll(products)
        }
    }

    /**
     * MBTI 질문 초기화
     */
    @Bean
    @Transactional
    fun initializeQuizQuestions() {
        if (quizQuestionRepository.count() == 0L) {
            val quizQuestions = listOf(
                QuizQuestion(question = "T 유형은 결정을 내릴 때 논리와 객관성을 중시한다.", answer = Answer.O, explanation = "T 유형은 결정을 내릴 때 논리와 객관성을 중시합니다. 감정보다는 사실과 데이터를 기반으로 판단하는 경향이 있습니다."),
                QuizQuestion(question = "F 유형은 결정을 내릴 때 감정과 사람들의 의견을 중시한다.", answer = Answer.O, explanation = "F 유형은 결정을 내릴 때 감정과 사람들의 의견을 중요하게 생각합니다. 논리보다는 인간 관계와 조화를 중시합니다."),
                QuizQuestion(question = "T 유형은 갈등 상황에서 논리적 해결책을 선호한다.", answer = Answer.O, explanation = "T 유형은 갈등 상황에서 논리적이고 객관적인 해결책을 찾는 경향이 있습니다."),
                QuizQuestion(question = "F 유형은 갈등 상황에서 감정적 해결책을 선호한다.", answer = Answer.O, explanation = "F 유형은 갈등 상황에서 감정적이고 공감적인 해결책을 찾는 경향이 있습니다."),
                QuizQuestion(question = "T 유형은 감정 표현을 잘 하지 않는다.", answer = Answer.O, explanation = "T 유형은 감정 표현을 잘 하지 않는 편입니다. 감정보다는 논리와 이성에 집중합니다."),
                QuizQuestion(question = "F 유형은 감정 표현을 중요하게 생각한다.", answer = Answer.O, explanation = "F 유형은 감정 표현을 중요하게 생각합니다. 감정을 솔직하게 드러내는 것을 중요시합니다."),
                QuizQuestion(question = "T 유형은 논리적인 비판을 잘 수용한다.", answer = Answer.O, explanation = "T 유형은 논리적인 비판을 잘 수용하는 편입니다. 비판을 감정적으로 받아들이기보다는 개선의 기회로 생각합니다."),
                QuizQuestion(question = "F 유형은 비판을 감정적으로 받아들일 수 있다.", answer = Answer.O, explanation = "F 유형은 비판을 감정적으로 받아들일 수 있습니다. 비판이 자신을 공격하는 것으로 느낄 수 있습니다."),
                QuizQuestion(question = "T 유형은 결정을 내릴 때 데이터를 중요하게 생각한다.", answer = Answer.O, explanation = "T 유형은 결정을 내릴 때 데이터를 중요하게 생각합니다. 객관적인 정보를 바탕으로 결정을 내립니다."),
                QuizQuestion(question = "F 유형은 결정을 내릴 때 사람들의 감정을 중요하게 생각한다.", answer = Answer.O, explanation = "F 유형은 결정을 내릴 때 사람들의 감정을 중요하게 생각합니다. 감정과 인간관계를 고려합니다."),
                QuizQuestion(question = "T 유형은 감정보다 사실을 중시한다.", answer = Answer.O, explanation = "T 유형은 감정보다 사실을 중시합니다. 객관적인 정보와 논리를 우선시합니다."),
                QuizQuestion(question = "F 유형은 사실보다 감정을 중시한다.", answer = Answer.O, explanation = "F 유형은 사실보다 감정을 중시합니다. 사람들의 감정과 관계를 더 중요하게 생각합니다."),
                QuizQuestion(question = "T 유형은 논리적인 토론을 즐긴다.", answer = Answer.O, explanation = "T 유형은 논리적인 토론을 즐깁니다. 논리와 이성을 기반으로 한 대화를 선호합니다."),
                QuizQuestion(question = "F 유형은 조화를 중요시하여 논쟁을 피하려고 한다.", answer = Answer.O, explanation = "F 유형은 조화를 중요시하여 논쟁을 피하려고 합니다. 갈등을 피하고 평화를 유지하려고 합니다."),
                QuizQuestion(question = "T 유형은 문제 해결에서 효율성을 중시한다.", answer = Answer.O, explanation = "T 유형은 문제 해결에서 효율성을 중시합니다. 가장 효율적인 방법을 찾으려고 합니다."),
                QuizQuestion(question = "F 유형은 문제 해결에서 관계 유지를 중시한다.", answer = Answer.O, explanation = "F 유형은 문제 해결에서 관계 유지를 중시합니다. 사람들 간의 관계를 중요하게 생각합니다."),
                QuizQuestion(question = "T 유형은 결정을 내릴 때 개인의 감정을 배제하려고 한다.", answer = Answer.O, explanation = "T 유형은 결정을 내릴 때 개인의 감정을 배제하려고 합니다. 감정보다는 사실과 데이터를 중시합니다."),
                QuizQuestion(question = "F 유형은 결정을 내릴 때 감정적 요소를 고려한다.", answer = Answer.O, explanation = "F 유형은 결정을 내릴 때 감정적 요소를 고려합니다. 감정과 인간관계를 중시합니다."),
                QuizQuestion(question = "T 유형은 규칙과 원칙을 중시한다.", answer = Answer.O, explanation = "T 유형은 규칙과 원칙을 중시합니다. 논리적 일관성과 규칙을 중요하게 생각합니다."),
                QuizQuestion(question = "F 유형은 사람들의 행복을 중시한다.", answer = Answer.O, explanation = "F 유형은 사람들의 행복을 중시합니다. 사람들의 감정과 행복을 중요하게 생각합니다."),
                QuizQuestion(question = "T 유형은 객관적인 피드백을 중요하게 생각한다.", answer = Answer.O, explanation = "T 유형은 객관적인 피드백을 중요하게 생각합니다. 피드백을 통해 개선할 점을 찾으려고 합니다."),
                QuizQuestion(question = "F 유형은 피드백을 받을 때 감정적인 반응을 보일 수 있다.", answer = Answer.O, explanation = "F 유형은 피드백을 받을 때 감정적인 반응을 보일 수 있습니다. 피드백을 개인적인 공격으로 받아들일 수 있습니다."),
                QuizQuestion(question = "T 유형은 효율성과 생산성을 중시한다.", answer = Answer.O, explanation = "T 유형은 효율성과 생산성을 중시합니다. 목표를 달성하기 위해 가장 효율적인 방법을 찾습니다."),
                QuizQuestion(question = "F 유형은 팀워크와 협력을 중시한다.", answer = Answer.O, explanation = "F 유형은 팀워크와 협력을 중시합니다. 사람들 간의 관계와 협력을 중요하게 생각합니다."),
                QuizQuestion(question = "T 유형은 공정한 평가를 중요시한다.", answer = Answer.O, explanation = "T 유형은 공정한 평가를 중요시합니다. 객관적이고 공정한 평가를 중요하게 생각합니다."),
                QuizQuestion(question = "F 유형은 사람 간의 관계를 중요시한다.", answer = Answer.O, explanation = "F 유형은 사람 간의 관계를 중요시합니다. 사람들 간의 조화와 관계를 중요하게 생각합니다."),
                QuizQuestion(question = "T 유형은 논리적 일관성을 유지하려고 한다.", answer = Answer.O, explanation = "T 유형은 논리적 일관성을 유지하려고 합니다. 논리적 일관성을 중요하게 생각합니다."),
                QuizQuestion(question = "F 유형은 상황에 따라 유연하게 대응한다.", answer = Answer.O, explanation = "F 유형은 상황에 따라 유연하게 대응합니다. 상황에 따라 융통성 있게 행동합니다."),
                QuizQuestion(question = "T 유형은 비판적 사고를 중요하게 생각한다.", answer = Answer.O, explanation = "T 유형은 비판적 사고를 중요하게 생각합니다. 논리적이고 비판적인 사고를 중요하게 생각합니다."),
                QuizQuestion(question = "F 유형은 이해와 공감을 중요하게 생각한다.", answer = Answer.O, explanation = "F 유형은 이해와 공감을 중요하게 생각합니다. 사람들 간의 이해와 공감을 중시합니다."),
                QuizQuestion(question = "T 유형은 효율성을 최우선으로 생각한다.", answer = Answer.O, explanation = "T 유형은 효율성을 최우선으로 생각합니다. 가장 효율적인 방법을 찾으려고 합니다."),
                QuizQuestion(question = "F 유형은 사람들의 감정을 최우선으로 생각한다.", answer = Answer.O, explanation = "F 유형은 사람들의 감정을 최우선으로 생각합니다. 사람들의 감정을 중요하게 생각합니다."),
                QuizQuestion(question = "T 유형은 사실과 데이터를 기반으로 결정을 내린다.", answer = Answer.O, explanation = "T 유형은 사실과 데이터를 기반으로 결정을 내립니다. 객관적인 정보를 바탕으로 결정을 내립니다."),
                QuizQuestion(question = "F 유형은 사람들의 의견과 감정을 기반으로 결정을 내린다.", answer = Answer.O, explanation = "F 유형은 사람들의 의견과 감정을 기반으로 결정을 내립니다. 인간관계와 감정을 중요하게 생각합니다."),
                QuizQuestion(question = "T 유형은 목표를 달성하는 데 집중한다.", answer = Answer.O, explanation = "T 유형은 목표를 달성하는 데 집중합니다. 효율적으로 목표를 달성하려고 합니다."),
                QuizQuestion(question = "F 유형은 과정에서의 조화를 중시한다.", answer = Answer.O, explanation = "F 유형은 과정에서의 조화를 중시합니다. 과정에서 사람들 간의 조화를 중요하게 생각합니다."),
                QuizQuestion(question = "T 유형은 갈등 상황에서도 감정을 배제하려고 한다.", answer = Answer.O, explanation = "T 유형은 갈등 상황에서도 감정을 배제하려고 합니다. 논리와 이성에 집중합니다."),
                QuizQuestion(question = "F 유형은 갈등 상황에서 감정을 표현하려고 한다.", answer = Answer.O, explanation = "F 유형은 갈등 상황에서 감정을 표현하려고 합니다. 자신의 감정을 솔직하게 드러내려고 합니다."),
                QuizQuestion(question = "T 유형은 비판을 받아들일 때 감정을 배제한다.", answer = Answer.O, explanation = "T 유형은 비판을 받아들일 때 감정을 배제합니다. 비판을 논리적으로 수용합니다."),
                QuizQuestion(question = "F 유형은 비판을 받아들일 때 감정적으로 반응할 수 있다.", answer = Answer.O, explanation = "F 유형은 비판을 받아들일 때 감정적으로 반응할 수 있습니다. 비판을 개인적인 공격으로 받아들일 수 있습니다."),
                QuizQuestion(question = "T 유형은 논리적인 이유를 중시한다.", answer = Answer.O, explanation = "T 유형은 논리적인 이유를 중시합니다. 결정을 내릴 때 논리와 이성을 중시합니다."),
                QuizQuestion(question = "F 유형은 감정적인 이유를 중시한다.", answer = Answer.O, explanation = "F 유형은 감정적인 이유를 중시합니다. 감정과 인간관계를 중요하게 생각합니다."),
                QuizQuestion(question = "T 유형은 문제 해결에서 논리적 접근을 선호한다.", answer = Answer.O, explanation = "T 유형은 문제 해결에서 논리적 접근을 선호합니다. 객관적인 정보를 바탕으로 문제를 해결하려고 합니다."),
                QuizQuestion(question = "F 유형은 문제 해결에서 감정적 접근을 선호한다.", answer = Answer.O, explanation = "F 유형은 문제 해결에서 감정적 접근을 선호합니다. 사람들의 감정과 관계를 중시합니다."),
                QuizQuestion(question = "T 유형은 사실과 데이터를 신뢰한다.", answer = Answer.O, explanation = "T 유형은 사실과 데이터를 신뢰합니다. 객관적인 정보를 중시합니다."),
                QuizQuestion(question = "F 유형은 사람들의 감정과 의견을 신뢰한다.", answer = Answer.O, explanation = "F 유형은 사람들의 감정과 의견을 신뢰합니다. 인간관계와 감정을 중요하게 생각합니다."),
                QuizQuestion(question = "T 유형은 객관적인 기준을 중시한다.", answer = Answer.O, explanation = "T 유형은 객관적인 기준을 중시합니다. 결정을 내릴 때 객관적인 정보를 중요하게 생각합니다."),
                QuizQuestion(question = "F 유형은 주관적인 감정을 중시한다.", answer = Answer.O, explanation = "F 유형은 주관적인 감정을 중시합니다. 사람들의 감정과 관계를 중요하게 생각합니다."),
                QuizQuestion(question = "내향형(I)은 혼자 있는 시간을 통해 에너지를 충전한다.", answer = Answer.O, explanation = "내향형(I)은 혼자 있는 시간을 통해 에너지를 충전합니다.")
            )
            quizQuestionRepository.saveAll(quizQuestions)
        }
    }
}
