package com.naze.numblechatbot.domain.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.naze.numblechatbot.data.local.model.Chat
import com.naze.numblechatbot.data.local.model.ChatType
import com.naze.numblechatbot.domain.repository.ChatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val chatRepository: ChatRepository
): ViewModel() {
    private val _chat = MutableStateFlow<List<Chat>>(emptyList())
    val chat: StateFlow<List<Chat>> = _chat.asStateFlow()

    /*init {
        viewModelScope.launch {
            chatRepository.getChat().collectLatest() {
                _chat.value = it
            }
        }
    }*/

    fun addQuestion(question: String) {
        _chat.value = _chat.value + Chat(question, ChatType.QUESTION)
    }
}