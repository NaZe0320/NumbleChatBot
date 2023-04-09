package com.naze.numblechatbot.data.repository

import android.content.Context
import android.util.Log
import com.google.gson.JsonSyntaxException
import com.naze.numblechatbot.data.local.dao.ChatDao
import com.naze.numblechatbot.data.local.model.Chat
import com.naze.numblechatbot.data.remote.api.GptAPI
import com.naze.numblechatbot.data.remote.model.request.RequestGPT
import com.naze.numblechatbot.data.remote.model.response.ResponseGPT
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

    override suspend fun insertChat(chat: Chat) :String{
        //Room 에 데이터 넣기
        var response: String
        coroutineScope {
            try {
                response = api.question(
                    RequestGPT(
                        prompt = chat.message,
                        maxTokens = 3900,
                        model = "text-davinci-003",
                        temperature = null,
                        frequencyPenalty = null
                    )
                ).choices[0].text
            } catch (e: HttpException) {
                response = e.message()
                Log.e("ChatRepositoryImpl", "HttpException ${e.message()}")
            } catch (e: JsonSyntaxException) {
                response = if (e.message.isNullOrEmpty()) "오류" else e.message.toString()
                Log.e("ChatRepositoryImpl", "JsonSyntaxException ${e.message}")
            } catch (e: IOException) {
                response = if (e.message.isNullOrEmpty()) "오류" else e.message.toString()
                Log.e("ChatRepositoryImpl", "IOException ${e.message}")
            } catch (e: Exception) {
                response = if (e.message.isNullOrEmpty()) "오류" else e.message.toString()
                Log.e("ChatRepositoryImpl", "Exception ${e.message}")
            }
        }
        return response
    }
}