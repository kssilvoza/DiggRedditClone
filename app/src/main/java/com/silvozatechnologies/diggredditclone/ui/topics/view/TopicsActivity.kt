package com.silvozatechnologies.diggredditclone.ui.topics.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.silvozatechnologies.diggredditclone.R
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_topic.*

class TopicsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topic)
        initializeButtons()
    }

    private fun initializeButtons() {
        button_add.setOnClickListener { onAddButtonClicked() }
    }

    private fun onAddButtonClicked() {
        
    }
}
