A simple Java CRUD application to create a wishlist (to-do list).

---

## 🚀 Features

- Create wish
- Read wish
- Edit wish
- Delete wish

---

## 🧰 Tech Stack

- **Backend**: Java, Spring Boot  
- **Database**: MySQL  
- **ORM**: Spring Data JPA  

---

## 📦 Installation

### 1. Download/Clone the repository

### 2. Configure MySQL
Create a MySQL database and update your application.properties:

```bash
spring.datasource.url=jdbc:mysql://localhost:3306/your_db_name
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### 3. Run the application

---

## 📫 API Endpoints

| Method | Endpoint                  | Description                   |
| ------ | ------------------------- | ----------------------------- |
| POST   | `/api/v1/wishes`          | Create a wish in the list     |
| GET    | `/api/v1/wishes`          | Get all wishes                |
| PUT    | `/api/v1/wishes/{id}`     | Update a existing wish        |
| DELETE | `/api/v1/wishes/{id}`     | Delete a wish from the list   |

---

## 🧪 Testing
You can use Postman to test the APIs.
