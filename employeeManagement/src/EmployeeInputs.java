
import java.util.Scanner;

public class EmployeeInputs {



    public static void main(String[] args){
       int  count= 1;
        Scanner s= new Scanner(System.in);

        EmployeeManagement e= new EmployeeManagement();
        int id= count++;
        e.setId(id);

        System.out.println("\nEnter employee name:");
        String name=s.nextLine();
        e.setName(name);

        System.out.println("\nEnter the employee dept");
        String dept=s.nextLine();
        e.setDept(dept);

        System.out.println("\nEnter employee designation");
        String designation=s.nextLine();
        e.setDesignation(designation);


        System.out.println("\nEmployee details are as follows ");
        System.out.println("Employee id:"+e.getId());
        System.out.println("\nEmployee name:"+ e.getName());
        System.out.println("\nEmployee  working in dept:"+e.getDept());
        System.out.println("\n Employee's designation:" + e.getDesignation());




    }




}
