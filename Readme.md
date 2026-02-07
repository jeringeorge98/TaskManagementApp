# Task Management App
A Task management app .

## 🚀 Tech Stack

- **UI:** [Jetpack Compose](https://developer.android.com/jetpack/compose) 
- **Persistence:** [Android Room](https://developer.android.com/training/data-storage/room) 
- **Language:** [Kotlin](https://kotlinlang.org/)
- **Asynchronous Programming:** [Kotlin Coroutines & Flow](https://kotlinlang.org/docs/flow.html) – For handling asynchronous operations and reactive datastreaming
- **Dependency Injection:** Manual DI with Factory pattern for ViewModel creation
- **Testing:** [JUnit](https://junit.org/), 
## 🏗️ Architecture

The app follows the **MVVM (Model-View-ViewModel)** architectural pattern combined with the **Repository Pattern** to ensure separation of concerns and maintainable code.

### MVVM Architecture Implementation:

#### **View Layer (UI)**
- Built entirely with **Jetpack Compose** for modern, declarative UI
- Observes state from ViewModels using `collectAsState()`
- Located in `ui/screens/` and `ui/components/`

#### **ViewModel Layer** 
- Acts as the bridge between the UI and Data layer
- Uses `StateFlow` and `Flow.combine()` for reactive state management
- Handles business logic such as input validation, task filtering, and search functionality
- Manages UI state and survives configuration changes

#### **Model Layer**
- **Domain Models:** Pure Kotlin data classes representing business entities (`Tasks.kt`)
- **Room Entities:** Database representations with Room annotations (`TaskEntity.kt`)
- **Mappers:** Convert between domain models and database entities for clean separation

### Repository Pattern (Single Source of Truth)

The app implements the **Repository Pattern** to centralize data operations and establish the **database as the single source of truth**:

- **TaskRepositoryImpl:** Concrete implementation that handles Room database operations
- **Flow-based Architecture:** Repository exposes `Flow<List<Tasks>>` from Room, ensuring automatic UI updates when data changes
- **Data Flow:** UI ← ViewModel ← Repository ← Room Database


## 🛠️ How to Run the App

### Prerequisites
- Android Studio 
- Android SDK API 24 (Android 7.0) or higher and JDK 11.0 or higher
- Kotlin 1.9.0 or later

### Running the Application

1. **Clone the repository:**
   ```bash
   git clone <repository-url>
   cd TaskManagementApp
   ```

2. **Open in Android Studio:**
   - Launch Android Studio
   - Select "Open an Existing Project"
   - Navigate to the project directory and open it

3. **Build the project:**
   ```bash
   ./gradlew build
   ```

4. **Run the app:**
   - Connect an Android device or start an emulator
   - Click the "Run" button in Android Studio.

## 🧪 Running Tests

### Unit Tests
Run all unit tests including ViewModel and utility function tests:
```bash
./gradlew test
```

**Test Structure:**
- Key test files:
  - `HomeViewModelTest.kt` - Tests ViewModel logic and state management
  - `UtilsTest.kt` - Tests utility functions

## 🚀 Future Improvements

The following improvements would be done by me in the future to enhance the app :

### Enhanced Task Status Management
- **Multiple Status Fields:** Expand beyond simple "done/not done" to include:
  - Priority levels (High, Medium, Low)
  - Category like Work,Home etc
  - Progress tracking (Not Started, In Progress, Completed, Blocked)

- **Advanced Filtering:** Implement comprehensive filtering options:
  - Filter by priority level
  - Filter by category
  - Filter by completion status

### Deadline and Notification System
- **Task Deadlines:** Add deadline functionality with:
  - Date and time picker for deadline selection
  - Would include Notifications once the deadline approaches.


    