# Reward Calculator API

## 📌 Overview

The **Reward Calculator API** is a Spring Boot application that calculates reward points for customers based on their purchase transactions over a given time period.

This project demonstrates backend development skills including REST API design, Java 8 Stream API, layered architecture, unit testing, and Git best practices.

---

## 🎯 Business Logic

A customer earns reward points based on transaction amount:

- **2 points for every dollar spent over $100**
- **1 point for every dollar spent between $50 and $100**
- **0 points for $50 or below**

### Example:
A transaction of **$120**:
- (120 - 100) × 2 = 40 points
- (100 - 50) × 1 = 50 points  
➡️ Total = **90 points**

---

## 🚀 Features

- RESTful API for reward calculation
- Monthly reward breakdown
- Total reward points calculation
- Customer transaction history
- Input validation
- Exception handling
- Clean layered architecture
- Unit tests (Service + Controller)

---

## 🛠️ Tech Stack

- Java 8
- Spring Boot 2.7.18
- Maven
- Spring Web
- JUnit 5
- Mockito
- SLF4J Logging

---

## 📡 API Endpoint
GET /api/rewards/{customerId}?months={months}

### Request Example
GET http://localhost:8080/api/rewards/101?months=3

### Response Example

```json
{
  "customerId": 101,
  "customerName": "John Smith",
  "email": "john@gmail.com",
  "requestedMonths": 3,
  "monthlyRewards": [
    {
      "month": "APRIL",
      "totalTransactions": 2,
      "rewardPoints": 120
    },
    {
      "month": "MAY",
      "totalTransactions": 1,
      "rewardPoints": 90
    }
  ],
  "totalRewardPoints": 350,
  "transactions": [
    {
      "transactionId": 1001,
      "amount": 120.0,
      "transactionDate": "2026-04-10",
      "rewardPoints": 90
    }
  ]
}

### Get Customer Rewards

