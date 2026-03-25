# 💸 AI-Powered Expense Tracker

An intelligent expense tracking backend built with **Spring Boot** that leverages **Google Gemini AI** to automatically categorize expenses and generate smart financial insights.

---

## 🚀 Features

* ✅ Add, delete, and fetch expenses
* 🤖 **AI-based expense categorization** (Food, Travel, Shopping, etc.)
* 🧠 **AI-generated financial insights & summaries**
* 📊 Category-wise expense breakdown
* 📈 Percentage-based spending distribution
* 📅 Date range filtering
* 💰 Total expense calculation

---

## 🧠 AI Capabilities

* Automatically categorizes expenses using **Google Gemini API**
* Generates smart insights like:

  * High spending detection
  * Category dominance
  * Suggestions for saving money

---

## 🛠️ Tech Stack

* **Backend:** Spring Boot, Java
* **Database:** (Add yours: MySQL / PostgreSQL / H2)
* **AI Integration:** Google Gemini API
* **Build Tool:** Maven
* **Validation:** Jakarta Validation

---

## 📂 Project Structure

```
expense-tracker/
│── controller/     # REST APIs
│── service/        # Business logic
│── ai/             # Gemini integration
│── repository/     # Database layer
│── entity/         # Models
│── dto/            # Request/Response DTOs
```

---

## 📌 Sample API

### Create Expense

POST `/expenses`

```json
{
  "title": "Swiggy order",
  "amount": 500,
  "category": "FOOD",
  "date": "2026-03-25",
  "note": "Dinner"
}
```

---

## 📊 Sample Response (Analytics)

```json
{
  "totalExpense": 121320.00,
  "topCategory": "FOOD",
  "categoryBreakdown": {
    "FOOD": 120120.00,
    "OTHER": 1200.00
  },
  "summary": "You are spending heavily on food. Consider reducing online orders to save more."
}
```

---

## ⚡ Future Enhancements

* 📈 Monthly trend analysis
* ⚠️ Anomaly detection in spending
* 📊 Frontend dashboard (React)
* 🔔 Smart alerts & recommendations

---

## 💡 Key Highlights

* Built a **real-world AI-integrated backend system**
* Designed **clean architecture** (Controller → Service → AI Client)
* Implemented **fault-tolerant AI fallback mechanisms**

---

## 📬 Author

**Shaurya Veer Singh Patial**

---

⭐ If you found this useful, consider giving it a star!
