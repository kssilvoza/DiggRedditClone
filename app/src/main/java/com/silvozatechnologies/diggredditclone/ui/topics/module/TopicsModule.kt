package com.silvozatechnologies.diggredditclone.ui.topics.module

import android.arch.lifecycle.ViewModel
import com.silvozatechnologies.diggredditclone.di.key.ViewModelKey
import com.silvozatechnologies.diggredditclone.ui.topics.viewmodel.TopicsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/*
 * Topics activity wide module. Anything that should live within the scope of the TopicsActivity
 * goes here. Thus, TopicsViewModel should be here
 */
@Module
abstract class TopicsModule {
    @Binds
    @IntoMap
    @ViewModelKey(TopicsViewModel::class)
    abstract fun bindTopicsViewModel(viewModel: TopicsViewModel) : ViewModel
}