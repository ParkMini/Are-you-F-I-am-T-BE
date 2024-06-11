package kr.pah.rufimt.service

import kr.pah.rufimt.entity.chat.ChatMessage
import kr.pah.rufimt.entity.chat.ChatRoom
import kr.pah.rufimt.repository.chat.ChatMessageRepository
import kr.pah.rufimt.repository.chat.ChatRoomRepository
import org.springframework.stereotype.Service
import java.util.concurrent.ConcurrentLinkedQueue

@Service
class ChatService(
    private val chatRoomRepository: ChatRoomRepository,
    private val chatMessageRepository: ChatMessageRepository
) {
    private val waitingQueue = ConcurrentLinkedQueue<String>()

    fun joinQueue(user: String): ChatRoom? {
        waitingQueue.add(user)
        if (waitingQueue.size >= 2) {
            val user1 = waitingQueue.poll()
            val user2 = waitingQueue.poll()
            return createChatRoom(user1, user2)
        }
        return null
    }

    fun createChatRoom(user1: String, user2: String): ChatRoom {
        val chatRoom = ChatRoom(name = "$user1-$user2", user1 = user1, user2 = user2)
        return chatRoomRepository.save(chatRoom)
    }

    fun saveMessage(roomId: Long, sender: String, content: String): ChatMessage {
        val chatMessage = ChatMessage(roomId = roomId, sender = sender, content = content)
        return chatMessageRepository.save(chatMessage)
    }
}
