package com.naze.numblechatbot.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.naze.numblechatbot.data.local.model.SettingModel

@Dao
interface SettingDao {
    @Insert
    suspend fun insert(setting: SettingModel)

    @Update
    suspend fun update(setting: SettingModel)

    @Query("SELECT * FROM setting")
    fun getSetting(): List<SettingModel>
}