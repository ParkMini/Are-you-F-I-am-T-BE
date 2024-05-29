package kr.pah.rufimt.service

import kr.pah.rufimt.dto.user.UserDto
import kr.pah.rufimt.entity.MbtiType
import kr.pah.rufimt.entity.User
import kr.pah.rufimt.repository.UserRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {
    private val passwordEncoder = BCryptPasswordEncoder()

    fun registerUser(userDto: UserDto): User {
        val encodedPassword = passwordEncoder.encode(userDto.password)
        val user = User(
            username = userDto.username,
            password = encodedPassword,
            realName = userDto.realName,
            email = userDto.email,
            phoneNumber = userDto.phoneNumber,
            birthDate = userDto.birthDate,
            gender = userDto.gender,
            mbti = userDto.mbti,
            role = if (userDto.role == "ADMIN") "ADMIN" else "USER"
        )
        return userRepository.save(user)
    }

    fun findByUsername(username: String): User? {
        return userRepository.findByUsername(username)
    }
}
