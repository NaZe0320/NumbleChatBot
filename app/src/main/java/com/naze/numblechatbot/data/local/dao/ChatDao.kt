package com.naze.numblechatbot.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.naze.numblechatbot.data.local.model.Chat

@Dao
interface ChatDao {
    @Insert
    suspend fun insert(chat:Chat)

    @Query("SELECT * FROM chat")
    suspend fun getAll(): List<Chat>

    @Query("DELETE FROM chat")
    suspend fun deleteAll()
}