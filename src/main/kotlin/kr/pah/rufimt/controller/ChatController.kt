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
        return chatRoom?.let {
            ChatRoomDto(
                id = it.id,
                name = it.name,
                user1 = it.user1,
                user2 = it.user2,
                createdAt = it.createdAt.toString()
            )
        }
    }

    @MessageMapping("/sendMessage")
    @SendTo("/topic/messages")
    fun sendMessage(messageDto: ChatMessageDto): ChatMessageDto {
        chatService.saveMessage(messageDto.roomId, messageDto.sender, messageDto.content)
        return messageDto
    }
}
