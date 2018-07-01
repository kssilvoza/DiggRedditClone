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

#### Architecture
This project follows the Model-View-ViewModel architecture because of the following reasons:
1. This architecture separates the business logic from the UI thus allowing single responsibility. 
2. It decouples the ViewModel and the View since the former does not need to keep a reference to the latter.
3. The two reasons above makes this application more unit testable
4. Android has developed a set of libraries that enables this Architecture (Android Architecture Components, specifically the `ViewModel` class)

Shown below is an 

#### Data Structure
The structure used by `TopicRepository` in storing the topics is a `HashMap` with the `topicId` as its key and a `Topic` as its value. This structure is used because adding and searching for a Topic in the `HashMap` will have O(1) complexity. 

When a topic is added, it is added to the `HashMap` using the `put` method. On the other hand, when a topic is upvoted/downvoted, it will be searched in the `HashMap` using the `get` method. For adding and upvoting/downvoting, after updating the `HashMap`, its values are then obtained as a `List` using the `values` method. To ensure that the topics would be arranged properly, the `List` is then sorted using the `sortedWith` method.  
