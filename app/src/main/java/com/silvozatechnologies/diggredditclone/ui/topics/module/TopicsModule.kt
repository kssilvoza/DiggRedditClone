package com.silvozatechnologies.diggredditclone.ui.topics.module

import android.arch.lifecycle.ViewModel
import com.silvozatechnologies.diggredditclone.di.key.ViewModelKey
import com.silvozatechnologies.diggredditclone.ui.topics.viewmodel.TopicsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class TopicsModule {
    @Binds
    @IntoMap
    @ViewModelKey(TopicsViewModel::class)
    abstract fun bindTopicsViewModel(viewModel: TopicsViewModel) : ViewModel
}