package kr.pah.rufimt.service

import kr.pah.rufimt.dto.point.PointRequestDto
import kr.pah.rufimt.dto.point.PointResponseDto
import kr.pah.rufimt.entity.user.User
import kr.pah.rufimt.repository.user.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PointService(
    private val userRepository: UserRepository
) {

    fun getUserPoints(userId: Long): PointResponseDto {
        val user = userRepository.findById(userId).orElseThrow { IllegalArgumentException("User not found") }
        return PointResponseDto(userId = user.id, points = user.points)
    }

    @Transactional
    fun chargePoints(userId: Long, requestDto: PointRequestDto): PointResponseDto {
        val user = userRepository.findById(userId).orElseThrow { IllegalArgumentException("User not found") }
        user.points += requestDto.amount
        userRepository.save(user)
        return PointResponseDto(userId = user.id, points = user.points)
    }
}
