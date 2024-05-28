package kr.pah.rufimt.controller

import kr.pah.rufimt.dto.user.UserDto
import kr.pah.rufimt.service.UserService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler

@RestController
@RequestMapping("/api")
class UserController(private val userService: UserService) {

    @PostMapping("/register")
    fun register(@RequestBody @Valid userDto: UserDto): ResponseEntity<Any> {
        if (userService.findByUsername(userDto.username) != null) {
            return ResponseEntity.badRequest().body("이미 가입된 아이디입니다.")
        }

        userService.registerUser(userDto.username, userDto.password)
        return ResponseEntity.ok("정상적으로 가입되었습니다.")
    }

    @GetMapping("/userInfo")
    fun userInfo(): ResponseEntity<Any> {
        val authentication: Authentication = SecurityContextHolder.getContext().authentication
        val username = authentication.name
        val user = userService.findByUsername(username)
        return if (user != null) {
            ResponseEntity.ok(user)
        } else {
            ResponseEntity.status(404).body("사용자를 찾을 수 없습니다.")
        }
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationExceptions(ex: MethodArgumentNotValidException): ResponseEntity<String> {
        val errors = ex.bindingResult.allErrors.joinToString("; ") { error -> error.defaultMessage ?: "잘못된 입력입니다." }
        return ResponseEntity.badRequest().body(errors)
    }
}
