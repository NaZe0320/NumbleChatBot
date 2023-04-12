package com.naze.numblechatbot.domain.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.naze.numblechatbot.data.local.model.Chat
import com.naze.numblechatbot.data.local.model.ChatType
import com.naze.numblechatbot.domain.model.ChatShare
import com.naze.numblechatbot.domain.repository.ChatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val chatRepository: ChatRepository
): ViewModel() {
    private val _chat = MutableLiveData<List<Chat>>()
    val chat: LiveData<List<Chat>> get() = _chat

    private val _chatShare = MutableLiveData<List<ChatShare>?>()
    val chatShare: MutableLiveData<List<ChatShare>?> get() = _chatShare

    var temperature: Double = 0.0
    var frequencyPenalty: Double = 0.0

    fun setTemperatureValue(t: Double) {
        temperature = t
    }
    fun setFrequencyPenaltyValue(f: Double) {
        frequencyPenalty = f
    }


    fun question(question: String) {
        viewModelScope.launch {
            setChat(question, ChatType.QUESTION)
            chatRepository.insertChat(
                Chat(
                    message = question,
                    type = ChatType.QUESTION,
                    time = Calendar.getInstance().timeInMillis
                )
            )
        }
        viewModelScope.launch {
            val response = chatRepository.question(question, temperature, frequencyPenalty)
            val str = response.chat.trimMargin()
            setChat(str, response.chatType)
            chatRepository.insertChat(
                Chat(
                    message = str,
                    type = response.chatType,
                    time = Calendar.getInstance().timeInMillis
                )
            )
        }
    }

    private fun setChat(message: String, type: ChatType) {
        val chatList = _chat.value?.toMutableList() ?: mutableListOf()
        chatList.add(
            Chat(
                message = message,
                type = type,
                time = Calendar.getInstance().timeInMillis
            )
        )
        _chat.value = chatList
    }

    fun getChat() {
        viewModelScope.launch {
            _chat.value = chatRepository.getChat()
        }

    }

    fun deleteAllChat() {
        viewModelScope.launch {
            chatRepository.deleteAll()
        }
    }

    fun chatToShare() {
        val chatShareListValue = _chat.value?.map { ChatShare(false, it.message, it.type, it.id) }
        _chatShare.value = chatShareListValue
        Log.d("TEST","Chat : ${_chatShare.value}")
    }
}