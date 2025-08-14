# news_management
News Management REST API is a backend application that implements news, comments, tags, and authors management via REST API. Supports CRUD operations, flexible search, sorting, pagination, and query validation. The project is developed using Java, Spring Boot, Hibernate, and a relational database.

# 📰 News Management REST API

A backend system for managing **News**, **Comments**, **Tags**, and **Authors** with comprehensive REST API support.  
The project provides CRUD operations, advanced search, sorting, pagination, and request validation, following best practices for RESTful APIs.

---

## 🚀 Features
- **CRUD operations** for:
  - News
  - Comments
  - Tags
  - Authors
- **Search & Filters**:
  - Search news by tags (IDs or names), author name, part of title or content
  - Search comments by news ID or comment ID
- **Sorting**:
  - Sort results by `Created` or `Modified` date (ASC/DESC)
  - Default sorting: `Created DESC`
- **Pagination** for all GET endpoints
- **Partial Updates**: only provided fields are updated
- **Request Validation**:
  - Handles invalid URLs, missing or wrong parameters, and incorrect constant values
  - Ignores case sensitivity for constants and parameters
  - Trims whitespace in parameter values
- **API Versioning** support
- **HATEOAS** links for navigation between resources
- **Swagger Documentation** for API endpoints

---

## 🛠️ Tech Stack
- **Java**
- **Spring Boot**
- **Spring Data JPA / Hibernate**
- **REST API**
- **HATEOAS**
- **Swagger**
- **PostgreSQL** (or any relational DB)
- **Maven** (or Gradle)

---

## 📚 API Endpoints Overview

### News
- `GET /news` — get all news with optional filters and sorting
- `GET /news/{id}` — get news by ID
- `POST /news` — create news (creates new tags/authors if needed)
- `PUT /news/{id}` — update news (partial update supported)
- `DELETE /news/{id}` — delete news

### Comments
- `GET /news/{newsId}/comments` — get comments for specific news
- `GET /comments/{id}` — get comment by ID
- `POST /comments` — create comment
- `PUT /comments/{id}` — update comment
- `DELETE /comments/{id}` — delete comment

### Tags & Authors
- `GET /tags`, `GET /tags/{id}` — retrieve tags
- `GET /authors`, `GET /authors/{id}` — retrieve authors
- `GET /news/{newsId}/tags` — get tags for specific news
- `GET /news/{newsId}/authors` — get the author for specific news
- `GET /authors/news-count` — get authors with the number of news written

---

## ⚙️ Installation & Run
```bash
# Clone the repository
git clone https://github.com/yourusername/news_management_iyembergen.git

# Navigate to project directory
cd news_management_iyembergen

# Build the project
mvn clean install

# Run the project
mvn spring-boot:run
