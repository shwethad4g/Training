package com.deltaforge.paymentmethods;

import java.util.Scanner;
public class PaymentMethods implements PaymentInterface {
    Scanner s=new Scanner(System.in);
    String cardNumber;
    String cardHolderName;
    String cardExpiryDate;
    boolean cashpayed;




    public void cashPayment(){
        System.out.println("Please handover the cash to the delivery person ");
        System.out.println("Have you paid the cash(true/false)?:");
        cashpayed=s.nextBoolean();

        if(cashpayed=true){

            System.out.println("Payment successful!Thank You keep shopping!");
        }
        else{
            System.out.println("Payment failed please pay the bill");
        }
    }

    public void  cardPayment(){
        System.out.println("\nEnter your card number here:");
        cardNumber=s.nextLine();
        if(cardNumber.length()==16){

            System.out.println("\nEnter card holder name:");
            cardHolderName=s.nextLine();


            System.out.println("\n Enter the Expiry date(MM/YY):");
            cardExpiryDate=s.nextLine();

            if(cardExpiryDate.length()==5){

                System.out.println("Enter your cvv");
                String cvv=s.nextLine();
                if(cvv.length()==4){
                    System.out.println("Payment through card is success.ThankYou for shopping!");
                }
                else{
                    System.out.println("Invalid cvv entered");
                }

            }
            else{
                System.out.println("\nEnter valid expiry date");
            }


        }

        else{
            System.out.println("\nPlease enter a valid card number");
        }


    }

    @Override
    public void UPI() {
     System.out.println("\nEnter your Virtual Payment address/Upi Id");
     String upiId=s.nextLine();
     System.out.println("\nEnter your UPI password and finish payment");
     String upiPassword=s.nextLine();

     if(upiPassword!=null){
         System.out.println("Your Payment is successful");
     }
    }


}
