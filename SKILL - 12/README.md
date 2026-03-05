# Full-Stack CRUD Application (React + Spring Boot)

This repository contains a student management system with full CRUD operations.

## Project Structure

- `frontend/` → React application (Vite + Axios)
- `backend/` → Spring Boot REST API

## Backend (Spring Boot)

Implemented endpoints:

- `POST /students` → Add a new student
- `GET /students` → Retrieve all students
- `PUT /students/{id}` → Update an existing student
- `DELETE /students/{id}` → Delete a student by ID

Backend follows layered architecture:

- Controller: `StudentController`
- Service: `StudentService`, `StudentServiceImpl`
- Repository: `StudentRepository`
- Entity: `Student`

## Frontend (React)

Components:

- `StudentForm` → Add and update form (`name`, `email`, `course`)
- `StudentList` → Student table with Update/Delete actions

Features:

- Uses Axios for API calls
- Prefills form for updates
- Clears form after successful add/update
- Updates UI immediately after add/update/delete

## Run Instructions

### 1) Start Backend

```bash
cd backend
mvn spring-boot:run
```

Backend runs at `http://localhost:8080`.

### 2) Start Frontend

In a new terminal:

```bash
cd frontend
npm install
npm run dev
```

Frontend runs at `http://localhost:5173`.

## Submission Note

To push to GitHub, initialize git in this root folder and push the full repository so both `frontend/` and `backend/` are included.
