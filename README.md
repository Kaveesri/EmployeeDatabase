# EmployeeDatabase

Employee Database App (Java JDBC)

Overview
This project is a simple employee database management system built using Java JDBC. It connects to a PostgreSQL database and performs CRUD (Create, Read, Update, Delete) operations on an employee table.

Features
- Connects to a PostgreSQL database using JDBC
- Implements CRUD operations for employee data
- Uses PreparedStatement for secure queries
- Provides a menu-driven interface for user interaction

Requirements
- Java Development Kit (JDK)
- PostgreSQL database
- JDBC driver for PostgreSQL
- VS Code (or any preferred IDE)

Setup
1. Clone the repository.
2. Set up a PostgreSQL database with an employee table.
3. Update database credentials in the code.
4. Run the application.

Usage
1. Run the app and select an option from the menu.
2. Follow the prompts to add, view, update, or delete employee data.

Code Structure
- EmployeeApp.java: The main application class.
- getConnection(): Establishes a connection to the PostgreSQL database.
- addEmployee(), viewEmployees(), updateEmployee(), deleteEmployee(): Perform CRUD operations on the employee table.

Notes
- Make sure to update the database credentials in the code to match your PostgreSQL database.
- This project uses a simple menu-driven interface for user interaction.
