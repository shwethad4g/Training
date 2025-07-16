package Day8Task;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Employee {

    private int empId;
    private String empName;
    private String designation;
    private double salary;
    public double hike;
    public double rate;
    public double updatedSalary;
    public int peerReview;
    public int selfAppraisal;
    public int clientRating;

    public Employee(int empId, String empName, String designation, double salary) {
        this.empId=empId;
        this.empName=empName;
        this.designation=designation;
        this.salary=salary;
    }

    public void getInformation() {
        System.out.println("Employee id is: " + empId);
        System.out.println("Name of Employee is:" + empName);
        System.out.println("Designation of Employee is" + designation);
        System.out.println("current salary is" + " " + salary);
    }

    @SuppressWarnings("unused")
    public void calculateAppraisal() {
        System.out.println("calculate Appraisal method");
    }
}
