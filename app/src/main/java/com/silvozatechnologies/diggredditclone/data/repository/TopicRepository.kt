package com.silvozatechnologies.diggredditclone.data.repository

import com.silvozatechnologies.diggredditclone.common.utility.generateRandomAlphanumericString
import com.silvozatechnologies.diggredditclone.data.model.Topic

class TopicRepository {

    private val topicsMap = HashMap<String, Topic>()
    private val topicsList = ArrayList<Topic>()

    fun addTopic(topicName: String) {
        val id = generateId()
        val topic = Topic(id = id, topicName = topicName, votes = 0)
        topicsMap[id] = topic
        topicsList.add(element = topic)
    }

    fun upvoteTopic(id: String) {
        val topic = topicsMap[id]
        if (topic != null) {

        }
    }

    private fun generateId() : String {
        var id: String
        do {
            id = generateRandomAlphanumericString(length = 40)
        } while (topicsMap.containsKey(key = id))
        return id
    }
}