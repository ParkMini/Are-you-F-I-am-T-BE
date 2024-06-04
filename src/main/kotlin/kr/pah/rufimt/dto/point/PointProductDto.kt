package kr.pah.rufimt.dto.point

data class PointProductDto(
    val id: Long,
    val name: String,
    val price: Int,
    val points: Int,
    val bonusPercentage: Double
)