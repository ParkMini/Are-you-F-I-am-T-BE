package kr.pah.rufimt.service

import kr.pah.rufimt.entity.chat.ChatRoom
import kr.pah.rufimt.repository.chat.ChatRoomRepository
import org.springframework.stereotype.Service
import java.util.concurrent.ConcurrentLinkedQueue

@Service
class ChatService(
    private val chatRoomRepository: ChatRoomRepository
) {
    private val waitingQueue = ConcurrentLinkedQueue<String>()

    fun joinQueue(user: String): ChatRoom? {
        waitingQueue.add(user)
        if (waitingQueue.size >= 2) {
            val user1 = waitingQueue.poll()
            val user2 = waitingQueue.poll()
            val chatRoom = createChatRoom("$user1-$user2")
            return chatRoom
        }
        return null
    }

    fun createChatRoom(name: String): ChatRoom {
        val chatRoom = ChatRoom(name = name)
        return chatRoomRepository.save(chatRoom)
    }
}
