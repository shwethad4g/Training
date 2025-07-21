package Day9Task;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;

public class ParseJsonFromFile {
    public static void main(String[] args) {

        try {
            ObjectMapper mapper = new ObjectMapper();
            InputStream is = ParseJsonFromFile.class.getClassLoader().getResourceAsStream(Constants.Json_File);
            Student student = mapper.readValue(is, Student.class);

            System.out.println("Name: " + student.getName());
            System.out.println("Age: " + student.getAge());
            System.out.println("Detail1: " + student.getDetails().getDetail1());
            System.out.println("ID: " + student.getDetails().getId());
        }

        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
