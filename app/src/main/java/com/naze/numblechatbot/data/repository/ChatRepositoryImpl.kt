package com.naze.numblechatbot.data.repository

import android.content.Context
import android.util.Log
import com.google.gson.JsonSyntaxException
import com.naze.numblechatbot.data.local.dao.ChatDao
import com.naze.numblechatbot.data.local.model.Chat
import com.naze.numblechatbot.data.local.model.ChatResponse
import com.naze.numblechatbot.data.local.model.ChatType
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
    override suspend fun getChat(): List<Chat> {
        return chatDao.getAll()
    }

    override suspend fun insertChat(chat: Chat){
        //Room 에 데이터 넣기
        chatDao.insert(chat)
    }

    override suspend fun question(question: String): ChatResponse {
        //API 에 전송
        var response: ChatResponse
        coroutineScope {
            try {
                response = ChatResponse(api.question(
                    RequestGPT(
                        prompt = question,
                        maxTokens = 3900,
                        model = "text-davinci-003",
                        temperature = null,
                        frequencyPenalty = null
                    )
                ).choices[0].text, ChatType.ANSWER)
            } catch (e: HttpException) {
                response = ChatResponse(e.message(), ChatType.ERROR)
                Log.e("ChatRepositoryImpl", "HttpException ${e.message()}")
            } catch (e: JsonSyntaxException) {
                response = if (e.message.isNullOrEmpty()) ChatResponse("오류", ChatType.ERROR)
                else ChatResponse(e.message.toString(), ChatType.ERROR)
                Log.e("ChatRepositoryImpl", "JsonSyntaxException ${e.message}")
            } catch (e: IOException) {
                response = if (e.message.isNullOrEmpty()) ChatResponse("오류", ChatType.ERROR)
                else ChatResponse(e.message.toString(), ChatType.ERROR)
                Log.e("ChatRepositoryImpl", "IOException ${e.message}")
            } catch (e: Exception) {
                response = if (e.message.isNullOrEmpty()) ChatResponse("오류", ChatType.ERROR)
                else ChatResponse(e.message.toString(), ChatType.ERROR)
                Log.e("ChatRepositoryImpl", "Exception ${e.message}")
            }
        }
        return response
    }
}