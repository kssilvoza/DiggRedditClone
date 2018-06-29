package com.silvozatechnologies.diggredditclone.di.module

import com.silvozatechnologies.diggredditclone.data.repository.TopicRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun provideTopicRepository() : TopicRepository {
        return TopicRepository()
    }
}