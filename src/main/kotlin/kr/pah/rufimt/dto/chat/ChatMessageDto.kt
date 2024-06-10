package kr.pah.rufimt.dto.chat

data class ChatMessageDto(
    val roomId: Long,
    val sender: String,
    val content: String
)
