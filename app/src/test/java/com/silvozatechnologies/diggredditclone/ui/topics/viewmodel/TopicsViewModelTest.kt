package com.silvozatechnologies.diggredditclone.ui.topics.viewmodel

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Observer
import com.silvozatechnologies.diggredditclone.data.model.Topic
import com.silvozatechnologies.diggredditclone.data.repository.TopicRepository
import mockito.RxImmediateSchedulerRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.never
import org.mockito.MockitoAnnotations
import org.powermock.modules.junit4.PowerMockRunner

@RunWith(PowerMockRunner::class)
class TopicsViewModelTest {
    @Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Rule
    @JvmField
    val rxImmediateSchedulerRule = RxImmediateSchedulerRule()

    @Mock
    private lateinit var topicsObserver: Observer<List<Topic>>

    private lateinit var emptyTopicsViewModel: TopicsViewModel
    private lateinit var filledTopicsViewModel: TopicsViewModel

    private val dummyTopics = mutableMapOf(
            "Id1" to Topic(id = "Id1", topicName = "Topic Name 1", votes = 16, lastUpdated = System.currentTimeMillis()),
            "Id2" to Topic(id = "Id2", topicName = "Topic Name 2", votes = 16, lastUpdated = System.currentTimeMillis() + 1),
            "Id3" to Topic(id = "Id3", topicName = "Topic Name 3", votes = 9, lastUpdated = System.currentTimeMillis() + 2),
            "Id4" to Topic(id = "Id4", topicName = "Topic Name 4", votes = 15, lastUpdated = System.currentTimeMillis() + 3),
            "Id5" to Topic(id = "Id5", topicName = "Topic Name 5", votes = 8, lastUpdated = System.currentTimeMillis() + 4),
            "Id6" to Topic(id = "Id6", topicName = "Topic Name 6", votes = 5, lastUpdated = System.currentTimeMillis() + 5),
            "Id7" to Topic(id = "Id7", topicName = "Topic Name 7", votes = 2, lastUpdated = System.currentTimeMillis() + 6),
            "Id8" to Topic(id = "Id8", topicName = "Topic Name 8", votes = 3, lastUpdated = System.currentTimeMillis() + 7),
            "Id9" to Topic(id = "Id9", topicName = "Topic Name 9", votes = 14, lastUpdated = System.currentTimeMillis() + 8),
            "Id10" to Topic(id = "Id10", topicName = "Topic Name 10", votes = 19, lastUpdated = System.currentTimeMillis() + 9),
            "Id11" to Topic(id = "Id11", topicName = "Topic Name 11", votes = 5, lastUpdated = System.currentTimeMillis() + 10),
            "Id12" to Topic(id = "Id12", topicName = "Topic Name 12", votes = 12, lastUpdated = System.currentTimeMillis() + 11),
            "Id13" to Topic(id = "Id13", topicName = "Topic Name 13", votes = 10, lastUpdated = System.currentTimeMillis() + 12),
            "Id14" to Topic(id = "Id14", topicName = "Topic Name 14", votes = 10, lastUpdated = System.currentTimeMillis() + 13),
            "Id15" to Topic(id = "Id15", topicName = "Topic Name 15", votes = 18, lastUpdated = System.currentTimeMillis() + 14),
            "Id16" to Topic(id = "Id16", topicName = "Topic Name 16", votes = 7, lastUpdated = System.currentTimeMillis() + 15),
            "Id17" to Topic(id = "Id17", topicName = "Topic Name 17", votes = 4, lastUpdated = System.currentTimeMillis() + 16),
            "Id18" to Topic(id = "Id18", topicName = "Topic Name 18", votes = 17, lastUpdated = System.currentTimeMillis() + 17),
            "Id19" to Topic(id = "Id19", topicName = "Topic Name 19", votes = 6, lastUpdated = System.currentTimeMillis() + 18),
            "Id20" to Topic(id = "Id20", topicName = "Topic Name 20", votes = 13, lastUpdated = System.currentTimeMillis() + 19),
            "Id21" to Topic(id = "Id21", topicName = "Topic Name 21", votes = 7, lastUpdated = System.currentTimeMillis() + 20),
            "Id22" to Topic(id = "Id22", topicName = "Topic Name 22", votes = 4, lastUpdated = System.currentTimeMillis() + 21),
            "Id23" to Topic(id = "Id23", topicName = "Topic Name 23", votes = 17, lastUpdated = System.currentTimeMillis() + 22),
            "Id24" to Topic(id = "Id24", topicName = "Topic Name 24", votes = 6, lastUpdated = System.currentTimeMillis() + 23),
            "Id25" to Topic(id = "Id25", topicName = "Topic Name 25", votes = 13, lastUpdated = System.currentTimeMillis() + 24)
    )

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        val filledTopicRepository = TopicRepository()
        filledTopicRepository.topicsMap = dummyTopics

