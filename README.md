# DiggRedditClone
This project is an Android application that mimics the functionality of news aggregator websites such as Digg and Reddit. It allows users to submit topics as well as upvote/downvote topics. The main page displays the top 20 most upvoted topics. If two or more topics have the same number of votes, they will be sorted from newest to oldest topic 

### Programming Language
Kotlin was used to develop the application

### Libraries
* __Android Architecture Components__ - Backbone of the MVVM architecture of the application
    * __LiveData__ - Lifecycle-aware observable data holder class. It automatically observes when the `Activity` is visible and unobserves when it is hidden
    * __ViewModel__ - Lifecycle-aware viewmodels that are retained even in orientation change
* __Dagger__ - Dependency Injection
* __RxJava and RxAndroid__ - A library for composing asychronous and event-based applications.
* __ConstraintLayout__ - Flexible ViewGroup that helps in creating flat view hierarchies

### Testing
* __JUnit__ - Unit testing library for Java/Android Applications
* __Mockito__ - Mocking framework for unit testing in Java/Android Applications 
* __Powermock__ - Extends capabilities of mocking frameworks like Mockito

### Architecture
This project follows the Model-View-ViewModel architecture because of the following reasons:
1. This architecture separates the business logic from the UI thus allowing single responsibility. 
2. It decouples the ViewModel and the View since the former does not need to keep a reference to the latter.
3. The two reasons above makes this application more unit testable
4. Android has developed a set of libraries that enables this Architecture (Android Architecture Components, specifically the `ViewModel` class)

Shown below is an illustration of the MVVM architecture of the application:

![alt text](https://github.com/kssilvoza/DiggRedditClone/blob/dev/markdown/DiggRedditClone_MVVM_Architecture.png "Architecture")

As shown in the illustration above, the components are loosely coupled. The View (Activity) has an instance of the ViewModel to execute business logic. The ViewModel has an instance to the Model (Repository) so it can fetch and manipulate the data stored. Other than the two above, there are no more instances of each component within the Architecture. This make unit testing the ViewModel easier since only the Repository is needed to be mocked. As long as there are no Android components in the ViewModel, it should be unit testable. Below is a discussion on each component of the MVVM structure

##### View (Activity)
This class is responsible for the UI of the application. Note that bridge classes like `Adapter` for `RecyclerView` is also included in this as well. To execute any business logic, it will use its `ViewModel` instance. As indicated by the broken line in the illustration, it also observes the LiveData instances in the ViewModel. If there is new data from these instances, the view should show said data onto the screen. 

##### ViewModel

This class is responsible for the business logic of the application. It extends the `ViewModel` class provided in the Android Architecture Components. This was done because it allows the ViewModel to survive even if there is an orientation change. It is also lifecycle-aware thus Rx `dispose` methods can be executed in its `onCleared` method for proper cleanup. 

It also has `LiveData` instances so that the view (`Activity`) can listen to  changes it makes to data. Note that the `LiveData` instances could be replaced by Rx Observables but this was not done since this is overkill for its job. There should be minimal data manipulation between the viewmodel and the view thus Rx is not necessary. Rx is ideal for manipulating the data flowing from the model to the viewmodel. 

Since it handles business logic, it has to have an instance of the model (`Repository`). It also observes the Rx `Observable` of the `Repository` to fetch and manipulate data. 

##### Model (Repository)
The model follows the repository pattern. Since we do not care where the data is coming from, the `Repository` should abstract the data source.

In a production scenario where APIs and local storage is used, this class should have instances to an `Api` instance for fetching topics from a server as well as a `Dao` instance for getting/storing topics from/to the database.

Since there is only have one data source (memory), it would be best to put the data in the `Repository`

The following section will discuss on how the topics are stored in the Model

### Data Structure
The structure used by `TopicRepository` in storing the topics is a `HashMap` with the `topicId` as its key and a `Topic` as its value. This structure is used because adding and searching for a Topic in the `HashMap` will have O(1) complexity. 

When a topic is added, it is added to the `HashMap` using the `put` method. On the other hand, when a topic is upvoted/downvoted, it will be searched in the `HashMap` using the `get` method. For adding and upvoting/downvoting, after updating the `HashMap`, its values are then obtained as a `List` using the `values` method. To ensure that the topics would be arranged properly, the `List` is then sorted using the `sortedWith` method. Built-in sorting functions of Java/Kotlin utilizes a modified mergesort which has a time complexity of O(n log(n)). 

Therefore, the topic operations have the following time complexities: 
* Adding - O(n log(n))
* Upvoting/Downvoting - O(n log(n))
