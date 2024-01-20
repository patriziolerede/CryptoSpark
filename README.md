<div align="center">

<p><img src="misc/ic_launcher.png" width="200"></p>

# CryptoSpark (Kotlin + Jetpack Compose + Navigation + MVI)

![Language](https://img.shields.io/github/languages/top/cortinico/kotlin-android-template?color=blue&logo=kotlin)

CryptoSpark is a sample project showcasing a contemporary Android app development approach. 

It integrates widely-used Android tools and illustrates best development practices by leveraging cutting-edge technologies such as Compose, Kotlin Flow, and Koin. 

This sample app embodies a scalable and maintainable modern Android application architecture through a MVI (Model-View-Intent) design pattern.
## Description

* UI
    * [Compose](https://developer.android.com/jetpack/compose) declarative UI framework
    * [Material design](https://material.io/design)

* Tech/Tools
    * [Kotlin](https://kotlinlang.org/) 100% coverage
    * [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) and [Flow](https://developer.android.com/kotlin/flow) for async operations
    * [Koin](https://insert-koin.io/) for dependency injection
    * [Jetpack](https://developer.android.com/jetpack)
        * [Compose](https://developer.android.com/jetpack/compose)
        * [Navigation](https://developer.android.com/topic/libraries/architecture/navigation/) for navigation between composables
        * [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) that stores, exposes and manages UI state
    * [Retrofit](https://square.github.io/retrofit/) for networking
    * [Coil](https://github.com/coil-kt/coil) for image loading

* Modern Architecture
    * Single activity architecture (with [Navigation component](https://developer.android.com/guide/navigation/navigation-getting-started)) that defines navigation graphs
    * MVI
    * [Android Architecture components](https://developer.android.com/topic/libraries/architecture) ([ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel), [Navigation](https://developer.android.com/jetpack/androidx/releases/navigation))
    * [Android KTX](https://developer.android.com/kotlin/ktx) - Jetpack Kotlin extensions


## Architecture
The project is layered traditionally with a View, Presentation, Model separation and presents a MVI but adapted to Compose.

Architecture layers:
* View - Composable screens responsible for consuming state, applying effects, and handling events.
* ViewModel - That effectively manages and reduces the state of the associated screen. Moreover, it intercepts UI events and generates side-effects. The lifecycle scope of the ViewModel is linked to the corresponding screen composable.
* Model - Repository classes designed to retrieve data. Within a clean architecture framework, it is recommended to utilize use-cases that interface with repositories.


There are a three core components described:
* **State** - This is a data class that holds the state content of the corresponding screen, such as a list of User and loading status. The state is exposed as a Compose runtime MutableState object, which perfectly matches the use case of receiving continuous updates with an initial value.

* **Event** - It is a plain object that is sent through callbacks from the UI to the presentation layer. Events should reflect UI events caused by the user. Event updates are exposed as a MutableSharedFlow type, which is similar to StateFlow and behaves as, in the absence of a subscriber, any posted event will be immediately dropped.

* **Effect** - This is a plain object that signals one-time side-effect actions affecting the UI. For example, it could trigger a navigation action or display a message such as a Toast or SnackBar. Effects are accessible as ChannelFlow, where each event is delivered to a single subscriber. If an event is posted without any subscribers, it will suspend as soon as the channel buffer becomes full, waiting for a subscriber to appear.

Each screen or flow defines its own contract class that outlines all the corresponding core components mentioned above: state content, events, and effects.
