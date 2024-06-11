package kr.pah.rufimt.repository.chat

import kr.pah.rufimt.entity.chat.ChatMessage
import org.springframework.data.jpa.repository.JpaRepository

interface ChatMessageRepository : JpaRepository<ChatMessage, Long>
