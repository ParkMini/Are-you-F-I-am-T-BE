package kr.pah.rufimt.entity.point

import jakarta.persistence.*

@Entity
@Table(name = "point_products")
data class PointProduct(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    val name: String,

    @Column(nullable = false)
    val price: Int,

    @Column(nullable = false)
    val points: Int,

    @Column(nullable = false)
    val bonusPercentage: Double
)
