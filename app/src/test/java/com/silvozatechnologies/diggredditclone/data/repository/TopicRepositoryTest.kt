package com.silvozatechnologies.diggredditclone.data.repository

import com.silvozatechnologies.diggredditclone.data.model.Topic
import io.reactivex.observers.TestObserver
import mockito.RxImmediateSchedulerRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TopicRepositoryTest {
    @Rule
    @JvmField
    val rxImmediateSchedulerRule = RxImmediateSchedulerRule()

    private lateinit var topicRepository: TopicRepository

    private val dummyTopics = mutableMapOf(
            "Id1" to Topic(id = "Id1", topicName = "Topic Name 1", votes = 3, lastUpdated = System.currentTimeMillis()),
            "Id2" to Topic(id = "Id2", topicName = "Topic Name 2", votes = 2, lastUpdated = System.currentTimeMillis() + 1),
            "Id3" to Topic(id = "Id3", topicName = "Topic Name 3", votes = 1, lastUpdated = System.currentTimeMillis() + 2)
    )

    @Before
    fun setup() {
        topicRepository = TopicRepository()
    }

    @Test
    fun addTopic_shouldAddTopic() {
        val testObserver = TestObserver<List<Topic>>()
        val topicName = "Topic Name"

        topicRepository.topicsObservable.subscribe(testObserver)
        topicRepository.addTopic(topicName)

        testObserver.assertNoErrors()
        testObserver.assertValue {
            var isTopicInList = false
            for (topic in it) {
                if (topic.topicName == topicName) {
                    isTopicInList = true
                    break
                }
            }
            it.size == 1 && isTopicInList
        }
    }

    @Test
    fun addTopic_shouldIdsBeRandomAlphanumeric() {
        val topicName1 = "Topic Name 1"
        val topicName2 = "Topic Name 2"

        val id1 = topicRepository.addTopic(topicName1)
        val id2 = topicRepository.addTopic(topicName2)

        assert(id1 != id2 && isAlphaNumeric(id1) && isAlphaNumeric(id2))
    }

    @Test
    fun addTopic_shouldTopicsInOrder() {
        val testObserver = TestObserver<List<Topic>>()

        topicRepository.topicsMap = dummyTopics

        // Subscribe here since this would be the test stimulus
        val topicName = "Topic Name"
        topicRepository.topicsObservable.subscribe(testObserver)
        topicRepository.addTopic(topicName = topicName)

        testObserver.assertNoErrors()
        testObserver.assertValue {
            isTopicsInOrder(topics = it)
        }
    }

    @Test
    fun upvoteTopic_shouldTopicsInOrder() {
        val testObserver = TestObserver<List<Topic>>()

        topicRepository.topicsMap = dummyTopics

        // Subscribe here since this would be the test stimulus
        val topicId = "Id2"
        topicRepository.topicsObservable.subscribe(testObserver)
        topicRepository.upvoteTopic(id = topicId)

        testObserver.assertNoErrors()
        testObserver.assertValue {
            isTopicsInOrder(topics = it)
        }
    }

    @Test
    fun downvoteTopic_shouldTopicsInOrder() {
        val testObserver = TestObserver<List<Topic>>()

        topicRepository.topicsMap = dummyTopics

        // Subscribe here since this would be the test stimulus
        val topicId = "Id2"
        topicRepository.topicsObservable.subscribe(testObserver)
        topicRepository.downvoteTopic(id = topicId)

        testObserver.assertNoErrors()
        testObserver.assertValue {
            isTopicsInOrder(topics = it)
        }
    }

    private fun isTopicsInOrder(topics: List<Topic>) : Boolean {
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

    private fun isAlphaNumeric(string: String) : Boolean {
        var isAlphaNumeric = true
        for (i in 0 until string.length) {
            val char = string[i]
            if (!Character.isLetter(char) && !Character.isDigit(char)) {
                isAlphaNumeric = false
                break
            }
        }
        return isAlphaNumeric
    }
}