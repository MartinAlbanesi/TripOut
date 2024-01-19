<a name="readme-top"></a>

<!-- PROJECT LOGO -->
<br />
<div align="center">
  <a href="https://github.com/MartinAlbanesi/TripOut">
    <img src="https://github.com/MartinAlbanesi/TripOut/blob/develop/img/tripout_logo_full.png" alt="Logo">
  </a>

  <p align="center">
    <h4>Embark on Seamless Journeys: Your Ultimate Trip Planner</h4>
    <br />
  </p>
</div>

<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#prerequisites">Prerequisites</a></li>
        <li><a href="#installation">Installation</a></li>
        <li><a href="#setup-google-api-key">Setup Google API Key</a></li>
      </ul>
    </li>
    <li><a href="#contributing">Contributing</a></li>
    <li><a href="#contact">Contact</a></li>
  </ol>
</details>

<!-- ABOUT THE PROJECT -->
## About The Project

![Trip Out](https://github.com/MartinAlbanesi/TripOut/blob/feature/home/img/Mockup.png)

There are many ways to plan your next trip; however, none of them offer you the freedom to organize them in a simple and concise way. We wanted to create an app for all those travelers who would like to save their adventures to revisit them later.

Here's why:
* Your time thinking about the next place to visit is important and should not be wasted
* You should have a very intuitive way to check all your planned trips
* You can share it with other users who wish to take the same trip

We want to offer you the best experience to organize all your trips, that's why we are going to keep adding more features in the future and improving what we currently have. You may also suggest changes by forking this repo and creating a pull request or opening an issue.

<p align="right">(<a href="#readme-top">back to top</a>)</p>

### Screenshot

| Home | Map | Setings | Create Trip |
|-|-|-|-|
| ![Home](https://github.com/GabrielGomezGG/TripOut/blob/feature/home/img/trip_out_home.gif) | ![Map](https://github.com/GabrielGomezGG/TripOut/blob/feature/home/img/trip_out_map.gif) | ![Settings](https://github.com/GabrielGomezGG/TripOut/blob/feature/home/img/trip_out_config.gif) | ![Create Trip](https://github.com/GabrielGomezGG/TripOut/blob/feature/home/img/trip_out_create_trip.gif) |

### Built With

<details>
  <summary>Android</summary>

* [Android Studio](https://developer.android.com/studio?hl=es-419)
* [Kotlin](https://kotlinlang.org/)
* [Gradle](https://gradle.org/)
* [MVVM](https://developer.android.com/topic/libraries/architecture/viewmodel?hl=es-419)
* [Dagger Hilt](https://developer.android.com/training/dependency-injection/hilt-android?hl=es-419)
* [Repository](https://developer.android.com/codelabs/basic-android-kotlin-training-repository-pattern#3)
* [Coroutines](https://developer.android.com/kotlin/coroutines?hl=es-419)
* [Live Data](https://developer.android.com/topic/libraries/architecture/livedata?hl=es-419)
* [Flow](https://kotlinlang.org/docs/flow.html)
* [Intent](https://developer.android.com/guide/components/intents-filters?hl=es-419)
* [Room](https://developer.android.com/training/data-storage/room?hl=es-419&authuser=1)
* [Junit](https://junit.org/junit5/)
* [Datastore Preferences](https://developer.android.com/topic/libraries/architecture/datastore?hl=es-419)
* [Locations](https://developer.android.com/training/location?hl=es-419)

</details>

<details>
  <summary>Jetpack Compose</summary>

* [Android Compose](https://developer.android.com/jetpack/compose?hl=es-419)
* [Google Map](https://developers.google.com/maps/documentation/android-sdk/maps-compose?hl=es-419)
* [Coil](https://coil-kt.github.io/coil/compose/)
* [Animations](https://developer.android.com/jetpack/compose/animation?hl=es-419)
* [Material 3](https://m3.material.io/)
* [Lottie](https://lottiefiles.com/)
* [Accompanist-Permissions](https://google.github.io/accompanist/permissions/)
* [Splash Screen](https://developer.android.com/about/versions/12/features/splash-screen)
* [Navegation](https://developer.android.com/jetpack/compose/navigation?hl=es-419)
* [Horizontal Pager](https://developer.android.com/jetpack/compose/layouts/pager)

</details>

<details>
    <summary>Libraries</summary>   

* [Retrofit](https://square.github.io/retrofit/)
* [Mockk](https://mockk.io/)
* [MockWebServer](https://github.com/square/okhttp/tree/master/mockwebserver)
* [Joda Time](https://www.joda.org/joda-time/)
* [ZXING](https://github.com/journeyapps/zxing-android-embedded)

</details>

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- GETTING STARTED -->
## Getting Started

### Prerequisites
* Android Studio (lastest version)
* Google API Key

### Installation

There are a few ways to open the project.

* #### Android Studio

1. `Android Studio` -> `File` -> `New` -> `From Version control` -> `Git`
2. Enter `https://github.com/MartinAlbanesi/TripOut.git` into URL field and press `Clone` button

* #### Command-line And Android Studio

1. Run `git clone https://github.com/MartinAlbanesi/TripOut.git` command to clone the project
2. Open `Android Studio` and select `File | Open...` from the menu. Select the cloned directory and press `Open` button

### Setup Google API Key
3. If you don't have a Google API Key, follow the steps at [Google Maps Platform Documentation](https://developers.google.com/maps/documentation/javascript/get-api-key?hl=en#create-api-keys)
5. Enter your API in `local.properties`
   ```kotlin
   MAPS_API_KEY = "ENTER YOUR API"
   ```

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- CONTRIBUTING -->
## Contributing

Contributions are what make the open source community such an amazing place to learn, inspire, and create. Any contributions you make are **greatly appreciated**.

If you have a suggestion that would make this better, please fork the repo and create a pull request. You can also simply open an issue with the tag "enhancement".
Don't forget to give the project a star! Thanks again!

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

<p align="right">(<a href="#readme-top">back to top</a>)</p>

<!-- CONTACT -->
## Contact

* Martín Albanesi - [Linkedin](https://www.linkedin.com/in/martin-albanesi/) - [Github](https://github.com/MartinAlbanesi) - martinalbanesi89@gmail.com
* Gabriel Gomez - [Linkedin](https://www.linkedin.com/in/gabrielgomezgg/) - [Github](https://github.com/GabrielGomezGG) - gabrielgomezgg1997@gmail.com

Project Link: [https://github.com/MartinAlbanesi/TripOut](https://github.com/MartinAlbanesi/TripOut)

<p align="right">(<a href="#readme-top">back to top</a>)</p>
