package Day6Task;

import java.util.*;
import java.util.stream.Collectors;

public class StudentOperations {

    List<Student> studentList = new ArrayList<>();
    Set<Integer> studentIdset = new HashSet<>();
    Map<Integer, Integer> studentMarks = new HashMap<>();

    public void saveStudent(Student student, int marks) {
        studentList.add(student);
        studentIdset.add(student.getId());
        studentMarks.put(student.getId(), marks);
    }

    public void displaySortedByName() {
        studentList.stream()
                .sorted(Comparator.comparing(Student::getName))
                .forEach(System.out::println);
    }

    public void displayPassedStudents() {
        studentList.stream()
                .filter(s -> studentMarks.get(s.getId()) >= 40)
                .forEach(s -> System.out.println(s + ", Marks: " + studentMarks.get(s.getId())));
    }

    public void displayPassFailMap() {
        Map<Integer, String> result = studentMarks.entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue() >= 40 ? "Pass" : "Fail"
                ));

        result.forEach((id, status) ->
                System.out.println("Student ID: " + id + ", Result: " + status)
        );
    }


    public void marksList(int studentId, int Marks) {
        studentMarks.entrySet().stream()
                .forEach(entry ->
                        System.out.println("Student ID: " + entry.getKey() + ", Marks: " + entry.getValue())
                );
    }

    public void displayHighestAndLowestMarks() {

        if (studentMarks.isEmpty()) {
            System.out.println("No student marks available.");

            return;
        }

        int maxMarks = Collections.max(studentMarks.values());
        int minMarks = Collections.min(studentMarks.values());
        System.out.println("Highest Marks: " + maxMarks);

        studentList.stream()
                .filter(s -> studentMarks.get(s.getId()) == maxMarks)
                .forEach(s -> System.out.println("Topper: " + s + ", Marks: " + maxMarks));
        System.out.println("Lowest Marks: " + minMarks);

        studentList.stream()
                .filter(s -> studentMarks.get(s.getId()) == minMarks)
                .forEach(s -> System.out.println("Lowest Scorer: " + s + ", Marks: " + minMarks));
    }


    public static void main(String[] args) {
        Student s1 = new Student(1, "zara");
        Student s2 = new Student(2, "akshaya");
        Student s3 = new Student(3, "maya");

        StudentOperations ops = new StudentOperations();
        ops.saveStudent(s1, 85);
        ops.saveStudent(s2, 35);
        ops.saveStudent(s3, 60);
        System.out.println("Sorted Students by Name:");
        ops.displaySortedByName();
        System.out.println("\nPassed Students (marks >= 40):");
        ops.displayPassedStudents();
        System.out.println("\nPass/Fail Map:");
        ops.displayPassFailMap();
        System.out.println("\nStudent ID along with Marks:");
        ops.marksList(1, 80);
        System.out.println("\nHighest and Lowest Marks:");
        ops.displayHighestAndLowestMarks();
    }
}
