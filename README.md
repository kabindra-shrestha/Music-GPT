<p align="center"> 
   <img height="250" src="art/app_icon.png"/> 
</p>

<h1 align="center"> MusicGPT </h1>

MusicGPT is the leading AI-powered application in the audio domain, designed to help users interact,
create, and explore music intelligently. It is built with a modern, scalable, and maintainable
architecture to ensure high-quality performance and developer-friendly structure.

## Architecture

A well planned architecture is extremely important for an app to scale and all architectures have
one common goal- to manage complexity of your app. This isn't something to be worried about in
smaller apps however it may prove very useful when working on apps with longer development lifecycle
and a bigger team.

The project follows Clean Architecture combined with MVVM (Model-View-ViewModel) and Repository
Pattern to ensure separation of concerns, scalability, and testability.

<p align="center"><img src="art/clean_arch.jpeg" alt="Clean Architecture"></p>

## Layers

### Project Structure
<p align="center"><img src="art/folder_structure.png" alt="Project Structure" width="500"></p>

### App
The ```app``` layer is responsible for common and general properties.

- __component__: This is responsible for general view.

- __View__: This is responsible for common view components that using app.

- __theme__: Defines themes, colors, fonts and resource files.

### Data
The ```data``` layer is responsible for selecting the proper data source for the domain layer. It contains the implementations of the repositories declared in the domain layer.

Components of data layer include:
- __model__

  -__dto__: Defines dto of ui model, also perform data transformation between ```domain```, ```response``` and ```entity``` models.

  -__local__: Defines the schema of SQLite database.

  -__remote__: Defines POJO of network responses.

- __local__: This is responsible for performing caching operations using [Room].

- __remote__: This is responsible for performing network operations eg. defining API endpoints using [Retrofit or Ktor].

- __repository__: Responsible for exposing data to the domain layer.

### Domain
This is the core layer of the application. The ```domain``` layer is independent of any other layers thus ] domain business logic can be independent from other layers.This means that changes in other layers will have no effect on domain layer eg.  screen UI (presentation layer) or changing database (data layer) will not result in any code change withing domain layer.

Components of domain layer include:
- __usecase__: They enclose a single action, like getting data from a database or posting to a service. They use the repositories to resolve the action they are supposed to do. They usually override the operator ```invoke``` , so they can be called as a function.

### Presentation
The ```features``` layer contains components involved in showing information to the user. The main part of this layer are the views(activity, compose) and ViewModels.