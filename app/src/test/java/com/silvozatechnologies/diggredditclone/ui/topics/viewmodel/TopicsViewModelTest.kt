package com.silvozatechnologies.diggredditclone.ui.topics.viewmodel

import android.arch.core.executor.testing.InstantTaskExecutorRule
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
import org.mockito.MockitoAnnotations
import org.mockito.runners.MockitoJUnitRunner
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner

//@RunWith(PowerMockRunner::class)
@RunWith(MockitoJUnitRunner::class)
class TopicsViewModelTest {
//    @Rule
//    var instantTaskExecutorRule = InstantTaskExecutorRule()

//    @Rule
//    @JvmField
//    val rxImmediateSchedulerRule = RxImmediateSchedulerRule()

    @Mock
    lateinit var topicsRepository: TopicRepository

    private lateinit var topicsViewModel: TopicsViewModel

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        val topicsObservable = ReplaySubject.create<List<Topic>>()
        Mockito.`when`(topicsRepository.observeTopics()).thenReturn(topicsObservable)
        topicsViewModel = TopicsViewModel(topicsRepository)
    }

    @Test
    fun addTopic_shouldAddTopic() {
        val topicName = "Topic Name"
        topicsViewModel.addTopic(topicName)
        Mockito.verify(topicsRepository).addTopic(topicName)
    }
}