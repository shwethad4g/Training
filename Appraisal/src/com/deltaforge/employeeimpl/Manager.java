package com.deltaforge.employeeimpl;

import com.deltaforge.employee.Employee;

import java.util.Scanner;

public class Manager extends Employee {

    public Manager(int empId, String empName, String designation, double salary) {
        super(empId, empName, designation, salary);
    }

    @Override
    public void calculateAppraisal() {

        Scanner s = new Scanner(System.in);
        System.out.println("Enter your peerReview(out of 5)");
        peerReview = s.nextInt();

        System.out.println("Enter your selfAppraisal(out of 5)");
        selfAppraisal = s.nextInt();


        if ((peerReview == 5 && selfAppraisal == 5)) {
            rate = 15;
            updatedSalary = (hike = (getSalary() * rate / 100 + getSalary()));
            System.out.println(" congratulations!! here is your updated salary after appraisal for this year:"
                    + " "
                    + updatedSalary);

        } else {
            System.out.println("sorry you are not eligible for appraisal this year");
        }

    }

    public static void main(String[] args) {
        Manager m = new Manager(1, "riya", "manager", 50000.0);
        m.getInformation();
        m.calculateAppraisal();
    }
}
