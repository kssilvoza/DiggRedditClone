package com.silvozatechnologies.diggredditclone.di.component

import com.silvozatechnologies.diggredditclone.DiggRedditCloneApplication
import com.silvozatechnologies.diggredditclone.di.module.ActivityBuilder
import com.silvozatechnologies.diggredditclone.di.module.RepositoryModule
import com.silvozatechnologies.diggredditclone.di.module.ViewModelModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/*
 * Main component of the application. Keeps track of modules that hold app wide objects.
 * Examples of these objects include singletons like Repositories, APIs, and DAOs
 */
@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,

    ActivityBuilder::class,
    RepositoryModule::class,
    ViewModelModule::class
])
interface ApplicationComponent : AndroidInjector<DiggRedditCloneApplication> {
    override fun inject(application: DiggRedditCloneApplication)
}