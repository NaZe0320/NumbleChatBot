package com.naze.numblechatbot.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.naze.numblechatbot.data.local.model.Setting

@Dao
interface SettingDao {
    @Insert
    suspend fun insert(setting: Setting)

    @Update
    suspend fun update(setting: Setting)

    @Query("SELECT * FROM setting")
    fun getSetting(): List<Setting>
}