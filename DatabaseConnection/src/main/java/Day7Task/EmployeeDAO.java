package Day7Task;

import java.sql.*;

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

    public void addEmployee(int empId, String name, String dept, double emp_sal) {
        String sql = "INSERT INTO Employee (emp_id, emp_name, dept, emp_sal) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, empId);
            pstmt.setString(2, name);
            pstmt.setString(3, dept);
            pstmt.setDouble(4, emp_sal);
            pstmt.executeUpdate();

            System.out.println(" Employee added successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getAllEmployees() {
        String sql = "SELECT * FROM Employee";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("Employee List:");
            while (rs.next()) {
                int id = rs.getInt("emp_id");
                String name = rs.getString("emp_name");
                String dept = rs.getString("dept");
                double salary = rs.getDouble("emp_sal");

                System.out.println(id + " | " + name + " | " + dept + " | " + salary);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateEmployee(int empId, String newDept, double newSalary) {
        String sql = "UPDATE Employee SET dept = ?, emp_sal = ? WHERE emp_id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newDept);
            pstmt.setDouble(2, newSalary);
            pstmt.setInt(3, empId);

            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println(" Employee updated successfully!");
            } else {
                System.out.println(" Employee not found.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteEmployee(int empId) {
        String sql = "DELETE FROM Employee WHERE emp_id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, empId);
            int rowsDeleted = pstmt.executeUpdate();

            if (rowsDeleted > 0) {
                System.out.println(" Employee deleted successfully!");
            } else {
                System.out.println(" Employee not found.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        EmployeeDAO dao = new EmployeeDAO();
        dao.addEmployee(1, "Allen", "CEO", 150000);
        dao.addEmployee(2, "Bob", "IT", 60000);
        dao.addEmployee(3,"smith","testing",15000);
        dao.getAllEmployees();
        dao.updateEmployee(1, "Finance", 55000);
        dao.deleteEmployee(2);
    }
}
