package Day9Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import static Day9Task.Constants.FILE_NAME;

public class FileOperations {

    public static void createOrUpdateFile(String content) {
        try {
            FileWriter writer = new FileWriter(FILE_NAME, true);
            writer.write(content + "\n");
            writer.close();
            System.out.println("Data written to file.");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void readFile() {

        try {
            File file = new File(FILE_NAME);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }

            scanner.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteFile() {
        File file = new File(FILE_NAME);

        if (file.exists()) {
            if (file.delete()) {
                System.out.println("File deleted successfully.");
            }
        }
        else {
            System.out.println("File does not exist.");
        }
    }

    public static void main(String[] args) {
        System.out.println("Working Directory: " + System.getProperty("user.dir"));
        createOrUpdateFile("Hello from Java!");
        readFile();
    }
}
