package com.silvozatechnologies.diggredditclone.ui.topics.view

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.silvozatechnologies.diggredditclone.data.model.Topic

class TopicsAdapter : RecyclerView.Adapter<TopicsAdapter.ViewHolder>() {
    private var topics = mutableListOf<Topic>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun setTopics(topics: List<Topic>) {
        val topicsDiffCallback = TopicsDiffCallback(this.topics, topics)
        val diffResult = DiffUtil.calculateDiff(topicsDiffCallback)

        this.topics.clear()
        this.topics.addAll(topics)
        diffResult.dispatchUpdatesTo(this)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }
}