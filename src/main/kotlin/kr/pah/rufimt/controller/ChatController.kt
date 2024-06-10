package kr.pah.rufimt.controller

import kr.pah.rufimt.dto.chat.ChatMessageDto
import kr.pah.rufimt.dto.chat.ChatRoomDto
import kr.pah.rufimt.service.ChatService
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/chat")
class ChatController(
    private val chatService: ChatService
) {

    @PostMapping("/queue")
    fun joinQueue(@RequestParam user: String): ChatRoomDto? {
        val chatRoom = chatService.joinQueue(user)
        return chatRoom?.let { ChatRoomDto(it.id, it.name) }
    }

    @MessageMapping("/sendMessage")
    @SendTo("/topic/messages")
    fun sendMessage(messageDto: ChatMessageDto): ChatMessageDto {
        return messageDto
    }
}
