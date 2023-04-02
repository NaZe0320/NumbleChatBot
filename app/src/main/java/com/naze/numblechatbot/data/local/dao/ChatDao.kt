package com.naze.numblechatbot.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.naze.numblechatbot.data.local.model.ChatModel

@Dao
interface ChatDao {
    @Insert
    suspend fun insert(chat:ChatModel)

    @Query("SELECT * FROM chat")
    fun getAll(): LiveData<List<ChatModel>>
}