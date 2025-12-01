# E-Commerce Application

A full-stack E-Commerce application built with a **Java (Spring Boot)** backend and a **JavaScript** frontend. This project provides a comprehensive platform for managing products, user shopping experiences, and order processing.

## 📂 Repository Structure

The project is divided into two main directories:

- **`Backend/`**: Contains the server-side Java application (likely Spring Boot).
- **`Frontend/ecommerce-ui/`**: Contains the client-side user interface.

## 🚀 Technologies Used

### Backend
- **Language:** Java
- **Framework:** Spring Boot (Inferred)
- **Build Tool:** Maven 
- **Database:** MySQL / PostgreSQL (Configurable in `application.properties`)

### Frontend
- **Language:** JavaScript
- **Framework:** React / Frontend Library (Located in `ecommerce-ui`)
- **Runtime:** Node.js

## ⚙️ Prerequisites

Before you begin, ensure you have the following installed on your machine:
- **Java Development Kit (JDK)** (v17 or higher recommended)
- **Node.js & npm** (for the frontend)
- **Maven** (for building the backend)
- **MySQL** or your preferred SQL database

## 🛠️ Installation & Setup

### 1. Clone the Repository
```bash
git clone [https://github.com/Vtk27/ECOMMERCE.git](https://github.com/Vtk27/ECOMMERCE.git)
cd ECOMMERCE

2. Backend Setup

Navigate to the backend directory and configure your database settings.

    Navigate to the Backend:
    Bash

cd Backend

Configure Database: Open src/main/resources/application.properties (or application.yml) and update your database credentials:
Properties

spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce_db
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update

Run the Application:
Bash

    mvn spring-boot:run

    The backend server should start on port 8080 (default).

3. Frontend Setup

Navigate to the frontend directory to install dependencies and start the UI.

    Navigate to the Frontend:
    Bash

cd ../Frontend/ecommerce-ui

Install Dependencies:
Bash

npm install

Start the Development Server:
Bash

    npm start

    The application should now be accessible at http://localhost:3000 (or the port specified).

✨ Features

    User Authentication: Secure login and registration.

    Product Catalog: Browse and search for products.

    Shopping Cart: Add, remove, and update items in the cart.

    Order Management: Checkout and view order history.

    Responsive Design: Optimized for both desktop and mobile devices

🤝 Contributing

Contributions are welcome! If you'd like to improve this project:

    Fork the repository.

    Create a new branch (git checkout -b feature/YourFeature).

    Commit your changes (git commit -m 'Add some feature').

    Push to the branch (git push origin feature/YourFeature).

    Open a Pull Request.

📝 License

This project is open-source. Please check the repository for specific license information.


### Tips for this Repo
* **Database Config:** You will likely need to create a database (e.g., named `ecommerce_db`) in your local MySQL setup before running the backend to avoid connection errors.
* **Dependencies:** Check the `Backend/pom.xml` file to see exactly which libraries (Spring Security, Lombok, etc.) are being used.
* **Frontend Port:** If the backend runs on `8080` and frontend on `3000`, ensure CORS is handled in your Spring Boot controller if you face connection issues.
