package Day3Task;

public class PaymentAccount {
    long accountno;
    String customername;
    String bankName;
    private double bal;
    double amount;

    public PaymentAccount(long accountno, String customername, String bankName) {
        this.accountno = accountno;
        this.customername = customername;
        this.bankName = bankName;
    }

    @Override
    public String toString() {
        return "PaymentAccount{" +
                "accountno=" + accountno +
                ", customername='" + customername + '\'' +
                ", bankName='" + bankName + '\'' +
                '}';
    }

    public double getBal() {
        return bal;
    }

    public void setBal(double bal) {
        this.bal = bal;
    }

    public void withdraw(double amount) {
        if (amount <= bal) {
            bal -= amount;
            System.out.println("\nWithdrawal successful");
        } else {
            System.out.println("\nAmount exceeds the balance please enter a valid amount");
        }

    }

    public void deposit(double amount) {
        if (amount > 0) {
            bal += amount;
            System.out.println("\nDeposit Successful");
        } else {
            System.out.println("\nPlease enter a valid amount");
        }

    }

    public void display() {
        System.out.println("\nThe customer details are:");
        System.out.println("\ncustomer Account number:" + accountno);
        System.out.println("\n customer name is:" + customername);
        System.out.println("\n  customer holding account in " + bankName);
        System.out.println("\n Your account balance is:" + bal);
    }

    public static void main(String[] args) {
        PaymentAccount p = new PaymentAccount(36876987309l, "shwetha", "SBI");
        p.deposit(5000);
        if (p.getBal() > 0) {
            CustomerShopping c1 = new CustomerShopping();
            c1.shopping();
        } else {
            System.out.println("Insufficient balance");
        }
    }
}
