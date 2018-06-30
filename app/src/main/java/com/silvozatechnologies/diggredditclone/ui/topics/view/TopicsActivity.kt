package com.silvozatechnologies.diggredditclone.ui.topics.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.silvozatechnologies.diggredditclone.R
import com.silvozatechnologies.diggredditclone.data.model.Topic
import com.silvozatechnologies.diggredditclone.ui.topics.viewmodel.TopicsViewModel
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_topic.*
import javax.inject.Inject

class TopicsActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var topicsAdapter: TopicsAdapter

    private lateinit var viewModel: TopicsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topic)
        initializeViewModel()
        initializeRecyclerView()
        initializeButtons()
        startObserving()
    }

    private fun initializeViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(TopicsViewModel::class.java)
    }

    private fun initializeRecyclerView() {
        topicsAdapter = TopicsAdapter()
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter = topicsAdapter
    }

    private fun initializeButtons() {
        button_add.setOnClickListener { onAddButtonClicked() }
    }

    private fun startObserving() {
        viewModel.topics.observe(this, Observer { onTopicsChanged(it) })
    }

    private fun onAddButtonClicked() {
        viewModel.addTopic(topicName = "Test")
    }

    private fun onTopicsChanged(topics: List<Topic>?) {
        if (topics != null) {
            topicsAdapter.setTopics(topics)
        }
    }
}
