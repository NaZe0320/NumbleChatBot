package com.naze.numblechatbot.domain.viewmodel

import androidx.lifecycle.ViewModel
import com.naze.numblechatbot.domain.repository.ChatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val chatRepository: ChatRepository
): ViewModel() {

}