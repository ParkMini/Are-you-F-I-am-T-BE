package kr.pah.rufimt.dto.point

data class CheckInResponseDto(
    val userId: Long,
    val points: Int,
    val consecutiveDays: Int
)