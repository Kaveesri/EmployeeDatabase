package employeeApp;

import java.sql.*;
import java.util.Scanner;

public class EmployeeApp {

    // DB Connection
    public static Connection getConnection() {
        String URL = "jdbc:postgresql://localhost:5432/company";
        String USER = "postgres";          // your DB user
        String PASSWORD = "root1234"; // your DB password

        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Connected to PostgreSQL successfully!");
            return conn;
        } catch (SQLException e) {
            System.out.println("❌ Connection failed!");
            e.printStackTrace();
            return null;
        }
    }

    // ADD EMPLOYEE
    public static void addEmployee(String name, String dept, double salary) {
        String sql = "INSERT INTO employees (name, department, salary) VALUES (?, ?, ?)";
        try (Connection conn = getConnection()) {
            if (conn == null) return;

            try (PreparedStatement pst = conn.prepareStatement(sql)) {
                pst.setString(1, name);
                pst.setString(2, dept);
                pst.setDouble(3, salary);
                int rows = pst.executeUpdate();
                System.out.println(rows + " employee added successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // VIEW EMPLOYEES
    public static void viewEmployees() {
        String sql = "SELECT * FROM employees";
        try (Connection conn = getConnection()) {
            if (conn == null) return;

            try (PreparedStatement pst = conn.prepareStatement(sql);
                 ResultSet rs = pst.executeQuery()) {

                System.out.println("ID | Name | Department | Salary");
                System.out.println("---------------------------------");
                while (rs.next()) {
                    System.out.println(rs.getInt("id") + " | " + rs.getString("name") + " | " +
                            rs.getString("department") + " | " + rs.getDouble("salary"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // UPDATE EMPLOYEE
    public static void updateEmployee(int id, String newName, String newDept, double newSalary) {
        String sql = "UPDATE employees SET name = ?, department = ?, salary = ? WHERE id = ?";
        try (Connection conn = getConnection()) {
            if (conn == null) return;

            try (PreparedStatement pst = conn.prepareStatement(sql)) {
                pst.setString(1, newName);
                pst.setString(2, newDept);
                pst.setDouble(3, newSalary);
                pst.setInt(4, id);
                int rows = pst.executeUpdate();
                System.out.println(rows + " employee updated!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE EMPLOYEE
    public static void deleteEmployee(int id) {
        String sql = "DELETE FROM employees WHERE id = ?";
        try (Connection conn = getConnection()) {
            if (conn == null) return;

            try (PreparedStatement pst = conn.prepareStatement(sql)) {
                pst.setInt(1, id);
                int rows = pst.executeUpdate();
                System.out.println(rows + " employee deleted!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // MAIN MENU
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n===== Employee Database Menu =====");
            System.out.println("1. Add Employee");
            System.out.println("2. View Employees");
            System.out.println("3. Update Employee");
            System.out.println("4. Delete Employee");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Department: ");
                    String dept = sc.nextLine();
                    System.out.print("Enter Salary: ");
                    double salary = sc.nextDouble();
                    addEmployee(name, dept, salary);
                    break;

                case 2:
                    viewEmployees();
                    break;

                case 3:
                    System.out.print("Enter Employee ID to update: ");
                    int updateId = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter New Name: ");
                    String newName = sc.nextLine();
                    System.out.print("Enter New Department: ");
                    String newDept = sc.nextLine();
                    System.out.print("Enter New Salary: ");
                    double newSalary = sc.nextDouble();
                    updateEmployee(updateId, newName, newDept, newSalary);
                    break;

                case 4:
                    System.out.print("Enter Employee ID to delete: ");
                    int deleteId = sc.nextInt();
                    deleteEmployee(deleteId);
                    break;

                case 5:
                    System.out.println("Exiting... Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice! Try again.");
            }

        } while (choice != 5);

        sc.close();
    }
}