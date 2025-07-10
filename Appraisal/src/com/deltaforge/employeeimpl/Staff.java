package com.deltaforge.employeeimpl;

import com.deltaforge.employee.Employee;
import java.util.Scanner;

public class Staff extends Employee {


    public Staff(int empId, String empName, String designation, double salary) {
        super(empId, empName, designation, salary);
    }


    @Override
    public void calculateAppraisal() {
        Scanner s= new Scanner(System.in);
        System.out.println("Enter your peerReview(out of 5)");
        peerReview=s.nextInt();

        System.out.println("Enter your selfAppraisal(out of 5)");
        selfAppraisal=s.nextInt();

        System.out.println("Enter your client rating(out of 5)");
        clientRating=s.nextInt();



        if((peerReview>=3&& selfAppraisal>=3)&&(peerReview<=5&& selfAppraisal<=5) && (clientRating>=3&& clientRating<=5)){
          rate=5;
          updatedSalary=  (hike= (getSalary()*rate/100 +getSalary()));
          System.out.println(" congratulations!! here is your updated salary after appraisal for this year:"+ " " + updatedSalary);
        }


        else{
            System.out.println("sorry you are not eligible for appraisal this year");
        }
    }

    public  static void main(String[] args){

        Staff j= new Staff(1,"zara","dev",15000.0);

        j.getInformation();
        j.calculateAppraisal();


    }
}
