package Day9Task;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;

public class ReadJsonFromTextFile {
    public static void main(String[] args) {

        try {
            InputStream inputStream = ReadJsonFromTextFile.class.getClassLoader().
                    getResourceAsStream(Constants.Text_File);

            if (inputStream == null) {
                System.out.println("File not found in resources folder.");
                return;
            }

            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(inputStream);
            System.out.println("JSON Content:");
            System.out.println(root.toPrettyString());
        }

        catch (Exception e) {
            System.out.println("Error reading or parsing file: " + e.getMessage());
        }
    }
}
