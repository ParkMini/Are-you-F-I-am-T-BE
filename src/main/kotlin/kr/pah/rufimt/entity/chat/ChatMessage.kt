package kr.pah.rufimt.entity.chat

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.time.LocalDateTime

@Entity
data class ChatMessage(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val roomId: Long,
    val sender: Long,
    val content: String,
    val timestamp: LocalDateTime = LocalDateTime.now()
)