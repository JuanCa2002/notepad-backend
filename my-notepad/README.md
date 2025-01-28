# 🗒️ My Notepad (Backend)

My Notepad is a simple note-taking application designed to help users organize their ideas, tasks, or information using **categories (tags)**, **notes**, and **users**. The backend is developed with **Spring Boot** and features API documentation automatically generated with **Swagger**.

![my-note-pad-logo](https://github.com/user-attachments/assets/e9281588-494d-42fb-a324-e41db4a157ed)

## 📚 Table Of Contents

* [Title and Cover Image](#-e-commerce-nexsys-platzy-fake-api)

* [Features](#-features)

* [Badges](#badges)

* [Installation](#-installation)

* [Documentation](#-documentation)

* [License](#-license)
## ✨ Features

1. **Users**:
   - Create user accounts for secure access.
   - Log in to manage notes and categories.

2. **Categories (Tags)**:
   - Create and organize custom categories.
   - Assign categories to notes for better organization.

3. **Notes**:
   - Create, edit, and delete notes.
   - Associate notes with categories.

## 🏅 Badges

- ![Java](https://img.shields.io/badge/Java-17-blue)
- ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.5-green)
- ![PostgreSQL](https://img.shields.io/badge/PostgreSQL-14-blue)
- ![Swagger](https://img.shields.io/badge/Swagger-2.2.0-brightgreen)
- ![Lombok](https://img.shields.io/badge/Lombok-1.18.26-orange)
- ![MapStruct](https://img.shields.io/badge/MapStruct-1.5.5-yellow)


## 🔧 Installation

Follow these steps to run the project locally:

### Prerequisites
- **Java 17** or higher installed.
- **Maven** installed.
- A running **PostgreSQL** instance.

### Steps
1. Clone this repository and move to the directory:
   ```
   git clone https://github.com/hirelens-challenges/TorresBeltran-97eb43.git
   cd my-notepad/
   cd backend/
   cd my-notepad/
   ```
2. Build the project:
   ```
   mvn clean install 
   ```
3. Run the project:
   ```
   mvn spring-boot:run -Dspring-boot.run.profiles=dev
   ```
4. Access the application:
   
   - The application will be available at: http://localhost:2208 by default.
   - Swagger documentation will be available at: http://localhost:2208/api/v1/my-notepad/swagger-ui/index.html.

    
## 📖 Documentation

The API documentation is available at the following URL:

- Deployed Environment: https://notepad-backend-production-3a85.up.railway.app/api/v1/my-notepad/swagger-ui/index.html#


## ⚖️ License

This software is proprietary and confidential. Unauthorized use, modification, or distribution is strictly prohibited. All rights reserved.
