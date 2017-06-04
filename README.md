Hacker News Mobile Application
===============================

This repository holds the source code of the Hacker News Mobile Application, a simple Android client for the [HackerNews API](github.com/HackerNews/API).

--------------------
### What is this repository for? ###

* Hacker News Mobile Application
*       Version:  1.0
* Last Update: Sat Jun 4, 2017

--------------------
### Development Tools ###

* Android Studio v2.2
* gradle-plugin v2.2.0
* Android SDK Build Tools v24.0.2
* MinSdkVersion 11
* CompileSDKVersion 24

--------------------
### Dependencies ###

* Android Support Tools (recyclerView, cardView, vector, palette,... ) v24.2.1
* Retrofit v2.1.0
* Okhttp v3.4.1
* Dagger v2.4
* RxJava v1.1.9
* RxAndroid v1.2.1
* Butterknife v8.0.1
* Timber v4.1.2
* AndroidUtils v1.0.1
* AppSettings v1.0.2
* jUnit v4.12
* Android Support Test v0.5
* Mockito v1.10.19
* Robolectric v3.1-rc1
* Espresso v2.2.2

--------------------
### Important Notes ###

The application has two Activity, Main Activities and detail activity. The Main Activity is responsible for presenting news List and detail activity to show the comments and reply.

All activity lifecycle and network behaviours are implemented, and according to device size and network situation user get a good UI and UX. In case having update cache data, app do not call Network. If no internet connection or network error, a message shows up.

Application network cache on Disk cause it work smooth and result a good UX.

Unit Test Cases was designed to test application UI functionality and core classes using jUnit and reboletric.
And instrument test use the dependency inject to mock the server data.

### Application Structure ###

The Application implemented and structured bases on the MVP pattern best practice, contributed by [Antonio Leiva](http://antonioleiva.com/mvp-android/).

Whole application functionality is implemented in "Core-Lib" module using pure Java. The "App" module contain all codes required for application to load on Android, which in this case can be replace by any other interface (like console app,...)

The view (MainActivity), contain a fragment. news fragment contain its own presenter and implement View interfaces and the only thing that the view will do is calling a method from the presenter every time there is an interface action.

The presenters (news, Main and detail Presenters), are responsible to act as the middle man between views and models. They retrieves data from the model and returns it formatted to the view. It also decides what happens when user interact with the view.

The models (News Interactor), would only be the gateway to the service domain layer or business logic. In this case it provide the data needed to be displayed in the view from Network or cached data (SharedPreferences).

The networking and API call are managed by [Retrofit](http://square.github.io/retrofit/) and OkHttp as its httpclient, contributed by [Square](http://square.github.io). It also shows decent logs while application is running in Debug mode. The whole caching and offline interceptors are also implemented using these libraries. 

Layers communications are managed by [RxJava](https://github.com/ReactiveX/RxJava) & [RxAndroid](https://github.com/ReactiveX/RxAndroid) contributed by [ReactiveX](http://reactivex.io).

Dependency Injections are being managed by [Dagger](https://github.com/google/dagger) created by [Square](http://square.github.io) and now maintained by [Google](http://google.github.io/dagger/).

Whole projects Dependencies are placed in "libraries.gradle" to avoid version conflicts and redundant in different modules.

The Android Log system is replaced with [Timber](https://github.com/JakeWharton/timber) contributed by Jake Wharton, which avoid logging in release version.

Used new SupportVector library in some icons cases for a better UI.

Used [circleci](http://circleci.com) as Continues Integration service which binded with Github repo.

React Native version is under development.