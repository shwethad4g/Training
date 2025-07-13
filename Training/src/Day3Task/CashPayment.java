package Day3Task;
import java.util.Scanner;

public class CashPayment implements PaymentInterface {
    boolean cashpayed;

    @Override
    public void cashPayment() {
        Scanner s = new Scanner(System.in);
        System.out.println("Please handover the cash to the delivery person ");
        System.out.println("Have you paid the cash(true/false)?:");
        cashpayed = s.nextBoolean();

        if (cashpayed = true) {
            System.out.println("Payment successful!Thank You keep shopping!");
        } else {
            System.out.println("Payment failed please pay the bill");
        }
    }

    @Override
    public void cardPayment() {
        System.out.println("cardpayment");
    }

    @Override
    public void UPI() {
        System.out.println("upi");
    }
}
