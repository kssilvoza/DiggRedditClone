package com.silvozatechnologies.diggredditclone.ui.topics.viewmodel

import android.arch.lifecycle.Observer
import com.silvozatechnologies.diggredditclone.data.model.Topic
import com.silvozatechnologies.diggredditclone.data.repository.TopicRepository
import io.reactivex.subjects.ReplaySubject
import mockito.RxImmediateSchedulerRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.never
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TopicsViewModelTest {
    @Rule
    @JvmField
    val rxImmediateSchedulerRule = RxImmediateSchedulerRule()

    @Mock
    private lateinit var topicsObserver: Observer<List<Topic>>

    @Mock
    private lateinit var topicRepository: TopicRepository

    private lateinit var topicsViewModel: TopicsViewModel

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
//        topicRepository.topicsObservable = ReplaySubject.create<List<Topic>>()
//        topicRepository.topicsMap = mutableMapOf()
        topicsViewModel = TopicsViewModel(topicRepository)
    }

    @Test
    fun addTopic_shouldNotAddTopicWithLongName() {
        val topicName = "YLqpm55qk01SWh21dt6yzCDz8AoNLaHHoStAYxz9zCbJLzy4ZalIHODFQDPuPxUbxZkOem51nA2BTrIuXkq6UzOkXe3Q9d0D2v3ikOIvjfSHyYNQzVt4VrB02izOYanRXoKSi1y5QsrmYYfQpB9k8RJ6yrqY3vB7mpsvWyazab5oZEIFbnsY3LWoajZj4iT8FMyPJLnQXAHafXa47dfV7ZbfSZ22tGDNxacYfXhmFeb5AaEp15ljEPiSI392U58r"
        topicsViewModel.topics.observeForever(topicsObserver)
        topicsViewModel.addTopic(topicName)
        verify(topicsObserver, never()).onChanged(mutableListOf())
    }

    @Test
    fun addTopic_shouldAddTopic() {
        val topicName = "Topic Name"
        topicsViewModel.topics.observeForever(topicsObserver)
        topicsViewModel.addTopic(topicName)
        Mockito.verify(topicRepository).addTopic(topicName)
//        assert(topicsViewModel.topics.value != null)
//        val topics = topicsViewModel.topics.value
//        System.out.println("$topics")
//        if (topics != null) {
//            System.out.println("${topics.size}")
//            var isTopicInList = false
//            for (topic in topics) {
//                if (topic.topicName == topicName) {
//                    isTopicInList = true
//                    break
//                }
//            }
//        } else {
//            assert(false)
//        }

    }
}