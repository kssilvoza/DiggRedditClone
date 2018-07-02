package com.silvozatechnologies.diggredditclone.ui.topics.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.text.InputFilter
import android.view.LayoutInflater
import android.widget.EditText
import com.silvozatechnologies.diggredditclone.R
import com.silvozatechnologies.diggredditclone.data.model.Topic
import com.silvozatechnologies.diggredditclone.ui.topics.viewmodel.TopicsViewModel
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_topic.*
import kotlinx.android.synthetic.main.view_edit_text.view.*
import javax.inject.Inject

class TopicsActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var topicsAdapter: TopicsAdapter
    private lateinit var addTopicDialog: AlertDialog

    private lateinit var viewModel: TopicsViewModel

    private val listener = object : TopicsAdapter.Listener {
        override fun onUpvoteClicked(topic: Topic) {
            viewModel.upvoteTopic(topic.id)
        }

        override fun onDownvoteClicked(topic: Topic) {
            viewModel.downvoteTopic(topic.id)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topic)
        initializeViewModel()
        initializeRecyclerView()
        initializeButtons()
        initializeDialog()
        startObserving()
    }

    private fun initializeViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(TopicsViewModel::class.java)
    }

    private fun initializeRecyclerView() {
        topicsAdapter = TopicsAdapter(lifecycleOwner = this, listener = listener)
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.adapter = topicsAdapter
    }

    private fun initializeButtons() {
        button_add.setOnClickListener { onAddButtonClicked() }
    }

    private fun initializeDialog() {
        val layoutInflater = LayoutInflater.from(this)
        val view = layoutInflater.inflate(R.layout.view_edit_text, null, false)
        val editText = view.edittext
        editText.filters = arrayOf(InputFilter.LengthFilter(viewModel.TOPIC_NAME_MAX_LENGTH))

        addTopicDialog = AlertDialog.Builder(this)
                .setTitle(R.string.add_topic_dialog_title)
                .setView(view)
                .setPositiveButton(R.string.button_add) { _, _ ->
                    viewModel.addTopic(topicName = editText.text.toString())
                    editText.text.clear()
                }
                .setNegativeButton(R.string.button_cancel) { _, _ ->
                    addTopicDialog.dismiss()
                }
                .create()
    }

    private fun startObserving() {
        viewModel.topics.observe(this, Observer { onTopicsChanged(it) })
    }

    private fun onAddButtonClicked() {
        addTopicDialog.show()
    }

    private fun onTopicsChanged(topics: List<Topic>?) {
        if (topics != null) {
            topicsAdapter.setTopics(topics)
        }
    }
}
