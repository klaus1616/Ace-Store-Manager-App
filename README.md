# Ace-Store-Manager-App

This project is a web application designed to manage vendors, employees, and user access based on roles, implemented with Vue.js. It features a vendor portal, employee management system, and login system with role-based access control.

## Features

### For **Unauthorized Users**:
- **Home Page**: View the home page.
- **Vendor Page**: View the list of all vendors.

### For **Logged-in Authorized Users**:
- **Home Page**: View the home page.
- **Vendor Page**: View the list of all vendors.
- **Employee Portal**: View the employee portal with employee data (without hourly wages).

### For **Admin Users**:
- **Home Page**: View the home page.
- **Vendor Page**: View the list of all vendors.
- **Employee Portal**: View employee data (including hourly wages).
- **CRUD Operations**: Add, update, and delete vendors and employees.

## Technologies Used

- **Frontend**:
  - Vue.js
  - Vue Router
  - Axios
  - Firebase Authentication
- **Backend**:
  - Node.js / Express.js
  - MongoDB (Mongoose ODM)
- **Styling**:
  - Custom CSS
  - Open Props for utility-first styling

---

## How to Use the Application

### 1. **Accessing the Application**

You can access the app through the home page after logging in. There are different views depending on your user role:

- **Unauthorized Users**: Can only see the Home Page and Vendor Page with a list of vendors.
- **Authorized Users**: Once logged in, you can access the Home, Vendor, and Employee pages.
- **Admin Users**: Admins can view all pages and have additional control to add, update, and delete vendor and employee data.

### 2. **User Authentication**

- **Login**: Go to the login page, enter your credentials, and log in. If you don’t have an account, you can register.
- **Roles**: Your role will be assigned upon registration, and it will control what pages you can view and what actions you can perform.
  - **Unauthorized Users**: No login required.
  - **Authorized Users**: Must log in, can access the vendor and employee portal pages (without hourly wages).
  - **Admins**: Full access to the app’s features.

---

## API Endpoints

The application uses a set of API endpoints to interact with the backend. Below is a list of the main API endpoints:

### **Vendor API**

- **GET /api/vendors**: Fetches all vendors.
- **POST /api/vendors**: Creates a new vendor.
- **PUT /api/vendors/:id**: Updates a vendor by ID.
- **DELETE /api/vendors/:id**: Deletes a vendor by ID.

### **Employee API**

- **GET /api/employees**: Fetches all employees.
- **POST /api/employees**: Creates a new employee.
- **PUT /api/employees/:id**: Updates an employee by ID.
- **DELETE /api/employees/:id**: Deletes an employee by ID.

### **Authentication API**

- **POST /api/auth/login**: Logs in a user using Firebase authentication.

---

## Running the Application

### 1. **Clone the Repository**

Start by cloning the repository to your local machine.

```bash
git clone https://github.com/klaus1616/Ace-Store-Manager-App
cd Ace Store Manager
```

### 2. **Install Dependencies**

To install the dependencies, run the following commands:

#### Frontend:
```Visual Studio Code
npm install
```
### 3. **Run the Application**

#### Frontend:
To start the Vue.js frontend application, run:

```Visual Studio Code
npm run dev
```

This will run the application on [http://localhost:8080](http://localhost:8080).

#### Backend:
To start the backend API, run:

```Intelij
server/crs/main/java/server

run Serviceapplication
```

## Role-Based Access

- **Unauthorized User**: Can visit the Home and Vendor pages.
- **Authorized User**: Can visit the Home, Vendor, and Employee Portal pages, but without the ability to view hourly wages.
- **Admin**: Has full access to all pages and can perform CRUD operations for vendors and employees.

---
