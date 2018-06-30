package com.silvozatechnologies.diggredditclone.ui.topics.view

import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.silvozatechnologies.diggredditclone.R
import com.silvozatechnologies.diggredditclone.data.model.Topic
import kotlinx.android.synthetic.main.item_topic.view.*

class TopicsAdapter(private val listener: Listener) : RecyclerView.Adapter<TopicsAdapter.ViewHolder>() {
    private var topics = mutableListOf<Topic>()

    interface Listener {
        fun onUpvoteClicked(topic: Topic)

        fun onDownvoteClicked(topic: Topic)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_topic, parent, false)
        return ViewHolder(itemView, listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setTopic(topics[position])
    }

    override fun getItemCount(): Int {
        return topics.size
    }

    fun setTopics(topics: List<Topic>) {
        val topicsDiffCallback = TopicsDiffCallback(this.topics, topics)
        val diffResult = DiffUtil.calculateDiff(topicsDiffCallback)

        this.topics.clear()
        this.topics.addAll(topics)
        diffResult.dispatchUpdatesTo(this)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View, listener: Listener) : RecyclerView.ViewHolder(itemView) {
        private lateinit var topic: Topic

        init {
            itemView.button_upvote.setOnClickListener {
                listener.onUpvoteClicked(topic)
            }
            itemView.button_downvote.setOnClickListener {
                listener.onDownvoteClicked(topic)
            }
        }

        fun setTopic(topic: Topic) {
            this.topic = topic

            itemView.textview_name.text = topic.topicName
            itemView.textview_votes.text = "${topic.votes}"
        }
    }
}