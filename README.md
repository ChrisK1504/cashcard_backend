
---

# Project Name: CashCard

## Description
This is a backend service built using **Spring Boot** that provides functionalities for managing cash cards. It includes RESTful APIs for handling operations like creating, updating, and querying cash card details.
It was developed following TDD (Test-Driven Development) principles. The workflow was as followed:
1. Write a new Test for some functionality (Example: A new Endpoint), which fails.
2. Implement Code to make the Test pass.
3. Refactor where possible (Make sure the tests still pass).
This is the essential cycle of the Red-Green-Refactor development cycle.

## Technologies
RESTful APIs were implemented using @RestController. As a database, the in-memory embedded H2 Database was used. As such the database is refreshed to a new state every time the application
is ran. Spring JDBC was used as a simple ORM. Authentication and authorization measures were also implemented using Spring Security starter library. 

---

# API Contract for CashCard Service

## Base URL
`/cashcards`

This API provides operations for managing cash cards, including fetching, creating, updating, and deleting cash card information. It uses the `Principal` object to ensure that actions are tied to the authenticated user (owner of the cash card).

### Headers
- **Authorization:** (Optional) Include an authorization token if applicable.

### Cross-Origin
The API allows cross-origin requests from:  
`http://localhost:5173`
To allow communication with the Front-End (In Development).

---

### 1. **Get CashCard by ID**

**Endpoint:**  
`GET /cashcards/{id}`

**Description:**  
Fetch a specific cash card by its ID for the authenticated user.

**Path Parameter:**
- `id` (Long): The ID of the cash card to fetch.

**Response:**
- `200 OK`: Returns the cash card details.
  ```json
  {
    "id": Long,
    "amount": Decimal,
    "owner": String
  }
  ```
- `404 Not Found`: If the cash card is not found.

---

### 2. **Get All CashCards**

**Endpoint:**  
`GET /cashcards`

**Description:**  
Fetch all cash cards for the authenticated user with pagination.

**Query Parameters:**
- `page` (int): Page number.
- `size` (int): Page size.
- `sort` (String, optional): Sorting criteria (default is by `amount` in ascending order).

**Response:**
- `200 OK`: Returns a list of cash cards.
  ```json
  [
    {
      "id": Long,
      "amount": Decimal,
      "owner": String
    }
  ]
  ```

---

### 3. **Create a New CashCard**

**Endpoint:**  
`POST /cashcards`

**Description:**  
Create a new cash card for the authenticated user.

**Request Body:**
- `amount` (Decimal): The amount to be associated with the cash card.

**Example Request:**
```json
{
  "amount": 100.00
}
```

**Response:**
- `201 Created`: The cash card is created, with a location header pointing to the new cash card.
- **Location Header:** `/cashcards/{id}` (The ID of the newly created cash card).

---

### 4. **Update an Existing CashCard**

**Endpoint:**  
`PUT /cashcards/{requestedId}`

**Description:**  
Update the amount on an existing cash card for the authenticated user.

**Path Parameter:**
- `requestedId` (Long): The ID of the cash card to update.

**Request Body:**
- `amount` (Decimal): The new amount for the cash card.

**Example Request:**
```json
{
  "amount": 150.00
}
```

**Response:**
- `204 No Content`: The cash card is successfully updated.
- `404 Not Found`: If the cash card is not found.

---

### 5. **Delete a CashCard**

**Endpoint:**  
`DELETE /cashcards/{id}`

**Description:**  
Delete a cash card by its ID for the authenticated user.

**Path Parameter:**
- `id` (Long): The ID of the cash card to delete.

**Response:**
- `204 No Content`: The cash card is successfully deleted.
- `404 Not Found`: If the cash card is not found.

---

### Summary of Endpoints:

| Method | Endpoint               | Description                        |
|--------|-------------------------|------------------------------------|
| GET    | `/cashcards/{id}`        | Get cash card by ID                |
| GET    | `/cashcards`             | Get all cash cards with pagination |
| POST   | `/cashcards`             | Create a new cash card             |
| PUT    | `/cashcards/{requestedId}`| Update an existing cash card       |
| DELETE | `/cashcards/{id}`        | Delete a cash card by ID           |

---

## Prerequisites
- **Java 17** or later
- **Gradle** (if you're not using the included wrapper)
- **Spring Boot** (included in dependencies)

## Installation
1. Clone the repository:
    ```bash
    git clone https://github.com/your-username/cashcard-backend.git
    ```
2. Navigate to the project directory:
    ```bash
    cd cashcard-backend
    ```
3. Build the project:
    ```bash
    ./gradlew build
    ```

## Usage
1. Run the application:
    ```bash
    ./gradlew bootRun
    ```
2. The application will start on `http://localhost:8080`.

## Testing
To run tests, execute the following command:
```bash
./gradlew test
```

## Dependencies
The project uses the following dependencies (based on `build.gradle`):
- Spring Boot Starter Web
- Spring Boot Starter Test
- Spring Boot Starter Security
- Spring Boot H2 Databse
- Spring JDBC

---
