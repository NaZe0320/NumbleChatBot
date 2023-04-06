package com.naze.numblechatbot.data.repository

import android.content.Context
import android.util.Log
import com.google.gson.JsonSyntaxException
import com.naze.numblechatbot.data.local.dao.ChatDao
import com.naze.numblechatbot.data.local.model.Chat
import com.naze.numblechatbot.data.remote.api.GptAPI
import com.naze.numblechatbot.data.remote.model.request.RequestGPT
import com.naze.numblechatbot.domain.repository.ChatRepository
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import retrofit2.HttpException
import java.io.IOException
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
            try {
                return@coroutineScope api.question(
                    RequestGPT(
                        prompt = chat.message,
                        maxTokens = 3900,
                        model = "text-davinci-003"
                    )
                )
            } catch (e: HttpException) {
                Log.e("ChatRepositoryImpl", "HttpException ${e.message()}")
            } catch (e: JsonSyntaxException) {
                Log.e("ChatRepositoryImpl", "JsonSyntaxException ${e.message}")
            } catch (e: IOException) {
                Log.e("ChatRepositoryImpl", "IOException ${e.message}")
            } catch (e: Exception) {
                Log.e("ChatRepositoryImpl", "Exception ${e.message}")
            }
        }

    }
}