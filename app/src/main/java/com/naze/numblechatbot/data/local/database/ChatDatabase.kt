package com.naze.numblechatbot.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.naze.numblechatbot.data.local.dao.ChatDao
import com.naze.numblechatbot.data.local.model.Chat

@Database(entities = [Chat::class], version = 1)
abstract class ChatDatabase: RoomDatabase() {
    abstract fun chatDao() : ChatDao
}