# UniHack 2024 - Android Template - XML View

Template for Android projects in UniHack 2024 using Android XML View

## Architecture
- This project uses MVVM architecture based on [Google's recommended architecture](https://developer.android.com/topic/architecture/recommendations).

## What used in this project?
- MDC (Material Design Components) via Android XML View
- Jetpack ViewModel and more Jetpack libraries
- Kotlin Coroutines
- Jetpack 
- Retrofit
- Moshi for JSON Serialization
- Hilt for Dependency Injection
- Coil
- Jetpack Navigation

## How to use this template?
- Clone this repository
```shell
git clone https://github.com/dscdut/android_xml_boilerplate.git
```
- Change the package name if need
- Copy `secret.example.properties` to `secret.properties`, this file will store BASE URL, api key, etc.
- If you have more than secret variable, please add more in `buildConfigField` in `app/build.gradle` file
## Contribute
- Feel free to contribute to this project by creating a pull request
