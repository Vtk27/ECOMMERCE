# 🛒 Full-Stack E-Commerce Application

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.0-green)
![React](https://img.shields.io/badge/React-18-blue)
![MySQL](https://img.shields.io/badge/Database-MySQL-lightgrey)

A robust full-stack E-Commerce platform built with a **Spring Boot** backend and a **React.js** frontend. This application features product management, a shopping cart, user authentication, and order processing capabilities.

---

## 📂 Repository Structure

| Directory | Description |
| :--- | :--- |
| **`Backend/`** | Server-side application (Java/Spring Boot). Handles APIs and DB logic. |
| **`Frontend/ecommerce-ui/`** | Client-side application (React). Handles the UI and user interaction. |

---

## 🚀 Technologies

### Backend
* **Framework:** Spring Boot
* **Language:** Java
* **Build Tool:** Maven
* **Database:** MySQL (Configurable via `application.properties`)
* **Security:** Spring Security (implied)

### Frontend
* **Framework:** React
* **Runtime:** Node.js
* **Styling:** CSS / Bootstrap (Inferred)

---

## ⚙️ Prerequisites

Ensure you have the following installed before starting:
1.  **Java JDK 17+**
2.  **Node.js & npm** (Latest LTS recommended)
3.  **Maven**
4.  **MySQL Server**

---

## 🛠️ Installation Guide

### 1. Clone the Repository
```bash
git clone [https://github.com/Vtk27/ECOMMERCE.git](https://github.com/Vtk27/ECOMMERCE.git)
cd ECOMMERCE
````

### 2\. Backend Setup

1.  **Navigate to the backend folder:**

    ```bash
    cd Backend
    ```

2.  **Configure the Database:**

      * Create a database named `ecommerce_db` in MySQL.
      * Open `src/main/resources/application.properties` and update your credentials:

    <!-- end list -->

    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce_db
    spring.datasource.username=YOUR_USERNAME
    spring.datasource.password=YOUR_PASSWORD
    spring.jpa.hibernate.ddl-auto=update
    ```

3.  **Run the Server:**

    ```bash
    mvn spring-boot:run
    ```

    ✅ *The Backend will start on `http://localhost:8080`*

### 3\. Frontend Setup

1.  **Open a new terminal** and navigate to the frontend folder:

    ```bash
    cd Frontend/ecommerce-ui
    ```

2.  **Install Dependencies:**

    ```bash
    npm install
    ```

3.  **Start the UI:**

    ```bash
    npm start
    ```

    ✅ *The Frontend will start on `http://localhost:3000`*

-----

## ✨ Key Features

  - [x] **User Auth:** Secure Login and Registration functionality.
  - [x] **Product Catalog:** View details, prices, and images of products.
  - [x] **Cart System:** Add to cart, update quantities, and remove items.
  - [x] **Order Flow:** Seamless checkout process.
  - [x] **Responsive:** Works on Mobile and Desktop.

-----

## 🔧 Troubleshooting

| Issue | Solution |
| :--- | :--- |
| **Database Connection Fail** | Ensure MySQL is running and the credentials in `application.properties` are correct. |
| **Port 8080 in use** | Change the port in `application.properties`: `server.port=8081`. |
| **CORS Error** | Ensure the Spring Boot `@CrossOrigin` annotation allows requests from `localhost:3000`. |

-----

## 🤝 Contributing

1.  Fork the Project
2.  Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3.  Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4.  Push to the Branch (`git push origin feature/AmazingFeature`)
5.  Open a Pull Request

-----
