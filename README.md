# ScienceFox Assignment

## App Overview

As per given assignment, This app has 2 screens,

- Character Search Screen - This screen lets use search for the character they want using search
  bar. It shows the list of character matched with query and shows the grid of 2 columns if multiple
  character found for same query. Use can navigate to details screen by clicking on the particular
  item from the grid.

- Character Detail Screen - This screen lets use browse through the details of the character they
  want. This screen shows the basic details of character like, name, birth year, height and list of
  films in which the character has appeared.

## Technology Stack

- Kotlin
- Android SDK v29 onwards
- Android Studio Chipmunk

## Application Architecture

This application has been developed using Clean architecture of android application development.
Below is the list of different components and libraries used,

- Jetpack Components -
    1. LiveData - For UI to observe the data changes and update the UI accordingly.
    2. Navigation - To provide the navigation between list and details screen, along with passing
       bundle parameter of character detail object.
    3. Data Binding - For details fragment the databinding is used so the UI will show the latest
       and updated data.
    4. ViewModel - I have used a shared viewmodel for both list and details fragment.
- Kotlin Coroutine - To create lightweight and non blocking background operations like web api
  calls.
- Dagger Hilt - For the dependency injection through the different layer of architecture.
- Retrofit/OkHttp/GSON - To provide support for calling apis, parsing responses and logging the
  details of web apis.
