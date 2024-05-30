package kr.pah.rufimt.repository.user

import kr.pah.rufimt.entity.user.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
    fun findByUsername(username: String): User?
    fun findByEmail(email: String): User?
    fun findByPhoneNumber(phoneNumber: String): User?
}
