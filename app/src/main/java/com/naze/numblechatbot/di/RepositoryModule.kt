package com.naze.numblechatbot.di

import android.content.Context
import com.naze.numblechatbot.data.local.dao.ChatDao
import com.naze.numblechatbot.data.repository.ChatRepositoryImpl
import com.naze.numblechatbot.domain.repository.ChatRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    fun provideChatRepository(chatDao: ChatDao, @ApplicationContext context: Context): ChatRepository = ChatRepositoryImpl(chatDao, context)
}