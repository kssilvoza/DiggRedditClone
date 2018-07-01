# DiggRedditClone
This project is an Android application that mimics the functionality of news aggregator websites such as Digg and Reddit. It allows users to submit topics as well as upvote/downvote topics. The main page displays the top 20 most upvoted topics. If two or more topics have the same number of votes, they will be sorted from newest to oldest topic 

#### Technology Stack
* __Kotlin__ - Programming language used to develop Android applications
* __Android Architecture Components__ - Backbone of the MVVM architecture of the application
    * __LiveData__ - Lifecycle-aware observable data holder class
    * __ViewModel__ - Lifecycle-aware viewmodels that are retained even in orientation change
* __Dagger__ - Dependency Injection
* __RxJava and RxAndroid__ - A library for composing asychronous and event-based applications.
* __ConstraintLayout__ - Flexible ViewGroup that helps in creating flat view hierarchies

#### Data Structure
The structure used by `TopicRepository` in storing the topics is a `HashMap` with the `topicId` as its key and a `Topic` as its value. This structure is used because adding and searching for a Topic in the `HashMap` will have O(1) complexity. 

When a topic is added, it is added to the `HashMap` using the `put` method. On the other hand, when a topic is upvoted/downvoted, it will be searched in the `HashMap` using the `get` method. For adding and upvoting/downvoting, after updating the `HashMap`, its values are then obtained as a `List` using the `values` method. To ensure that the topics would be arranged properly, the `List` is then sorted using the `sortedWith` method.  
