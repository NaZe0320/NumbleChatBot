package com.naze.numblechatbot.domain.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.naze.numblechatbot.data.local.model.Chat
import com.naze.numblechatbot.data.local.model.ChatType
import com.naze.numblechatbot.domain.repository.ChatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val chatRepository: ChatRepository
): ViewModel() {
    private val _chat = MutableLiveData<List<Chat>>()
    val chat: LiveData<List<Chat>> get() = _chat

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
            val response = chatRepository.question(question)
            setChat(response.chat, response.chatType)
            chatRepository.insertChat(
                Chat(
                    message = response.chat,
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
}