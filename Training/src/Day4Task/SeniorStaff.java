package Day4Task;

import java.util.Scanner;

public class SeniorStaff extends Employee {

    public SeniorStaff(int empId, String empName, String designation, double salary) {
        super(empId, empName, designation, salary);
    }

    @Override
    public void calculateAppraisal() {
        Scanner s = new Scanner(System.in);
        System.out.println("Enter your peerReview(out of 5)");
        peerReview = s.nextInt();
        System.out.println("Enter your selfAppraisal(out of 5)");
        selfAppraisal = s.nextInt();
        System.out.println("Enter your client rating(out of 5)");
        clientRating = s.nextInt();

        if ((peerReview >= 4 && selfAppraisal >= 4) && (peerReview <= 5 && selfAppraisal <= 5) &&
                (clientRating >= 3 && clientRating <= 5)) {
            rate = 10;
            updatedSalary = (hike = (getSalary() * rate / 100 + getSalary()));
            System.out.println(" congratulations!! here is your updated salary after appraisal for this year:"
                    + " " + updatedSalary);

        } else if ((peerReview < 4 && selfAppraisal < 4) || (peerReview > 5 && selfAppraisal > 5) ||
                (clientRating < 3)) {
            System.out.println("sorry you are not eligible for appraisal this year");
        }

    }

    public static void main(String[] args) {
        SeniorStaff s = new SeniorStaff(1, "akshay", "tester", 10000.0);
        s.getInformation();
        s.calculateAppraisal();

    }
}
