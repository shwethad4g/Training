package Day7Task;


import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import java.sql.*;

public class Migrator {
    private static final String MYSQL_URL = "jdbc:mysql://localhost:3306/sample";
    private static final String MYSQL_USER = "root";
    private static final String MYSQL_PASSWORD = "root";

    private static final String MONGO_URI = "mongodb://localhost:27017";
    private static final String MONGO_DB = "company";
    private static final String MONGO_COLLECTION = "employees";

    public static void main(String[] args) {
        try (
                Connection mysqlConn = DriverManager.getConnection(MYSQL_URL, MYSQL_USER, MYSQL_PASSWORD);
                MongoClient mongoClient = MongoClients.create(MONGO_URI)
        ) {
            String query = "SELECT emp_id, emp_name, dept, emp_sal FROM Employee";
            Statement stmt = mysqlConn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            MongoDatabase mongoDatabase = mongoClient.getDatabase(MONGO_DB);
            MongoCollection<Document> mongoCollection = mongoDatabase.getCollection(MONGO_COLLECTION);

            int count = 0;
            while (rs.next()) {
                Document doc = new Document("emp_id", rs.getInt("emp_id"))
                        .append("emp_name", rs.getString("emp_name"))
                        .append("dept", rs.getString("dept"))
                        .append("emp_sal", rs.getDouble("emp_sal"));

                mongoCollection.insertOne(doc);
                count++;
            }

            System.out.println("Migration completed. " + count + " records migrated from MySQL to MongoDB.");

        } catch (SQLException e) {
            System.out.println("MySQL error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("MongoDB error: " + e.getMessage());
        }
    }
}
