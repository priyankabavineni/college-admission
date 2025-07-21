# **API Documentation: Student Management**

This document provides the details for the endpoints used to manage student data and their applications.

## **1. Save Student**

**Endpoint:**  
`POST /student/save`

### **Input:**
**Request Body (StudentDTO)**  
```json
{
  "sid": 1,              // The student ID (int)
  "sname": "John Doe",    // The student's name (String)
  "email": "john.doe@gmail.com" // The student's email address (must end with "@gmail.com")
}
```

### **Procedure:**
- This endpoint is used to save the provided student data to the database.
- It validates the student's name (must be at least 10 characters) and email (must end with "@gmail.com").
- On successful creation, the student is saved and the response will return the created student details.

### **Output:**
**Response Body**  
```json
{
  "status": "success",    // Indicates the outcome of the request ("success" or "failed")
  "message": "saved successfully",    // A message describing the outcome
  "data": {
    "sid": 1,
    "sname": "John Doe",
    "email": "john.doe@gmail.com"
  } // The saved student details (StudentDTO)
}
```

### **URL**  
`POST http://localhost:8080/student/save`

---

## **2. Get Student Status & Applications**

**Endpoint:**  
`GET /student/status`

### **Input:**
**Query Parameter**  
```plaintext
sid=1   // The student ID (int)
```

### **Procedure:**
- This endpoint fetches the details of a student using their student ID (`sid`).
- It also fetches all applications related to the student and returns both in the response.

### **Output:**
**Response Body**  
```json
{
  "status": "success",    // Indicates the outcome of the request ("success" or "failed")
  "message": "success",   // A message describing the outcome (optional)
  "data": {
    "student": {
      "sid": 1,
      "sname": "John Doe",
      "email": "john.doe@gmail.com"
    },
    "applications": []    // List of applications related to the student (empty if no applications exist)
  }
}
```

### **URL**  
`GET http://localhost:8080/student/status?sid=1`

---
