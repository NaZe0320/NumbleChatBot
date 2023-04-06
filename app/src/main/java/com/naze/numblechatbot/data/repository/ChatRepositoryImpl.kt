package com.naze.numblechatbot.data.repository

import android.content.Context
import android.util.Log
import com.naze.numblechatbot.data.local.dao.ChatDao
import com.naze.numblechatbot.data.local.model.Chat
import com.naze.numblechatbot.data.remote.api.GptAPI
import com.naze.numblechatbot.data.remote.model.request.RequestGPT
import com.naze.numblechatbot.domain.repository.ChatRepository
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ChatRepositoryImpl @Inject constructor(
    private val chatDao: ChatDao,
    private val context: Context,
    private val api: GptAPI,
): ChatRepository {
    override suspend fun getChat(): Flow<List<Chat>> {
        TODO("Not yet implemented")
    }

    override suspend fun insertChat(chat: Chat) {
        //Room 에 데이터 넣기
        coroutineScope {
            val test: Any =
                api.question(
                    RequestGPT(prompt = chat.message,
                        maxTokens = 3900,
                        model = "text-davinci-003")
                )
            Log.d("GPT TEST", test.toString())
        }
    }
}