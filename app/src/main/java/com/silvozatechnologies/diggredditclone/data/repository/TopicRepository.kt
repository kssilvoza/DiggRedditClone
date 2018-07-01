package com.silvozatechnologies.diggredditclone.data.repository

import com.silvozatechnologies.diggredditclone.common.utility.generateRandomAlphanumericString
import com.silvozatechnologies.diggredditclone.data.model.Topic
import io.reactivex.subjects.PublishSubject

/*
 * This class follows the repository pattern. Since we do not care where the data is coming from,
 * the repository abstracts the data source whether it is from API or local storage
 *
 * In a production scenario where APIs and local storage is used, this class should have instances
 * to an TopicsApi class for fetching topics from a server as well as a TopicsDao for
 * getting/storing topics from/to the database.
 *
 * Since there is only have one data source (memory), it would be best to put data handling in this class
 */
class TopicRepository {
    val topicsObservable: PublishSubject<List<Topic>> = PublishSubject.create<List<Topic>>()

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
            topic.lastUpdated = System.currentTimeMillis()
            updateTopicsObservable()
        }
    }

    fun downvoteTopic(id: String) {
        val topic = topicsMap[id]
        if (topic != null && topic.votes > 0) {
            topic.votes = topic.votes - 1
            topic.lastUpdated = System.currentTimeMillis()
            updateTopicsObservable()
        }
    }

    /*
     * Obtains the values from the HashMap and sorts them according to number of votes. In case of
     * a tie, it is sorted from newest to oldest (updated)
     */
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