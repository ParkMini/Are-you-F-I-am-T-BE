package kr.pah.rufimt.service

import kr.pah.rufimt.dto.point.*
import kr.pah.rufimt.entity.user.User
import kr.pah.rufimt.repository.user.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

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

    @Transactional
    fun watchAd(userId: Long): AdWatchResponseDto {
        val user = userRepository.findById(userId).orElseThrow { IllegalArgumentException("User not found") }
        if (user.adWatchDate != LocalDate.now()) {
            user.adWatchDate = LocalDate.now()
            user.adWatchCount = 0
        }

        if (user.adWatchCount >= 10) {
            throw IllegalStateException("일일 광고 시청 횟수를 초과하였습니다.")
        }

        user.adWatchCount += 1
        user.points += 10 // 광고 시청 시 10 포인트 지급
        userRepository.save(user)
        return AdWatchResponseDto(user.id, user.points, user.adWatchCount)
    }

    @Transactional
    fun checkIn(userId: Long): CheckInResponseDto {
        val user = userRepository.findById(userId).orElseThrow { IllegalArgumentException("User not found") }
        val today = LocalDate.now()

        if (user.lastCheckInDate == today) {
            throw IllegalStateException("이미 오늘 출석체크를 하였습니다.")
        }

        if (user.lastCheckInDate == today.minusDays(1)) {
            user.consecutiveDays += 1
        } else {
            user.consecutiveDays = 1
        }

        user.lastCheckInDate = today
        user.points += 5 // 출석체크 시 5 포인트 지급

        if (user.consecutiveDays == 7) {
            user.points += 20 // 7일 연속 출석 시 추가 20 포인트 지급
        }

        userRepository.save(user)

        return CheckInResponseDto(user.id, user.points, user.consecutiveDays)
    }
}
