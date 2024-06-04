package kr.pah.rufimt.repository

import kr.pah.rufimt.entity.point.PointProduct
import org.springframework.data.jpa.repository.JpaRepository

interface PointProductRepository : JpaRepository<PointProduct, Long>
