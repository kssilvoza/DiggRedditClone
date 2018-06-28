package com.silvozatechnologies.diggredditclone.data.repository

import com.silvozatechnologies.diggredditclone.common.extension.generateRandomAlphanumericString
import com.silvozatechnologies.diggredditclone.data.model.Topic

private const val ID_LENGTH = 40

class TopicRepository {
    private val topicsMap = HashMap<String, Topic>()
    private val topicsList = ArrayList<Topic>()

    fun addTopic(topicName: String) {
        val id = generateId()
        val topic = Topic(id = id, topicName = topicName, votes = 0)
        topicsMap[id] = topic
        topicsList.add(topic)
    }

    fun upvoteTopic(id: String) {
        val topic = topicsMap[id]
        if (topic != null) {
            
        }
    }

    private fun generateId() : String {
        var id: String
        do {
            id = generateRandomAlphanumericString(ID_LENGTH)
        } while (topicsMap.containsKey(id))
        return id
    }
}