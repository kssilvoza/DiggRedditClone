package com.silvozatechnologies.diggredditclone.data.repository

import com.silvozatechnologies.diggredditclone.common.utility.generateRandomAlphanumericString
import com.silvozatechnologies.diggredditclone.data.model.Topic
import io.reactivex.subjects.ReplaySubject

/*
 * This class follows the repository pattern. Since we do not care where the data is coming from,
 * the repository should abstracts the data source.
 *
 * In a production scenario where APIs and local storage is used, this class should have instances
 * to an TopicsApi class for fetching topics from a server as well as a TopicsDao for
 * getting/storing topics from/to the database.
 *
 * Since there is only have one data source (memory), it would be best to put data handling in this class
 */
class TopicRepository {
    var topicsObservable: ReplaySubject<List<Topic>> = ReplaySubject.create<List<Topic>>()

    var topicsMap = mutableMapOf<String, Topic>()

    fun addTopic(topicName: String): String {
        val id = generateId()
        val lastUpdated = System.currentTimeMillis()
        val topic = Topic(id = id, topicName = topicName, votes = 0, lastUpdated = lastUpdated)
        topicsMap[id] = topic
        updateTopicsObservable()
        return id
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
        val sortedTopics = topicsMap.values.sortedWith(Comparator { o1, o2 ->
            if (o1.votes > o2.votes) {
                -1
            } else if (o1.votes == o2.votes) {
                if (o1.lastUpdated > o2.lastUpdated) -1 else 1
            } else {
                1
            }
        })
        topicsObservable.onNext(copyTopics(sortedTopics))
    }

    private fun generateId(): String {
        var id: String
        do {
            id = generateRandomAlphanumericString(length = 40)
        } while (topicsMap.containsKey(key = id))
        return id
    }

    private fun copyTopics(topics: List<Topic>): MutableList<Topic> {
        val topicsCopy = mutableListOf<Topic>()
        for (topic in topics) {
            val topicCopy = Topic(id = topic.id, topicName = topic.topicName, votes = topic.votes, lastUpdated = topic.lastUpdated)
            topicsCopy.add(topicCopy)
        }
        return topicsCopy
    }
}