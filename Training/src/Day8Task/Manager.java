package Day8Task;

import lombok.ToString;

import java.util.Scanner;

@ToString(callSuper = true)
public class Manager extends Employee {

    public Manager(int empId, String empName, String designation, double salary) {
        super(empId, empName, designation, salary);
    }

    @Override
    public void calculateAppraisal() {

        try (Scanner s = new Scanner(System.in)) {
            System.out.println("Enter your peerReview(out of 5)");
            peerReview = s.nextInt();
            System.out.println("Enter your selfAppraisal(out of 5)");
            selfAppraisal = s.nextInt();

            if ((peerReview == 5 && selfAppraisal == 5)) {
                rate = 15;
                updatedSalary = (hike = (getSalary() * rate / 100 + getSalary()));
                System.out.println("Congratulations!! Here is your updated salary after appraisal for this year: " + updatedSalary);
            }

            else {
                System.out.println("Sorry, you are not eligible for appraisal this year.");
            }

        }
    }

    @Deprecated
    public static void main(String[] args) {
        Manager m = new Manager(1, "Riya", "Manager", 50000.0);
        m.getInformation();
        m.calculateAppraisal();
    }
}
