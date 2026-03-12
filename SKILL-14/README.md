# User Authentication & Session Management

A minimal full-stack starter that pairs a Spring Boot backend with a Vite + React frontend to demonstrate registration, login, session persistence with `localStorage`, protected routing, profile retrieval, and logout flows.

## Project structure

```
.
├── backend/   # Spring Boot 3 + H2 API
└── frontend/  # React 19 + Vite UI
```

## Backend (Spring Boot)

Prerequisites: JDK 17+

```bash
cd backend
./mvnw spring-boot:run   # or mvnw.cmd on Windows
```

Key details:

- In-memory H2 database with auto schema generation (`spring.jpa.hibernate.ddl-auto=update`).
- REST endpoints (all under `/api`):
  - `POST /auth/register` — create a user record.
  - `POST /auth/login` — validate credentials and return profile summary.
  - `GET /users/{id}` — fetch profile by identifier.
  - `GET /users/by-username/{username}` — alternate lookup used by the frontend.
- Basic validation and friendly error responses via `RestExceptionHandler`.
- CORS is enabled for `http://localhost:5173` (Vite dev server).

Run the backend test suite at any time with `./mvnw test`.

## Frontend (React)

Prerequisites: Node.js 18+

```bash
cd frontend
cp .env.example .env        # optional, defaults to localhost API
npm install
npm run dev                 # starts Vite on http://localhost:5173
```

Notable implementation choices:

- `AuthContext` stores `{ id, username }` in `localStorage`; `Profile` uses that key to fetch the full record from the backend, as required.
- `ProtectedRoute` guards `/home` and `/profile` routes. Users without a saved session are redirected to `/login`.
- `NavBar` renders Home, Profile, and Logout links whenever a user is signed in.
- Styling is handled with custom CSS (no UI library) to keep the layout clean and responsive.
- API calls are isolated inside `src/services/api.js`, making it easy to swap the backend base URL via `VITE_API_BASE_URL`.

### Demo account

The backend seeds a demo user on startup so reviewers can log in immediately:

| Username | Password |
| --- | --- |
| `demo` | `demo123` |

You can still register new users via the UI; the seeder only runs when the username is absent, so it will not overwrite manual changes.

## Typical flow

1. Visit `/register` and submit the form — the backend stores the user and redirects to login.
2. Log in via `/login`. On success, the app saves the user identity in `localStorage` and redirects to `/home`.
3. Navigate to `/profile` to fetch and display the full user document.
4. Click **Logout** to clear storage and return to `/login`.

## Verification

- `npm run build` (frontend) ✔️
- `./mvnw test` (backend) ✔️

Push both folders into a single GitHub repository to complete the assignment deliverable.
