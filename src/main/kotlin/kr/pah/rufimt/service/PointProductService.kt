package kr.pah.rufimt.service

import kr.pah.rufimt.dto.point.*
import kr.pah.rufimt.entity.point.PointProduct
import kr.pah.rufimt.repository.PointProductRepository
import kr.pah.rufimt.repository.user.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PointProductService(
    private val pointProductRepository: PointProductRepository,
    private val userRepository: UserRepository
) {

    fun getAllProducts(): List<PointProductDto> {
        return pointProductRepository.findAll().map { product ->
            PointProductDto(
                id = product.id,
                name = product.name,
                price = product.price,
                points = product.points,
                bonusPercentage = product.bonusPercentage
            )
        }
    }

    @Transactional
    fun purchaseProduct(userId: Long, requestDto: PurchasePointRequestDto): PointResponseDto {
        val user = userRepository.findById(userId).orElseThrow { IllegalArgumentException("User not found") }
        val product = pointProductRepository.findById(requestDto.productId)
            .orElseThrow { IllegalArgumentException("Product not found") }

        val bonusPoints = (product.points * product.bonusPercentage / 100).toInt()
        user.points += product.points + bonusPoints
        userRepository.save(user)

        return PointResponseDto(userId = user.id, points = user.points)
    }
}
