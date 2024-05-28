package kr.pah.rufimt.controller

import kr.pah.rufimt.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class UserController(private val userService: UserService) {

    @GetMapping("/userInfo")
    fun userInfo(): ResponseEntity<Any> {
        val authentication: Authentication = SecurityContextHolder.getContext().authentication
        val username = authentication.name
        val user = userService.findByUsername(username)
        return if (user != null) {
            ResponseEntity.ok(user)
        } else {
            ResponseEntity.status(404).body("User not found")
        }
    }
}

data class UserDto(val username: String, val password: String)
