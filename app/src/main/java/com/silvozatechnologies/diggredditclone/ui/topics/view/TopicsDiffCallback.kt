package com.silvozatechnologies.diggredditclone.ui.topics.view

import android.os.Bundle
import android.support.v7.util.DiffUtil
import android.util.Log
import com.silvozatechnologies.diggredditclone.data.model.Topic

/*
 * This prevents us from having to call notifyDataSetChanged just to update the RecyclerView.
 * It detects whether an item in the topics RecyclerView has totally changed or just its contents.
 * If only the content has changed, it will only do a partial change to the RecyclerView
 *
 * Logic obtained here:
 * https://android.jlelse.eu/android-dtt-12-animate-recyclerview-with-diffutil-cac02b229911
 */
class TopicsDiffCallback(private val oldTopics: List<Topic>, private val newTopics: List<Topic>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldTopics.size
    }

    override fun getNewListSize(): Int {
        return newTopics.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldTopics[oldItemPosition].id == newTopics[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldTopics[oldItemPosition] == newTopics[newItemPosition]
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
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