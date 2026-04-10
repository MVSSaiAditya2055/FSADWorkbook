# Full-Stack Auth Application

Repository structure:

- `frontend/` -> React application
- `backend/` -> Spring Boot application

## Backend run

1. Open `backend/` in STS or any Java IDE.
2. Run `AuthappApplication`.
3. Backend starts on `http://localhost:8080`.

## Frontend run

1. Open terminal in `frontend/`.
2. Install dependencies:
   - `npm install`
3. Start dev server:
   - `npm run dev`
4. Open `http://localhost:5173`.

## Auth flow implemented

1. Register user from Register page.
2. Login with username/password.
3. User object (`userId`, `username`) stored in `localStorage`.
4. Home page protected; accessible only when user exists in storage.
5. Profile page uses stored user data to call backend and fetch full profile.
6. Logout clears storage and redirects to Login.
