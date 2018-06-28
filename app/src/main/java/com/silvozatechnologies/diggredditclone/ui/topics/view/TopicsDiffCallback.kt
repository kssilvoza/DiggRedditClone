package com.silvozatechnologies.diggredditclone.ui.topics.view

import android.support.v7.util.DiffUtil
import com.silvozatechnologies.diggredditclone.data.model.Topic

class TopicsDiffCallback(private val oldTopics: List<Topic>,  private val newTopics: List<Topic>) : DiffUtil.Callback() {
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
}