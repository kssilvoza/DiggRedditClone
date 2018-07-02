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

/*
 * ViewModel for the TopicsActivity. Business logic for topics display and adding goes here
 */
class TopicsViewModel @Inject constructor(private val topicsRepository: TopicRepository) : ViewModel() {
    val TOPIC_NAME_MAX_LENGTH = 255
    val TOPICS_MAX_COUNT = 20

    var topics = MutableLiveData<List<Topic>>()

    private var disposable: Disposable

    init {
        disposable = topicsRepository.topicsObservable
                .map {
                    // This takes the 20 most upvoted topics from the repository to be displayed in the Activity/View
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
        // This limits the topic name to 255 characters just in case the character limit in the View does not work properly
        if (topicName.length <= TOPIC_NAME_MAX_LENGTH) {
            topicsRepository.addTopic(topicName)
        }
    }

    fun upvoteTopic(topicId: String) {
        topicsRepository.upvoteTopic(topicId)
    }

    fun downvoteTopic(topicId: String) {
        topicsRepository.downvoteTopic(topicId)
    }

    private fun onTopicsChanged(topics: List<Topic>) {
        this.topics.value = topics
    }
}