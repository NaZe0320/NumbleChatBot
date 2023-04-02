package com.naze.numblechatbot.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.naze.numblechatbot.data.local.dao.SettingDao
import com.naze.numblechatbot.data.local.model.Setting

@Database(entities = [Setting::class], version = 1)
abstract class SettingDatabase: RoomDatabase(){
    abstract fun settingDao() : SettingDao
}