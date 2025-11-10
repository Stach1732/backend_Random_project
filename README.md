# Rock–Paper–Scissors Game (Spring Boot + Angular + MongoDB)
##Endpoints
| Method | Endpoint             | Description             |
| ------ | -------------------- | ----------------------- |
| `POST` | `/api/auth/register` | Register a new user     |
| `POST` | `/api/auth/login`    | Login with credentials  |
| `POST` | `/api/game/play`     | Play a round against AI |


##Features implemented:
| Area | Feature | Description |
|------|----------|-------------|
| **Backend – Core** | Game Logic (AI vs Player) | Functional game service where the user plays Rock–Paper–Scissors against a randomly generated AI move. |
|  | Random AI | The AI opponent makes moves randomly without learning or prediction. |
|  | GameRecord persistence | Each game is saved in MongoDB with player move, CPU move, result, and timestamp. |
| **Backend – Authentication** | User registration endpoint (`/api/auth/register`) | Allows users to register with username, password, email, and level. Passwords are stored securely with BCrypt hashing. |
|  | Login endpoint (`/api/auth/login`) | Authenticates users by verifying their credentials against MongoDB with BCrypt password matching. |
|  | BCrypt encryption | All passwords are hashed using BCrypt before being stored — no plaintext credentials exist in the database. |
| **Backend – Auditing & Logging** | Audit system | Each login and key user action is logged as an `AuditEvent` in MongoDB, including type, user, and timestamp. |
|  | AuditEventRepository (Mongo) | Stores all audit entries automatically into the `audit_events` collection. |
|  | Structured logging | Loggers report key lifecycle events (service calls, audit saves, authentication steps). |
| **Backend – Database** | MongoDB integration | The project fully uses MongoDB via `spring-boot-starter-data-mongodb`, with collections auto-generated. |
|  | Repositories migrated to Mongo | Both `AuditEventRepository` and `GameRecordRepository` extend `MongoRepository`. |
| **Backend – Testing** | JUnit + Mockito tests | Unit and integration tests for authentication and game logic. |
|  | Naming pattern: `methodName_inputData_expectedResult` | All test cases follow this naming standard for clarity and consistency. |
| **Frontend – Structure** | Angular 20+ standalone components | Application built with modern Angular standalone architecture (no NgModules). |
|  | Routing system | Functional routing between Login, Register, and Game pages using `provideRouter()`. |
|  | Dynamic API communication | Frontend communicates with backend endpoints (`/api/auth/*` and `/api/game/play`) via Angular `HttpClient`. |
| **Frontend – Game** | Interactive buttons | Players can click Rock, Paper, or Scissors to play rounds, and the result message updates dynamically. |
| **Frontend – Internationalization (i18n)** | Multilingual support (English, Spanish, German) | The entire UI supports three languages using `@ngx-translate/core` and `@ngx-translate/http-loader`. |
|  | Language Switcher component | Allows switching between English, Spanish, and German from any page (Login, Register, Game). |
|  | JSON translation files | Separate translation files for each language located in `assets/i18n/en.json`, `es.json`, and `de.json`. |
| **Infrastructure & Configuration** | MongoDB configuration in `application.yml` | Uses `spring.data.mongodb.uri` instead of SQL datasource (H2 removed). |
|  | Application logging enabled | Configured to log key backend lifecycle steps for debugging and monitoring. |
| **General & Best Practices** | Clean architecture | Separation between controller, service, repository, and model layers. |
|  | Entity migration from JPA to MongoDB | `@Entity` replaced with `@Document` in all models (`AuditEvent`, `GameRecord`). |
|  | Proper repository interfaces | `JpaRepository` replaced with `MongoRepository` and IDs switched from `UUID` to `String`. |
|  | Code modularization | Clear separation between Auth, Game, and Audit logic. |
|  | Proxy configuration | Angular proxy file redirects `/api` calls from port 4200 to backend port 8080. |

##Features Not Yet Implemented (But could be added in the future easily)
| Feature                         | Description                                                                                                                                                 |
| ------------------------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------- |
|  **Dynamic frontend interface** | Enhance the UI to include animations, transitions, and real-time updates.                                                                                   |
|  **Metrics tracking**           | Add win rate, loss rate, and total games played per user.                                                                                                   |
|  **User profile page**          | Allow players to view their statistics, profile details, and change their password.                                                                         |
|  **Password management**        | Enable users to change or recover passwords both from their profile and from the login page.                                                                |
|  **Extended testing coverage**  | Add more test cases to cover all new and future developments.                                                                                               |
|  **Smarter AI**                 | Enhance the AI’s decision-making to recognize simple player patterns or tendencies.                                                                         |

##Things that COULD be added 
| Feature         | Description                                                  |
| --------------- | ------------------------------------------------------------ |
|  User Profiles  | Allow users to upload avatars or edit personal info.         |
|  Leaderboards   | Track and display top players or win ratios.                 |
|  Statistics     | Show player performance charts with historical data.         |
|  More Languages | Expand i18n support to French, Italian, or Japanese.         |
|  Game History   | Add a page to review past matches and outcomes.              |


Ok, now small chat. 
There are many of these features or things that could have been added or done different. But I had a lot of work I had to do so i only could work a couple of evenings on this project.
It's not much work but it's honest work.

Enjoy it


## Installation and Startup Guide

### 1. Prerequisites

Before running the application, make sure you have the following installed:

| Tool | Minimum Version |
|------|------------------|
| **Java JDK** | 17 or higher |
| **Maven** | 3.8+ |
| **Node.js** | 18 or higher |
| **npm** | 9 or higher |
| **MongoDB** | 6+ |
| **Git** | Latest |

### 2. Run the Backend (Spring Boot)
## Run the backend

cd rps-backend
mvn clean install
mvn spring-boot:run

Once running, the backend will be available at: http://localhost:8080

