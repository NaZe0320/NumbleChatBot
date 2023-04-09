package com.naze.numblechatbot.domain.repository

import com.naze.numblechatbot.data.local.model.Chat
import kotlinx.coroutines.flow.Flow


interface ChatRepository {
    suspend fun getChat(): Flow<List<Chat>>
    suspend fun insertChat(chat: Chat): String
}