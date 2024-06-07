package kr.pah.rufimt.repository.point

import kr.pah.rufimt.entity.point.PointProduct
import org.springframework.data.jpa.repository.JpaRepository

interface PointProductRepository : JpaRepository<PointProduct, Long>
