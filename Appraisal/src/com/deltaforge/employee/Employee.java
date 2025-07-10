package com.deltaforge.employee;

public class Employee {

     private int empId;
     private String empName;
     private  String designation;
     private double salary;

     public double hike;
     public double rate;
     public double updatedSalary;
     public int peerReview;
     public int selfAppraisal;
     public int clientRating;


    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }


    public Employee(int empId, String empName, String designation, double salary) {
        this.empId = empId;
        this.empName = empName;
        this.designation = designation;
        this.salary = salary;

    }

    public  void getInformation(){
     System.out.println("Employee id is: "+ empId);
     System.out.println("Name of Employee is:"+ empName);
     System.out.println("Designation of Employee is" + designation);
     System.out.println("current salary is"+" "+ salary);
    }

   public void calculateAppraisal(){
      System.out.println("calculate Appraisal method");


   }

}
