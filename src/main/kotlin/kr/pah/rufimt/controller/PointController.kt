package kr.pah.rufimt.controller

import kr.pah.rufimt.dto.point.*
import kr.pah.rufimt.service.PointService
import kr.pah.rufimt.service.UserService
import kr.pah.rufimt.util.Result
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/points")
class PointController(
    private val pointService: PointService,
    private val userService: UserService
) {

    @GetMapping
    fun getUserPoints(@AuthenticationPrincipal userDetails: UserDetails): ResponseEntity<Map<String, Any>> {
        val user = userService.findByUsername(userDetails.username)
        return if (user != null) {
            val points = pointService.getUserPoints(user.id)
            Result.ok(points)
        } else {
            Result.notFound("사용자를 찾을 수 없습니다.")
        }
    }

    @PostMapping("/charge")
    fun chargePoints(
        @AuthenticationPrincipal userDetails: UserDetails,
        @RequestBody requestDto: PointRequestDto
    ): ResponseEntity<Map<String, Any>> {
        val user = userService.findByUsername(userDetails.username)
        return if (user != null) {
            val points = pointService.chargePoints(user.id, requestDto)
            Result.ok(points)
        } else {
            Result.notFound("사용자를 찾을 수 없습니다.")
        }
    }

    @PostMapping("/watch-ad")
    fun watchAd(@AuthenticationPrincipal userDetails: UserDetails): ResponseEntity<Map<String, Any>> {
        val user = userService.findByUsername(userDetails.username)
        return if (user != null) {
            try {
                val adWatchResponse = pointService.watchAd(user.id)
                Result.ok(adWatchResponse)
            } catch (e: IllegalStateException) {
                Result.tooManyRequests(e.message ?: "일일 광고 시청 횟수를 초과하였습니다.")
            }
        } else {
            Result.notFound("사용자를 찾을 수 없습니다.")
        }
    }

    @PostMapping("/check-in")
    fun checkIn(@AuthenticationPrincipal userDetails: UserDetails): ResponseEntity<Map<String, Any>> {
        val user = userService.findByUsername(userDetails.username)
        return if (user != null) {
            try {
                val checkInResponse = pointService.checkIn(user.id)
                Result.ok(checkInResponse)
            } catch (e: IllegalStateException) {
                Result.badRequest(e.message ?: "이미 오늘 출석체크를 하였습니다.")
            }
        } else {
            Result.notFound("사용자를 찾을 수 없습니다.")
        }
    }
}
