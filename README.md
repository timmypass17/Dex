#  Dex - Pokemon Card Collection App

A card collection app that allows users to store their pokemon card collection onto their devices. Allows users to look up specific cards, entire sets, prices, track set completion, and other additional information.

Dex uses the [Pokémon TCG API](https://docs.pokemontcg.io/) and follows the [MVVM architecture pattern](https://developer.android.com/jetpack/guide) using [Room](https://developer.android.com/training/data-storage/room) & [Retrofit](https://square.github.io/retrofit/) to cache and make network requests.

## Screenshots
![Collection View](https://github.com/timmypass17/dex/blob/main/collection.PNG)
![Card Detail View](https://github.com/timmypass17/dex/blob/main/card_detail.PNG)
![Set View](https://github.com/timmypass17/dex/blob/main/set.PNG)
![Set Detail View](https://github.com/timmypass17/dex/blob/main/set_detail.PNG)
![Search View](https://github.com/timmypass17/dex/blob/main/search.PNG)

## Getting Started (WIP)

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

What things you need to install the software and how to install them

```
Give examples
```

### Installing

A step by step series of examples that tell you how to get a development env running

Say what the step will be

```
Give the example
```

And repeat

```
until finished
```

## Libraries Used

* [Architecture](https://developer.android.com/jetpack/arch/) - A collection of libraries that help you design robust, testable, and
  maintainable apps. Start with classes for managing your UI component lifecycle and handling data
  persistence.
  * [Data Binding](https://developer.android.com/topic/libraries/data-binding/) - Declaratively bind observable data to UI elements.
  * [Lifecycles](https://developer.android.com/topic/libraries/architecture/lifecycle) - Create a UI that automatically responds to lifecycle events.
  * [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - Build data objects that notify views when the underlying database changes.
  * [Navigation](https://developer.android.com/guide/navigation) - Handle everything needed for in-app navigation.
  * [Retrofit](https://square.github.io/retrofit/) - Handles network requests
  * [Room](https://developer.android.com/training/data-storage/room) - Access your app's SQLite database with in-app objects and compile-time checks.
  * [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Store UI-related data that isn't destroyed on app rotations. Easily schedule asynchronous tasks for optimal execution.
* [UI](https://developer.android.com/guide/topics/ui) - Details on why and how to use UI Components in your apps - together or separate
  * [Fragment](https://developer.android.com/guide/components/fragments) - A basic unit of composable UI.
  * [Layout](https://developer.android.com/guide/topics/ui/declaring-layout) - Lay out widgets using different algorithms.
* Third party and miscellaneous libraries
  * [Glide](https://bumptech.github.io/glide/) - Image loading
  * [Palette](https://developer.android.com/training/material/palette-colors) - Dynamic coloring
  * [Parceler](https://developer.android.com/reference/android/os/Parcelable.html) - Serialize Java Objects between Contexts


