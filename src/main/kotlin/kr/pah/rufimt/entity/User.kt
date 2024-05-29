package kr.pah.rufimt.entity

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
    val isDeleted: Boolean = false
)
