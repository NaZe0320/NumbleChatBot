package com.naze.numblechatbot.data.repository

import android.content.Context
import com.naze.numblechatbot.data.local.dao.ChatDao
import com.naze.numblechatbot.data.local.model.Chat
import com.naze.numblechatbot.domain.repository.ChatRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ChatRepositoryImpl @Inject constructor(
    private val chatDao: ChatDao,
    private val context: Context
): ChatRepository {
    override suspend fun getChat(): Flow<List<Chat>> {
        TODO("Not yet implemented")
    }

    override suspend fun insertChat(chat: Chat) {
        TODO("Not yet implemented")
    }
}