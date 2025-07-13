package Day3Task;

import java.util.Scanner;

public class CardPayment implements PaymentInterface {
    Scanner s = new Scanner(System.in);
    String cardNumber;
    String cardHolderName;
    String cardExpiryDate;

    @Override
    public void cashPayment() {
        System.out.println("cashpayment method");
    }

    public void cardPayment() {
        System.out.println("\nEnter your card number here:");
        cardNumber = s.nextLine();
        if (cardNumber.length() == 16) {
            System.out.println("\nEnter card holder name:");
            cardHolderName = s.nextLine();
            System.out.println("\nEnter the Expiry date(MM/YY):");
            cardExpiryDate = s.nextLine();

            if (cardExpiryDate.length() == 5) {
                System.out.println("\nEnter your cvv");
                String cvv = s.nextLine();

                if (cvv.length() == 3) {
                    System.out.println("Payment through card is success.ThankYou for shopping!");
                } else {
                    System.out.println("Invalid cvv entered");
                }
            } else {
                System.out.println("\nEnter valid expiry date");
            }
        } else {
            System.out.println("\nPlease enter a valid card number");
        }
    }

    @Override
    public void UPI() {
        System.out.println("upi method");
    }
}
