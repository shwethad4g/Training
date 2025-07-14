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
        List<Student> sortedList = new ArrayList<>(studentList);
        Collections.sort(sortedList, (s1, s2) -> s1.getName().compareTo(s2.getName()));

        for (Student s : sortedList) {
            System.out.println(s);
        }
    }

    public void displayPassedStudents() {

        for (Student s : studentList) {
            int marks = studentMarks.get(s.getId());
            if (marks >= 40) {
                System.out.println(s + ", Marks: " + marks);
            }
        }
    }

    public void displayHighestAndLowestMarks() {

        if (studentMarks.isEmpty()) {
            System.out.println("No student marks available.");
            return;
        }

        int maxMarks = Collections.max(studentMarks.values());
        int minMarks = Collections.min(studentMarks.values());
        System.out.println("Highest Marks: " + maxMarks);

        for (Student s : studentList) {
            if (studentMarks.get(s.getId()) == maxMarks) {
                System.out.println("Topper: " + s + ", Marks: " + maxMarks);
            }
        }

        System.out.println("Lowest Marks: " + minMarks);

        for (Student s : studentList) {
            if (studentMarks.get(s.getId()) == minMarks) {
                System.out.println("Lowest Scorer: " + s + ", Marks: " + minMarks);
            }
        }
    }

    public void displayPassFailMap() {
        Map<Integer, String> result = studentMarks.entrySet().stream()
                .map(entry -> Map.entry(entry.getKey(),
                        entry.getValue() >= 40 ? "Pass" : "Fail"))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        result.forEach((id, status) ->
                System.out.println("Student ID: " + id + ", Result: " + status)
        );
    }


    public void marksList(int studentId, int Marks) {
        
        for (Map.Entry<Integer, Integer> entry : studentMarks.entrySet()) {
            System.out.println("Student ID: " + entry.getKey() + ", Marks: " + entry.getValue());
        }
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
        System.out.println("\nPassed Students(marks>=40):");
        ops.displayPassedStudents();
        System.out.println("\nPass/Fail Map:");
        ops.displayPassFailMap();
        System.out.println("\nStudent ID along with Marks:");
        ops.marksList(1, 80);
        System.out.println("\nHighest and Lowest Marks:");
        ops.displayHighestAndLowestMarks();
    }
}
