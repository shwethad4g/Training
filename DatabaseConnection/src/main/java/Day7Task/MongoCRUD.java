package Day7Task;

import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.Scanner;

public class MongoCRUD {

    private static final String URI = "mongodb://localhost:27017";
    private static final String DB_NAME = "company";
    private static final String COLLECTION_NAME = "employees";

    private MongoCollection<Document> employeeCollection;

    public MongoCRUD() {
        MongoClient mongoClient = MongoClients.create(URI);
        MongoDatabase database = mongoClient.getDatabase(DB_NAME);
        employeeCollection = database.getCollection(COLLECTION_NAME);
    }

    public void insertEmployee(int id, String name, String dept, double salary) {
        Document doc = new Document("emp_id", id)
                .append("emp_name", name)
                .append("dept", dept)
                .append("emp_sal", salary);
        employeeCollection.insertOne(doc);
        System.out.println("Employee inserted with ID: " + id);
    }

    public void getAllEmployees() {
        FindIterable<Document> docs = employeeCollection.find();
        System.out.println("\nThe list of employees are as follows:");
        for (Document doc : docs) {
            System.out.println("ID: " + doc.getInteger("emp_id"));
            System.out.println("Name: " + doc.getString("emp_name"));
            System.out.println("Department: " + doc.getString("dept"));
            System.out.println("Salary: " + doc.getDouble("emp_sal"));
            System.out.println();
        }
    }

    public void updateEmployee(int id, String newDept, double newSalary) {
        Bson filter = Filters.eq("emp_id", id);
        Bson updates = Updates.combine(
                Updates.set("dept", newDept),
                Updates.set("emp_sal", newSalary)
        );
        employeeCollection.updateOne(filter, updates);
        System.out.println("Employee updated successfully!");
    }

    public void deleteEmployee(int id) {
        Bson filter = Filters.eq("emp_id", id);
        employeeCollection.deleteOne(filter);
        System.out.println("Employee deleted successfully!");
    }

    public void findEmployeeByName(String name) {
        FindIterable<Document> docs = employeeCollection.find(Filters.eq("emp_name", name));
        boolean found = false;
        for (Document doc : docs) {
            if (!found) {
                System.out.println("1 record(s) found. Please verify and enter the ID:");
                found = true;
            }
            System.out.println("ID: " + doc.getInteger("emp_id"));
            System.out.println("Name: " + doc.getString("emp_name"));
            System.out.println("Department: " + doc.getString("dept"));
            System.out.println("Salary: " + doc.getDouble("emp_sal"));
            System.out.println();
        }
        if (!found) {
            System.out.println("No employee found with the name: " + name);
        }
    }

    public static void main(String[] args) {
        MongoCRUD crud = new MongoCRUD();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Choose an option:");
            System.out.println("1. View all employees");
            System.out.println("2. Insert an employee");
            System.out.println("3. Update an employee");
            System.out.println("4. Delete an employee");
            System.out.println("5. Exit");

            int choice = Integer.parseInt(scanner.nextLine());
            System.out.println();

            switch (choice) {
                case 1:
                    crud.getAllEmployees();
                    break;

                case 2:
                    System.out.println("Enter the employee ID: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    System.out.println("Enter the employee name: ");
                    String name = scanner.nextLine();
                    System.out.println("Enter the employee department: ");
                    String dept = scanner.nextLine();
                    System.out.println("Enter the employee salary: ");
                    double salary = Double.parseDouble(scanner.nextLine());
                    crud.insertEmployee(id, name, dept, salary);
                    break;

                case 3:
                    System.out.println("Enter the employee name to update: ");
                    String updateName = scanner.nextLine();
                    crud.findEmployeeByName(updateName);
                    System.out.print("\"");
                    int updateId = Integer.parseInt(scanner.nextLine());
                    System.out.println("Enter the new department: ");
                    String newDept = scanner.nextLine();
                    System.out.println("Enter the new salary: ");
                    double newSalary = Double.parseDouble(scanner.nextLine());
                    crud.updateEmployee(updateId, newDept, newSalary);
                    break;

                case 4:
                    System.out.println("Enter the employee name to delete: ");
                    String deleteName = scanner.nextLine();
                    crud.findEmployeeByName(deleteName);
                    System.out.print("\"");
                    int deleteId = Integer.parseInt(scanner.nextLine());
                    crud.deleteEmployee(deleteId);
                    break;

                case 5:
                    System.out.println("Exiting!");
                    return;

                default:
                    System.out.println("Invalid option. Try again.");
            }

            System.out.println();
        }
    }
}
