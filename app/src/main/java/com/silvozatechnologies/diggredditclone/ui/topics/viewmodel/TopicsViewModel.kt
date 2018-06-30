package com.silvozatechnologies.diggredditclone.ui.topics.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.silvozatechnologies.diggredditclone.data.model.Topic
import com.silvozatechnologies.diggredditclone.data.repository.TopicRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class TopicsViewModel @Inject constructor(private val topicsRepository: TopicRepository) : ViewModel() {
    var topics = MutableLiveData<List<Topic>>()

    private var disposable: Disposable

    init {
        disposable = topicsRepository.topicsObservable
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onTopicsChanged)
    }

    fun addTopic(topicName: String) {
        topicsRepository.addTopic(topicName)
    }

    fun upvoteTopic(topic: Topic) {
        topicsRepository.upvoteTopic(topic.id)
    }

    fun downvoteTopic(topic: Topic) {
        topicsRepository.downvoteTopic(topic.id)
    }

    private fun onTopicsChanged(topics: List<Topic>) {
        this.topics.value = topics
    }
}