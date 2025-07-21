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
        String gender;
        System.out.println("Select Gender:");
        System.out.println("1. Male");
        System.out.println("2. Female");
        System.out.println("3. Others");
        System.out.print("Enter your choice (1-3): ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
               gender = "Male";
                break;
            case 2:
                gender = "Female";
                break;
            case 3:
                gender = "Others";
                break;
            default:
                System.out.println("Invalid choice. Defaulting to 'Others'.");
                gender = "Others";
        }
        System.out.println("Selected Gender: " + gender);
        System.out.println("Enter Qualification: ");
        String qualification = scanner.nextLine();
        Person person = new Person(name, age, gender, qualification);

        try {
            XmlMapper xmlMapper = new XmlMapper();
            String xmlOutput = xmlMapper.writerWithDefaultPrettyPrinter().writeValueAsString(person);
            System.out.println(xmlOutput);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

