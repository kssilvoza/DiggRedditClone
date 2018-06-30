package com.silvozatechnologies.diggredditclone.data.repository

import android.util.Log
import com.silvozatechnologies.diggredditclone.common.utility.generateRandomAlphanumericString
import com.silvozatechnologies.diggredditclone.data.model.Topic
import io.reactivex.subjects.PublishSubject

class TopicRepository {
    val topicsObservable = PublishSubject.create<List<Topic>>()

    private val topicsMap = mutableMapOf<String, Topic>()
    private var topics = listOf<Topic>()

    fun addTopic(topicName: String) {
        val id = generateId()
        val lastUpdated = System.currentTimeMillis()
        val topic = Topic(id = id, topicName = topicName, votes = 0, lastUpdated = lastUpdated)
        topicsMap[id] = topic
        updateTopicsObservable()
    }

    fun upvoteTopic(id: String) {
        val topic = topicsMap[id]
        if (topic != null) {
            topic.votes = topic.votes + 1
            updateTopicsObservable()
        }
    }

    fun downvoteTopic(id: String) {
        val topic = topicsMap[id]
        if (topic != null && topic.votes > 0) {
            topic.votes = topic.votes - 1
            updateTopicsObservable()
        }
    }

    private fun updateTopicsObservable() {
        topics = topicsMap.values.sortedWith(compareBy({ it.votes }, { it.lastUpdated }))
        topicsObservable.onNext(topics)
    }

    private fun generateId() : String {
        var id: String
        do {
            id = generateRandomAlphanumericString(length = 40)
        } while (topicsMap.containsKey(key = id))
        return id
    }
}