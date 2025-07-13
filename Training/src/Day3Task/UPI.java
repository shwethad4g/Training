package Day3Task;

import java.util.Scanner;

public class UPI implements PaymentInterface {
    Scanner s = new Scanner(System.in);

    @Override
    public void cashPayment() {
        System.out.println("cashpayment");
    }

    @Override
    public void cardPayment() {
        System.out.println("cardpayment");
    }

    @Override
    public void UPI() {
        System.out.println("\nEnter your Virtual Payment address/Upi Id");
        String upiId = s.nextLine();
        System.out.println("\nEnter your UPI password and finish payment");
        String upiPassword = s.nextLine();

        if (upiPassword != null) {
            System.out.println("Your Payment is successful");
        }
    }
}
