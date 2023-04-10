package com.naze.numblechatbot.domain.repository

import com.naze.numblechatbot.data.local.model.Chat
import com.naze.numblechatbot.data.local.model.ChatResponse
import kotlinx.coroutines.flow.Flow


interface ChatRepository {
    suspend fun getChat(): List<Chat>
    suspend fun insertChat(chat: Chat)
    suspend fun question(question: String, temperature: Double, frequencyPenalty: Double): ChatResponse
    suspend fun deleteAll()
}