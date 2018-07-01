package com.silvozatechnologies.diggredditclone.ui.topics.viewmodel

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Observer
import com.silvozatechnologies.diggredditclone.data.model.Topic
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.powermock.modules.junit4.PowerMockRunner

@RunWith(PowerMockRunner::class)
class TopicItemViewModelTest {
    @Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var topicNameObserver: Observer<String>
    @Mock
    private lateinit var votesObserver: Observer<String>

    private lateinit var topicItemViewModel: TopicItemViewModel

    @Before
    fun setup() {
        topicItemViewModel = TopicItemViewModel()
    }

    @Test
    fun setTopicAndUpdate_shouldShowTopicNameAndVotes() {
        val topicName = "Topic Name"
        val votes = 999
        val topic = Topic(id = "Id", topicName = topicName, votes = votes, lastUpdated = 0)

        topicItemViewModel.topicName.observeForever(topicNameObserver)
        topicItemViewModel.votes.observeForever(votesObserver)
        topicItemViewModel.setTopicAndUpdate(topic)

        assert(topicItemViewModel.topicName.value == topicName &&
                topicItemViewModel.votes.value == "$votes")
    }

    @Test
    fun setTopicAndUpdate_shouldShowThousandVotesProperly() {
        val votes = 1234
        val topic = Topic(id = "Id", topicName = "Topic Name", votes = votes, lastUpdated = 0)

        topicItemViewModel.votes.observeForever(votesObserver)
        topicItemViewModel.setTopicAndUpdate(topic)

        assert(topicItemViewModel.votes.value == "1.2K")
    }

    @Test
    fun setTopicAndUpdate_shouldShowMillionVotesProperly() {
        val votes = 1234567
        val topic = Topic(id = "Id", topicName = "Topic Name", votes = votes, lastUpdated = 0)

        topicItemViewModel.votes.observeForever(votesObserver)
        topicItemViewModel.setTopicAndUpdate(topic)

        assert(topicItemViewModel.votes.value == "1.2M")
    }

    @Test
    fun setTopicAndUpdate_shouldShowBillionVotesProperly() {
        val votes = 1234567890
        val topic = Topic(id = "Id", topicName = "Topic Name", votes = votes, lastUpdated = 0)

        topicItemViewModel.votes.observeForever(votesObserver)
        topicItemViewModel.setTopicAndUpdate(topic)

        assert(topicItemViewModel.votes.value == "1.2B")
    }
}