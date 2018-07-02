package com.silvozatechnologies.diggredditclone.ui.topics.view

import android.os.Bundle
import android.support.v7.util.DiffUtil
import android.util.Log
import com.silvozatechnologies.diggredditclone.data.model.Topic

class TopicsDiffCallback(private val oldTopics: List<Topic>, private val newTopics: List<Topic>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldTopics.size
    }

    override fun getNewListSize(): Int {
        return newTopics.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val bool = oldTopics[oldItemPosition].id == newTopics[newItemPosition].id
//        if (bool) {
//            Log.d("TopicsDiffCallback", "Item Same = $oldItemPosition ${oldTopics[oldItemPosition].topicName} $newItemPosition = ${newTopics[newItemPosition].topicName}")
//        }
        return oldTopics[oldItemPosition].id == newTopics[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        Log.d("TopicsDiffCallback", "OLD = $oldTopics")
        Log.d("TopicsDiffCallback", "NEW = $newTopics")

        Log.d("TopicsDiffCallback", "OLD position = $oldItemPosition name = ${oldTopics[oldItemPosition].topicName} votes = ${oldTopics[oldItemPosition].votes} NEW position = $newItemPosition name = ${newTopics[newItemPosition].topicName} votes = ${newTopics[newItemPosition].votes}")

        val bool = oldTopics[oldItemPosition].topicName == newTopics[newItemPosition].topicName &&
                oldTopics[oldItemPosition].votes == newTopics[newItemPosition].votes &&
                oldTopics[oldItemPosition].lastUpdated == newTopics[newItemPosition].lastUpdated
//        if (!bool) {
//            Log.d("TopicsDiffCallback", "Content Same = $oldItemPosition ${oldTopics[oldItemPosition].topicName} ${oldTopics[oldItemPosition].votes} New = $newItemPosition ${newTopics[newItemPosition].topicName} ${newTopics[newItemPosition].votes}")
//        }
        return bool
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        Log.d("TopicsDiffCallback", "getChangePayload")
        val oldTopic = oldTopics[oldItemPosition]
        val newTopic = newTopics[newItemPosition]

        val payload = Bundle()
        if (oldTopic.topicName != newTopic.topicName) {
            payload.putString(TOPIC_NAME_CHANGE, newTopic.topicName)
        }
        if (oldTopic.votes != newTopic.votes) {
            payload.putInt(VOTES_CHANGE, newTopic.votes)
        }
        if (oldTopic.lastUpdated != newTopic.lastUpdated) {
            payload.putLong(LAST_UPDATED_CHANGE, newTopic.lastUpdated)
        }

        return if (payload.isEmpty) {
            null
        } else {
            payload
        }
    }

    companion object {
        const val TOPIC_NAME_CHANGE = "Topic Name Change"
        const val VOTES_CHANGE = "Votes Change"
        const val LAST_UPDATED_CHANGE = "Last Updated Change"
    }
}