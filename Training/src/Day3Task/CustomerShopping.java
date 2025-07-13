package Day3Task;
import java.util.Scanner;

public class CustomerShopping {
    public void shopping() {
        double amount;
        int choice;
        Scanner s = new Scanner(System.in);
        System.out.println("\nWelcome back keep shopping!!");
        System.out.println("\nEnter the amount for purchase:");
        amount = s.nextDouble();
        System.out.println("\nEnter your mode of payment");
        System.out.println("choice1: cashpaymentmethod");
        System.out.println("choice2: Debit card/credit card");
        System.out.println("choice3: UPI");
        System.out.println("Enter your choice:");
        choice = s.nextInt();

        if (choice == 1 || choice == 2 || choice == 3) {
            System.out.println("Proceeding to payment gateway");
            CashPayment c=new CashPayment();
            CardPayment c1=new CardPayment();
            UPI u=new UPI();
            if (choice == 1) {
                c.cashPayment();
            }
            if (choice == 2) {
                c1.cardPayment();
            }
            if (choice == 3) {
                u.UPI();
            }
        } else {
            System.out.println("Invalid choice please choose between 1 to 3");
        }
    }
}
