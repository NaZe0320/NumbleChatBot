package com.naze.numblechatbot.domain.model

import com.naze.numblechatbot.data.local.model.ChatType

data class ChatShare(
    var checked: Boolean = false,
    var message: String,
    var type: ChatType,
)