        emptyTopicsViewModel = TopicsViewModel(TopicRepository())
        filledTopicsViewModel = TopicsViewModel(filledTopicRepository)
    }

    @Test
    fun addTopic_topicShouldBeAdded() {
        val topicName = "Topic Name"
        emptyTopicsViewModel.topics.observeForever(topicsObserver)
        emptyTopicsViewModel.addTopic(topicName)
        val list = emptyTopicsViewModel.topics.value
        assert(list != null && list.size == 1 && list[0].topicName == topicName)
    }

    @Test
    fun addTopic_topicWithEmptyNameShouldNotBeAdded() {
        val topicName = ""
        emptyTopicsViewModel.topics.observeForever(topicsObserver)
        emptyTopicsViewModel.addTopic(topicName)
        Mockito.verify(topicsObserver, never()).onChanged(Mockito.anyList())
    }

    @Test
    fun addTopic_topicWithLongNameShouldNotBeAdded() {
        val topicName = "YLqpm55qk01SWh21dt6yzCDz8AoNLaHHoStAYxz9zCbJLzy4ZalIHODFQDPuPxUbxZkOem51nA2BTrIuXkq6UzOkXe3Q9d0D2v3ikOIvjfSHyYNQzVt4VrB02izOYanRXoKSi1y5QsrmYYfQpB9k8RJ6yrqY3vB7mpsvWyazab5oZEIFbnsY3LWoajZj4iT8FMyPJLnQXAHafXa47dfV7ZbfSZ22tGDNxacYfXhmFeb5AaEp15ljEPiSI392U58r"
        emptyTopicsViewModel.topics.observeForever(topicsObserver)
        emptyTopicsViewModel.addTopic(topicName)
        Mockito.verify(topicsObserver, never()).onChanged(Mockito.anyList())
    }

    @Test
    fun addTopic_topicsShouldBeInOrderAndWithinMaxCount() {
        val topicName = "Topic Name"
        filledTopicsViewModel.topics.observeForever(topicsObserver)
        filledTopicsViewModel.addTopic(topicName)
        val list = filledTopicsViewModel.topics.value
        assert(list != null && isTopicsInOrder(list) && list.size == filledTopicsViewModel.TOPICS_MAX_COUNT)
    }

    @Test
    fun upvoteTopic_topicsShouldBeInOrderAndWithinMaxCount() {
        val topicId = "Id1"
        filledTopicsViewModel.topics.observeForever(topicsObserver)
        filledTopicsViewModel.upvoteTopic(topicId)
        val list = filledTopicsViewModel.topics.value
        assert(list != null && isTopicsInOrder(list) && list.size == filledTopicsViewModel.TOPICS_MAX_COUNT)
    }

    @Test
    fun downvoteTopic_topicsShouldBeInOrderAndWithinMaxCount() {
        val topicId = "Id1"
        filledTopicsViewModel.topics.observeForever(topicsObserver)
        filledTopicsViewModel.downvoteTopic(topicId)
        val list = filledTopicsViewModel.topics.value
        assert(list != null && isTopicsInOrder(list) && list.size == filledTopicsViewModel.TOPICS_MAX_COUNT)
    }

    private fun isTopicsInOrder(topics: List<Topic>): Boolean {
        var isTopicsInOrder = true
        var prevTopic: Topic? = null
        for (topic in topics) {
            if (prevTopic == null) {
                prevTopic = topic
            } else {
                if (prevTopic.votes < topic.votes ||
                        (prevTopic.votes == topic.votes && prevTopic.lastUpdated < topic.lastUpdated)) {
                    isTopicsInOrder = false
                    break
                }
                prevTopic = topic
            }
        }
        return isTopicsInOrder
    }
}