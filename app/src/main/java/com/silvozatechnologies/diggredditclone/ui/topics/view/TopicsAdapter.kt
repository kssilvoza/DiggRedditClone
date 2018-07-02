package com.silvozatechnologies.diggredditclone.ui.topics.view

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.silvozatechnologies.diggredditclone.R
import com.silvozatechnologies.diggredditclone.data.model.Topic
import com.silvozatechnologies.diggredditclone.ui.topics.viewmodel.TopicItemViewModel
import kotlinx.android.synthetic.main.item_topic.view.*

class TopicsAdapter(private val lifecycleOwner: LifecycleOwner, private val listener: Listener) : RecyclerView.Adapter<TopicsAdapter.ViewHolder>() {
    private var topics = mutableListOf<Topic>()

    interface Listener {
        fun onUpvoteClicked(topic: Topic)

        fun onDownvoteClicked(topic: Topic)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_topic, parent, false)
        return ViewHolder(itemView, lifecycleOwner, listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int, payloads: MutableList<Any>) {
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position)
            return
        }

        holder.viewModel.setTopicAndUpdate(topics[position])
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.viewModel.setTopicAndUpdate(topics[position])
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
    }

    class ViewHolder(itemView: View, lifecycleOwner: LifecycleOwner, private val listener: Listener) : RecyclerView.ViewHolder(itemView) {
        val viewModel = TopicItemViewModel()

        init {
            viewModel.topicName.observe(lifecycleOwner, Observer {
                itemView.textview_name.text = it
            })
            viewModel.votes.observe(lifecycleOwner, Observer {
                itemView.textview_votes.text = it
            })

            itemView.button_upvote.setOnClickListener {
                listener.onUpvoteClicked(viewModel.topic)
            }
            itemView.button_downvote.setOnClickListener {
                listener.onDownvoteClicked(viewModel.topic)
            }
        }
    }
}