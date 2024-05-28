package kr.pah.rufimt.dto.user

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class UserDto(
    @field:NotBlank(message = "아이디는 필수 항목입니다.")
    @field:Size(min = 3, max = 20, message = "아이디는 3자 이상 20자 이하이어야 합니다.")
    val username: String,

    @field:NotBlank(message = "비밀번호는 필수 항목입니다.")
    @field:Size(min = 6, max = 100, message = "비밀번호는 6자 이상 100자 이하이어야 합니다.")
    val password: String
)
