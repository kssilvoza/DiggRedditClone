package com.silvozatechnologies.diggredditclone.di.component

import com.silvozatechnologies.diggredditclone.di.module.RepositoryModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    RepositoryModule::class
])
interface ApplicationComponent {
    
}