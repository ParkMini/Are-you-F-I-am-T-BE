package kr.pah.rufimt.service

import kr.pah.rufimt.entity.User
import kr.pah.rufimt.entity.MbtiType
import kr.pah.rufimt.repository.UserRepository
import kr.pah.rufimt.dto.user.UserDto
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {
    private val passwordEncoder = BCryptPasswordEncoder()

    fun registerUser(userDto: UserDto): User {
        if (userRepository.findByEmail(userDto.email) != null) {
            throw IllegalArgumentException("이미 가입된 이메일입니다.")
        }
        if (userRepository.findByPhoneNumber(userDto.phoneNumber) != null) {
            throw IllegalArgumentException("이미 가입된 전화번호입니다.")
        }

        val encodedPassword = passwordEncoder.encode(userDto.password)
        val mbtiType = MbtiType.valueOf(userDto.mbti)
        val user = User(
            username = userDto.username,
            password = encodedPassword,
            realName = userDto.realName,
            email = userDto.email,
            phoneNumber = userDto.phoneNumber,
            birthDate = userDto.birthDate,
            gender = userDto.gender,
            mbti = mbtiType,
            role = userDto.role
        )
        return userRepository.save(user)
    }

    fun findByUsername(username: String): User? {
        return userRepository.findByUsername(username)
    }
}
