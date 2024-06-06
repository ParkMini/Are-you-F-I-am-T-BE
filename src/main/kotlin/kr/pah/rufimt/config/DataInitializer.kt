package kr.pah.rufimt.config

import kr.pah.rufimt.entity.point.PointProduct
import kr.pah.rufimt.repository.PointProductRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.transaction.annotation.Transactional

@Configuration
class DataInitializer(private val pointProductRepository: PointProductRepository) {

    @Bean
    @Transactional
    fun initializeData() {
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
}
