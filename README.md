# 💰 Expense Tracker

A backend-focused **Expense Tracker application** built using **Spring Boot**, designed to manage, track, and analyze daily expenses efficiently.

This project demonstrates clean backend architecture, REST API design, and database integration using Java and Spring Boot.

---

## 🚀 Features

- ➕ Add new expenses  
- 📅 Filter expenses between date ranges  
- 💰 Calculate total expenses within a time range  
- 🧾 Store and manage expense records in a database  
- 🔍 Clean and scalable REST API design  

---

## 🛠️ Tech Stack

- **Backend:** Spring Boot  
- **Language:** Java  
- **Database:** MySQL  
- **ORM:** JPA / Hibernate  
- **Build Tool:** Maven  
- **API Testing:** Postman  

---

## 📂 Project Structure
```
expense-tracker/
│── src/
│ ├── controller/ # REST Controllers (API layer)
│ ├── service/ # Business Logic
│ ├── repository/ # Database Access Layer
│ ├── model/ # Entity Classes
│
│── pom.xml
│── README.md
```

---

## ⚙️ Setup & Installation

### 1️⃣ Clone the repository
```bash
git clone https://github.com/shauryaveer10/expense-tracker.git
cd expense-tracker
```

### 2️⃣ Configure Database
Update your application.properties file:
```
spring.datasource.url=jdbc:mysql://localhost:3306/expense_db
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```
### 2️⃣ Run the application
```
mvn spring-boot:run
```
