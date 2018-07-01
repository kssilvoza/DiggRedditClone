package com.silvozatechnologies.diggredditclone.ui.topics.viewmodel

import android.arch.lifecycle.MutableLiveData
import com.silvozatechnologies.diggredditclone.data.model.Topic
import java.text.DecimalFormat

private const val ONE_BILLION = 1000000000
private const val ONE_MILLION = 1000000
private const val ONE_THOUSAND = 1000

class TopicItemViewModel {
    lateinit var topic: Topic

    val topicName = MutableLiveData<String>()
    val votes = MutableLiveData<String>()

    fun setTopicAndUpdate(topic: Topic) {
        this.topic = topic

        topicName.value = topic.topicName
        val decimalFormat = DecimalFormat("#.#")
        val voteString = when {
            topic.votes >= ONE_BILLION -> {
                val quotient = topic.votes / ONE_BILLION * 1.0f
                "${decimalFormat.format(quotient)}B"
            }
            topic.votes >= ONE_MILLION -> {
                val quotient = topic.votes / ONE_MILLION * 1.0f
                "${decimalFormat.format(quotient)}M"
            }
            topic.votes >= ONE_THOUSAND -> {
                val quotient = topic.votes / ONE_THOUSAND * 1.0f
                "${decimalFormat.format(quotient)}K"
            }
            else -> "${topic.votes}"
        }

        votes.value = voteString
    }
}