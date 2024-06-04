package kr.pah.rufimt.controller

import kr.pah.rufimt.dto.point.*
import kr.pah.rufimt.service.PointProductService
import kr.pah.rufimt.service.UserService
import kr.pah.rufimt.util.Result
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/point-products")
class PointProductController(
    private val pointProductService: PointProductService,
    private val userService: UserService
) {

    @GetMapping
    fun getAllProducts(): ResponseEntity<Map<String, Any>> {
        val products = pointProductService.getAllProducts()
        return Result.ok(products)
    }

    @PostMapping("/purchase")
    fun purchaseProduct(
        @AuthenticationPrincipal userDetails: UserDetails,
        @RequestBody requestDto: PurchasePointRequestDto
    ): ResponseEntity<Map<String, Any>> {
        val user = userService.findByUsername(userDetails.username)
        return if (user != null) {
            val points = pointProductService.purchaseProduct(user.id, requestDto)
            Result.ok(points)
        } else {
            Result.notFound("사용자를 찾을 수 없습니다.")
        }
    }
}
