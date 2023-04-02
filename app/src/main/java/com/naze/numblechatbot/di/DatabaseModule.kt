package com.naze.numblechatbot.di

import android.content.Context
import androidx.room.Room
import com.naze.numblechatbot.data.local.dao.ChatDao
import com.naze.numblechatbot.data.local.dao.SettingDao
import com.naze.numblechatbot.data.local.database.ChatDatabase
import com.naze.numblechatbot.data.local.database.SettingDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Singleton
    @Provides
    fun provideChatDatabase(@ApplicationContext context: Context): ChatDatabase =
        Room.databaseBuilder(context, ChatDatabase::class.java, "chat_database").build()

    @Singleton
    @Provides
    fun provideSettingDatabase(@ApplicationContext context: Context): SettingDatabase =
        Room.databaseBuilder(context, SettingDatabase::class.java, "setting_database").build()

    @Provides
    fun provideChatDao(database: ChatDatabase): ChatDao = database.chatDao()

    @Provides
    fun provideSettingDao(database: SettingDatabase): SettingDao = database.settingDao()
}