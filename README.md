# Movie Archive

Movie Archive is a Kotlin Multiplatform open-source project developed by Adnan, leveraging modern
technologies to create
a robust and efficient movie catalogue application.
The project utilizes Jetpack Compose for building a reactive user interface, implements MVVM
architectural pattern with Clean Architecture principles organizes the codebase into multiple
modules for improved scalability and maintainability, and incorporates various libraries such as
Koin, Flow, Coroutine, Ktor, Kotlin Serialization, SQLDelight, and Version Catalog for seamless
development and maintenance.

https://github.com/Adnan9011/movie_archive_kmp/assets/7091803/d91b9e97-26c3-45b3-8864-025334c5628c

## Give a Star!

If you find this project helpful for learning or starting your own solution, we would greatly
appreciate it if you could give it a star. Thank you!

## Features

| Feature                       | Status           |
|-------------------------------|------------------|
| MultiModule                   | ✔️ Implemented   |
| Implement Remote and Database | ✔️ Implemented   |
| Home                          | ✔️ Implemented   |
| Detail                        | ✔️ Implemented   |
| Implement IMDB api            | ✔️ Implemented   |
| Refactor Home                 | ✔️ Implemented   |
| Refactor Celebrity            | ✔️ Implemented   |
| Refactor Detail               | ⚠️ Working on it |
| Search Movie                  | ⚠️ Working on it |
| Unit Test                     | ⚠️ Working on it |

## Tech

### Compose UI:

Utilizes Jetpack Compose Multiplatform for building a modern and reactive user interface.

### MVVM + Clean Architecture:

Implements MVVM architectural pattern with Clean Architecture principles for better separation of
concerns.

### MultiModule:

Organizes the project into multiple modules for improved scalability and maintainability.

### Navigation Component:

Uses precompose for Navigation Component to handle in-app navigation effectively.

### Koin:

Incorporates Koin for dependency injection to facilitate easier app testing and maintenance.

### Flow, Coroutine:

We leverage Kotlin's Flow and Coroutine for asynchronous programming and reactive data streams.

### Ktor:

Implements Ktor for networking capabilities, making API requests and handling responses.

### Kotlin Serialization:

Utilizes Kotlin Serialization for parsing JSON data.

### SQLDelight:

Implements SQLDelight persistence library for local database management.

### Version Catalog:

Utilizes a version catalogue for easy management of library versions and dependencies.

## Getting Started

To build and run the Movie Archive app locally, follow these steps:

Clone the repository: git clone https://github.com/Adnan9011/movie_archive_kmp.git

- To access the API, you need an API key. Please visit https://rapidapi.com/DataCrawler/api/imdb188
  to obtain your own key by signing in through RapidAPI.

Contributions to Movie Archive are welcome! If you'd like to contribute, please follow these
guidelines:

Fork the repository and create your branch from master.
Open an issue to discuss proposed changes or enhancements.
Make your contributions following the project's coding conventions.

License
This project is licensed under the MIT License.