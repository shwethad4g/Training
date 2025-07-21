package Day10Task;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.InputStream;
import java.io.FileWriter;
import java.io.IOException;

public class JsonToXmlConverter {
    public static void main(String[] args) {
        String outputXmlFile = PathConstants.outputXmlFile;

        try {
            InputStream inputStream = JsonToXmlConverter.class.getClassLoader()
                    .getResourceAsStream(PathConstants.Json_File);

            if (inputStream == null) {
                throw new IOException("Resource file 'Employee.json' not found in classpath.");
            }

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(inputStream);
            XmlMapper xmlMapper = new XmlMapper();
            String formattedXml = xmlMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonNode);
            System.out.println("Formatted XML Output:\n" + formattedXml);
            FileWriter writer = new FileWriter(outputXmlFile);
            writer.write(formattedXml);
            writer.close();
            System.out.println("\nXML has been written to: " + outputXmlFile);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
