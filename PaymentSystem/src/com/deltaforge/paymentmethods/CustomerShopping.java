package com.deltaforge.paymentmethods;

import java.util.Scanner;
public class CustomerShopping {



    public void shopping(){
        double amount;
        int choice;
       Scanner s=new Scanner(System.in);

       System.out.println("\nWelcome back keep shopping!!");


       System.out.println("Enter the amount for purchase:" );
       amount= s.nextDouble();

       System.out.println("\nEnter your mode of payment");
       System.out.println("choice1:"+" "+"cashpaymentmethod");
       System.out.println("choice2:"+" " +"Debit card/credit card");
       System.out.println("choice3:"+"  "+"UPI" );

       System.out.println("Enter your choice:");
        choice = s.nextInt();
       if(choice==1||choice==2|| choice==3) {

           System.out.println("Proceeding to payment gateway");
           PaymentMethods p=new PaymentMethods();
           if(choice==1){
               p.cashPayment();
           }

           if (choice==2){
               p.cardPayment();
           }

           if(choice==3){
               p.UPI();
           }


       }

       else{
           System.out.println("Invalid choice please choose between 1 to 3");
       }
    }


}
