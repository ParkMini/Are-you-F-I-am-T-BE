package kr.pah.rufimt.dto.user

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import kr.pah.rufimt.entity.MbtiType
import java.time.LocalDate

data class UserDto(
    @field:NotBlank(message = "아이디는 필수 항목입니다.")
    @field:Size(min = 3, max = 20, message = "아이디는 3자 이상 20자 이하이어야 합니다.")
    val username: String,

    @field:NotBlank(message = "비밀번호는 필수 항목입니다.")
    @field:Size(min = 6, max = 100, message = "비밀번호는 6자 이상 100자 이하이어야 합니다.")
    val password: String,

    @field:NotBlank(message = "이름은 필수 항목입니다.")
    val realName: String,

    @field:NotBlank(message = "이메일은 필수 항목입니다.")
    @field:Email(message = "유효한 이메일 형식이 아닙니다.")
    val email: String,

    @field:NotBlank(message = "전화번호는 필수 항목입니다.")
    val phoneNumber: String,

    @field:NotBlank(message = "생년월일은 필수 항목입니다.")
    val birthDate: LocalDate,

    @field:NotBlank(message = "성별은 필수 항목입니다.")
    val gender: String,

    @field:NotBlank(message = "MBTI는 필수 항목입니다.")
    val mbti: MbtiType,

    val role: String = "USER"
)
