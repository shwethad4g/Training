package Day7Task;

import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.conversions.Bson;

public class MongoCRUD {
    private static final String URI = "mongodb://localhost:27017";
    private static final String DB_NAME = "company";
    private static final String COLLECTION_NAME = "employees";

    private MongoCollection<Document> collection;

    public MongoCRUD() {
        MongoClient mongoClient = MongoClients.create(URI);
        MongoDatabase database = mongoClient.getDatabase(DB_NAME);
        collection = database.getCollection(COLLECTION_NAME);
    }

    public void insertEmployee(int id, String name, String dept, double salary) {
        Document doc = new Document("emp_id", id)
                .append("name", name)
                .append("dept", dept)
                .append("salary", salary);
        collection.insertOne(doc);
        System.out.println("Inserted employee");
    }

    public void getAllEmployees() {
        FindIterable<Document> docs = collection.find();
        for (Document doc : docs) {
            System.out.println(doc.toJson());
        }
    }

    public void updateEmployee(int id, String newDept, double newSalary) {
        Bson filter = Filters.eq("emp_id", id);
        Bson updates = Updates.combine(
                Updates.set("dept", newDept),
                Updates.set("salary", newSalary)
        );
        collection.updateOne(filter, updates);
        System.out.println("Updated employee");
    }

    public void deleteEmployee(int id) {
        Bson filter = Filters.eq("emp_id", id);
        collection.deleteOne(filter);
        System.out.println("Deleted employee");
    }

    public static void main(String[] args) {
        MongoCRUD crud = new MongoCRUD();
        crud.insertEmployee(1, "Alice", "HR", 50000);
        crud.insertEmployee(2, "Bob", "IT", 60000);
        crud.insertEmployee(3,"zara","testing",45000.0);
        crud.getAllEmployees();
        crud.updateEmployee(1, "Finance", 55000);
        crud.deleteEmployee(2);
    }
}
