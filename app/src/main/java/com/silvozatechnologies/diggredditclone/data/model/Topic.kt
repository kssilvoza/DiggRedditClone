package com.silvozatechnologies.diggredditclone.data.model

data class Topic(val id: String, val topicName: String, var votes: Int, var lastUpdated: Long)