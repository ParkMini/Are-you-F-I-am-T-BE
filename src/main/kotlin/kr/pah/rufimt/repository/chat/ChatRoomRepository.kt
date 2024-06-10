package kr.pah.rufimt.repository.chat

import kr.pah.rufimt.entity.chat.ChatRoom
import org.springframework.data.jpa.repository.JpaRepository

interface ChatRoomRepository : JpaRepository<ChatRoom, Long>
