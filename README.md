# Rock–Paper–Scissors Game (Spring Boot + Angular + MongoDB)
## Features

- Register and login securely with hashed passwords  
- Play against a random AI move generator  
- View real-time game results  
- Multilingual support (English / Spanish / German)  
- Audit log system (records each login, game, and action)  
- Prometheus-compatible metrics and structured logging  

##Endpoints
| Method | Endpoint             | Description             |
| ------ | -------------------- | ----------------------- |
| `POST` | `/api/auth/register` | Register a new user     |
| `POST` | `/api/auth/login`    | Login with credentials  |
| `POST` | `/api/game/play`     | Play a round against AI |

##Things That Could Be Changed
| Area               | Description                                                                                 |
| ------------------ | ------------------------------------------------------------------------------------------- |
| **Database**       | Switch from MongoDB to PostgreSQL if strong relational consistency is needed.               |
| **Security**       | Add JWT-based authentication instead of simple session validation.                          |
| **Logging**        | Replace simple logs with centralized monitoring (ELK, Loki, or Graylog).                    |
| **Game logic**     | Introduce configurable difficulty levels or “AI personalities.”                             |
| **Frontend state** | Integrate state management (NgRx or signals) for better scalability.                        |
| **UI design**      | Replace basic HTML/CSS with a Material or Tailwind design system.                           |
| **Testing**        | Expand unit/integration tests using JUnit + Mockito (backend) and Jasmine/Karma (frontend). |

##Features Not Yet Implemented (But could be added in the future easily)
| Feature                         | Description                                                                                                                                                 |
| ------------------------------- | ----------------------------------------------------------------------------------------------------------------------------------------------------------- |
|  **Dynamic frontend interface** | Enhance the UI to include animations, transitions, and real-time updates.                                                                                   |
|  **Metrics tracking**           | Add win rate, loss rate, and total games played per user.                                                                                                   |
|  **User profile page**          | Allow players to view their statistics, profile details, and change their password.                                                                         |
|  **Password management**        | Enable users to change or recover passwords both from their profile and from the login page.                                                                |
|  **Extended testing coverage**  | Add more test cases to cover all new and future developments.                                                                                               |
|  **Smarter AI**                 | Enhance the AI’s decision-making to recognize simple player patterns or tendencies.                                                                         |
|  **Test naming convention**     | All tests follow the naming pattern: `methodName_inputData_expectedResult`.                                                                                 |
|  **Secure password storage*    | User passwords are stored using a strong one-way encryption algorithm (BCrypt), ensuring no plaintext storage — even in the event of a data leak or breach. |

##Things that COULD be added 
| Feature         | Description                                                  |
| --------------- | ------------------------------------------------------------ |
|  User Profiles  | Allow users to upload avatars or edit personal info.         |
|  Leaderboards   | Track and display top players or win ratios.                 |
|  Statistics     | Show player performance charts with historical data.         |
|  More Languages | Expand i18n support to French, Italian, or Japanese.         |
|  Game History   | Add a page to review past matches and outcomes.              |

