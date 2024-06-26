package kr.pah.rufimt.entity.user

import jakarta.persistence.*
import java.time.LocalDate

@Entity
@Table(name = "users")
data class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false, unique = true)
    val username: String,

    @Column(nullable = false)
    val password: String,

    @Column(nullable = false)
    val role: String = "USER",

    @Column(nullable = false)
    val realName: String,

    @Column(nullable = false, unique = true)
    val email: String,

    @Column(nullable = false, unique = true)
    val phoneNumber: String,

    @Column(nullable = false)
    val birthDate: LocalDate,

    @Column(nullable = false)
    val gender: String,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val mbti: MbtiType,

    @Column(nullable = false)
    val registeredAt: LocalDate = LocalDate.now(),

    @Column(nullable = false)
    val isDeleted: Boolean = false,

    @Column(nullable = false)
    var points: Int = 0,

    @Column(nullable = false)
    var adWatchCount: Int = 0,

    @Column(nullable = true)
    var adWatchDate: LocalDate? = null,

    @Column(nullable = true)
    var lastCheckInDate: LocalDate? = null,

    @Column(nullable = false)
    var consecutiveDays: Int = 0
)
