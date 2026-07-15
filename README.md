# Pro-Expense

Pro-Expense is a modern Android application designed to track and manage personal expenses efficiently. Built with the latest Android development practices, it focuses on performance, scalability, and a seamless user experience.

## 🚀 Core Technologies

- **[Kotlin](https://kotlinlang.org/)**: The primary programming language, leveraging modern features like Coroutines and Flow.
- **[Jetpack Compose](https://developer.android.com/jetpack/compose)**: A modern toolkit for building native UI using a declarative approach.
- **[Hilt (Dagger)](https://developer.android.com/training/dependency-injection/hilt-android)**: Dependency injection for better modularity and testability.
- **[Room Database](https://developer.android.com/training/data-storage/room)**: A robust abstraction layer over SQLite for local data persistence.
- **[Kotlin Coroutines & Flow](https://developer.android.com/kotlin/coroutines)**: For managing asynchronous tasks and reactive data streams.
- **[Jetpack Navigation](https://developer.android.com/guide/navigation)**: Handling navigation between different screens within a Single Activity architecture.

## 🏗 Project Structure

The project follows the **Clean Architecture** principles, ensuring a separation of concerns and making the codebase more maintainable and testable.

### 📂 Directory Structure
```text
app/src/main/java/com/example/pro_expense/
├── data/                  # Data Layer
│   ├── local/             # Room DB, DAOs, Entities
│   ├── mapper/            # Data-Domain Mappers
│   └── ExpenseRepoImpl    # Repository Implementation
├── domain/                # Domain Layer (Business Logic)
│   ├── model/             # Business Models
│   ├── repo/              # Repository Interfaces
│   └── usecase/           # UseCases (Single Action)
├── ui/                    # UI Layer
│   ├── components/        # Reusable Compose Widgets
│   ├── expenselist/       # List Feature (Screen, VM, State)
│   ├── logexpense/        # Entry Feature (Screen, VM, State)
│   ├── mapper/            # Domain-VO Mappers
│   └── vo/                # View Objects
└── navigation/            # Compose Navigation Setup
```

## 🧪 Testing

The project emphasizes quality through comprehensive unit testing:
- **MockK**: Used for mocking dependencies in tests.
- **Turbine**: A small library for testing Kotlin Flow.
- **Coroutines Test**: Provides utilities for testing asynchronous code predictably.

Run unit tests via:
```bash
./gradlew test
```

## 🛠 Setup & Installation

1. Clone the repository.
2. Open the project in **Android Studio (Ladybug or newer)**.
3. Ensure you have **JDK 21** configured.
4. Sync the project with Gradle files.
5. Run the `app` module on an emulator or physical device.

---
