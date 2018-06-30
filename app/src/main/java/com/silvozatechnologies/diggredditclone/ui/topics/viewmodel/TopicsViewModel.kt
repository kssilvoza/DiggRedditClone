package com.silvozatechnologies.diggredditclone.ui.topics.viewmodel

import android.arch.lifecycle.ViewModel
import com.silvozatechnologies.diggredditclone.data.repository.TopicRepository
import javax.inject.Inject

class TopicsViewModel @Inject constructor(private val topicsRepository: TopicRepository) : ViewModel() {

}