package kr.pah.rufimt.controller

import kr.pah.rufimt.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class UserController(private val userService: UserService) {

    @PostMapping("/register")
    fun register(@RequestBody userDto: UserDto): ResponseEntity<Any> {
        val user = userService.registerUser(userDto.username, userDto.password)
        return ResponseEntity.ok(user)
    }

    @GetMapping("/login")
    fun login(): ResponseEntity<Any> {
        return ResponseEntity.ok("Logged in successfully")
    }
}

data class UserDto(val username: String, val password: String)
