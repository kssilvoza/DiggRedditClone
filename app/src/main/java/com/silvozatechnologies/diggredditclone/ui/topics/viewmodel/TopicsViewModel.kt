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
    val TOPIC_NAME_MAX_LENGTH = 255
    val TOPICS_MAX_COUNT = 20

    var topics = MutableLiveData<List<Topic>>()

    private var disposable: Disposable

    init {
        disposable = topicsRepository.observeTopics()
                .map {
                    it.take(TOPICS_MAX_COUNT)
                }
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(this::onTopicsChanged)
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
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