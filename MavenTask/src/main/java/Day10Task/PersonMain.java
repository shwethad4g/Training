package Day10Task;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.util.Scanner;

public class PersonMain {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Age: ");
        int age = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Gender: ");
        String gender = scanner.nextLine();
        System.out.print("Enter Qualification: ");
        String qualification = scanner.nextLine();
        Person person = new Person(name, age, gender, qualification);

        try {
            XmlMapper xmlMapper = new XmlMapper();
            String xmlOutput = xmlMapper.writerWithDefaultPrettyPrinter().writeValueAsString(person);
            System.out.println(xmlOutput);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
