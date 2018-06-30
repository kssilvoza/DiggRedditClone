package com.silvozatechnologies.diggredditclone.di.module

import com.silvozatechnologies.diggredditclone.ui.topics.module.TopicsModule
import com.silvozatechnologies.diggredditclone.ui.topics.module.TopicsScope
import com.silvozatechnologies.diggredditclone.ui.topics.view.TopicsActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {
    @TopicsScope
    @ContributesAndroidInjector(modules = [TopicsModule::class])
    abstract fun contributeTopicsActivityInjector() : TopicsActivity
}