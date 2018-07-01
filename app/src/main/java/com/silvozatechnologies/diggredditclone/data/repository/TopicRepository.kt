package com.silvozatechnologies.diggredditclone.data.repository

import com.silvozatechnologies.diggredditclone.common.utility.generateRandomAlphanumericString
import com.silvozatechnologies.diggredditclone.data.model.Topic
import io.reactivex.subjects.ReplaySubject
import java.util.*

class TopicRepository {
    val topicsObservable: ReplaySubject<List<Topic>> = ReplaySubject.create<List<Topic>>()

    val topicsMap = mutableMapOf<String, Topic>()
    var topics = listOf<Topic>()

    fun observeTopics() : ReplaySubject<List<Topic>> {
        return topicsObservable
    }

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
        topics = topicsMap.values.sortedWith(Comparator {
            o1, o2 ->
                if (o1.votes > o2.votes) {
                    -1
                } else if (o1.votes == o2.votes) {
                    if (o1.lastUpdated > o2.lastUpdated) -1 else 1
                } else {
                    1
                }
        })
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