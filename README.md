# Music-GPT
Music-GPT

MusicGPT is the leading AI-powered application in the audio domain, designed to help users interact, create, and explore music intelligently. It is built with a modern, scalable, and maintainable architecture to ensure high-quality performance and developer-friendly structure.

Getting Started

To build and run the project locally:

Clone the repository:

git clone https://github.com/kabindra-shrestha/Music-GPT

Open the project in Android Studio (or your preferred IDE for Kotlin Multiplatform).

Build the project using Gradle:

./gradlew build


Run the app on an Android device or emulator.

Architecture Overview

The project follows Clean Architecture combined with MVVM (Model-View-ViewModel) and Repository Pattern to ensure separation of concerns, scalability, and testability.

Folder Structure:

com.kabindra.musicgpt
│
├── data
│   ├── datasources       # Remote and local data sources
│   ├── repositoryimpl    # Implementation of repository interfaces
│
├── domain
│   ├── model             # Core domain models
│   ├── repository        # Repository interfaces
│   ├── usecase           # Business logic encapsulated as use cases
│
├── presentation
│   ├── screen            # Screens / Composables
│   ├── viewmodel         # ViewModels for UI state and events
│   └── components        # Reusable UI components (Text, Button, List, etc.)
│
└── util                  # Common utilities and helpers


Flow:

Screen → ViewModel → UseCase → Repository → RepositoryImpl → DataSources

State Management

The app uses events and state for managing UI interactions and data flow.

Events: User actions or triggers from the UI.

State: UI data that reacts to changes from events or repository updates.

This ensures reactive updates and consistent UI behavior.

Reusable Components

To maintain a clean and modular codebase, the app uses reusable components for:

Lists / LazyColumns

Text Fields & Buttons

Cards & App Bars

Utility functions for formatting, network handling, and UI helpers

These components can be easily reused across different screens and modules, reducing boilerplate and improving maintainability.