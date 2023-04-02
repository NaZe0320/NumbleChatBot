package com.naze.numblechatbot.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "chat")
data class Chat(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    var message: String,
    var type: ChatType
)
