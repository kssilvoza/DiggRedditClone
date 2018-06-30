package com.silvozatechnologies.diggredditclone.di.component

import com.silvozatechnologies.diggredditclone.DiggRedditCloneApplication
import com.silvozatechnologies.diggredditclone.di.module.ActivityBuilder
import com.silvozatechnologies.diggredditclone.di.module.RepositoryModule
import com.silvozatechnologies.diggredditclone.di.module.ViewModelModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidInjectionModule::class,

    ActivityBuilder::class,
    RepositoryModule::class,
    ViewModelModule::class
])
interface ApplicationComponent : AndroidInjector<DiggRedditCloneApplication> {
    override fun inject(application: DiggRedditCloneApplication)
}