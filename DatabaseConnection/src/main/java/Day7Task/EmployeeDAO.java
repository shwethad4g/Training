package Day7Task;

import java.sql.*;
import java.util.Scanner;

public class EmployeeDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/sample";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found.");
        }
    }

    public void getAllEmployees() {
        String sql = "SELECT * FROM Employee";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\nThe list of employees are as follows:");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("emp_id"));
                System.out.println("Name: " + rs.getString("emp_name"));
                System.out.println("Department: " + rs.getString("dept"));
                System.out.println("Salary: " + rs.getDouble("emp_sal"));
                System.out.println();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addEmployee(String name, String dept, double salary) {
        String sql = "INSERT INTO Employee (emp_name, dept, emp_sal) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setString(2, dept);
            pstmt.setDouble(3, salary);
            pstmt.executeUpdate();
            System.out.println("The new employee has been added successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void findEmployeeByName(String name) {
        String sql = "SELECT * FROM Employee WHERE emp_name = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            boolean found = false;

            while (rs.next()) {

                if (!found) {
                    System.out.println("1 record(s) found. Please verify the below record and enter the ID:");
                    found = true;
                }

                System.out.println("ID: " + rs.getInt("emp_id"));
                System.out.println("Name: " + rs.getString("emp_name"));
                System.out.println("Department: " + rs.getString("dept"));
                System.out.println("Salary: " + rs.getDouble("emp_sal"));
                System.out.println();
            }

            if (!found) {
                System.out.println("No employee found with the name: " + name);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateEmployee(int id, String newName, String newDept, double newSalary) {
        String sql = "UPDATE Employee SET emp_name = ?, dept = ?, emp_sal = ? WHERE emp_id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newName);
            pstmt.setString(2, newDept);
            pstmt.setDouble(3, newSalary);
            pstmt.setInt(4, id);
            int rows = pstmt.executeUpdate();

            if (rows > 0) {
                System.out.println("The employee details have been updated successfully!");
            }
            else {
                System.out.println("Employee not found.");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void deleteEmployee(int id) {
        String sql = "DELETE FROM Employee WHERE emp_id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                System.out.println("The employee detail has been deleted successfully!");
            }
            else {
                System.out.println("Employee not found.");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EmployeeDAO dao = new EmployeeDAO();

        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. View all employees");
            System.out.println("2. Insert an employee");
            System.out.println("3. Update an employee");
            System.out.println("4. Delete an employee");
            System.out.println("5. Exit");
            int choice = scanner.nextInt();
            System.out.println();

            switch (choice) {
                case 1:
                    dao.getAllEmployees();
                    break;

                case 2:
                    System.out.print("Enter the employee name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter the employee department: ");
                    String dept = scanner.nextLine();
                    System.out.print("Enter the employee salary: ");
                    double salary = Double.parseDouble(scanner.nextLine());
                    dao.addEmployee(name, dept, salary);
                    break;

                case 3:
                    System.out.print("Enter the employee name to update: ");
                    String updateName = scanner.nextLine();
                    dao.findEmployeeByName(updateName);
                    System.out.print("\"");
                    int updateId = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter the updated name: ");
                    String newName = scanner.nextLine();
                    System.out.print("Enter the updated department: ");
                    String newDept = scanner.nextLine();
                    System.out.print("Enter the updated salary: ");
                    double newSalary = Double.parseDouble(scanner.nextLine());
                    dao.updateEmployee(updateId, newName, newDept, newSalary);
                    break;

                case 4:
                    System.out.print("Enter the employee name to delete: ");
                    String deleteName = scanner.nextLine();
                    dao.findEmployeeByName(deleteName);
                    System.out.print("\"");
                    int deleteId = Integer.parseInt(scanner.nextLine());
                    dao.deleteEmployee(deleteId);
                    break;

                case 5:
                    System.out.println("\nExiting!");
                    return;

                default:
                    System.out.println("Invalid option. Try again.");
            }

            System.out.println();
        }
    }
}
