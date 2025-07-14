package Day6Task;

import java.util.*;

public class StudentOperations {
    List<Student> studentList = new ArrayList<>();
    Set<Integer> studentIdset = new HashSet<>();
    Map<Integer, Integer> studentDetail = new HashMap<>();

    public void saveStudent(Student student) {
        studentList.add(student);
        System.out.println("student data has been stored successfully");

        for (Student s : studentList) {
            System.out.println(s);
        }
    }

    public void addId(int studentId) {
        studentIdset.add(studentId);
        System.out.println("student id has been added");
        for (Integer s1 : studentIdset) {
            System.out.println(s1);
        }
    }

    public void marksList(int studentId, int Marks) {
            System.out.println("Student ID along with Marks:");
            for (Map.Entry<Integer, Integer> entry : studentDetail.entrySet()) {
                System.out.println("Student ID: " + entry.getKey() + ", Marks: " + entry.getValue());
            }

        }
    public static void main(String[] args){
//        Student s1=new Student(1,"zara",90);
//        Student s2=new Student(2,"akshaya",80);
//        Student s3=new Student(3,"maya",85);
//
   StudentOperations ops=new StudentOperations();
//        ops.saveStudent(s1);
//        ops.saveStudent(s2);
//        ops.saveStudent(s3);
//        ops.addId(4);
        ops.marksList(4,75);
    }
}
