package Day5Task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class LoanInterestCalculator {
    String startDate;
    static int years;
    static double LoanAmount;
    static double InterestRate;
    double InterestAmount;
    double totalAmount;

    public void LoanInterestCalculation() {
        Scanner s = new Scanner(System.in);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");
        System.out.println("Enter start date(DD/MM/YY)");
        startDate = s.nextLine();

        try {
            Date date = formatter.parse(startDate);
            System.out.println("Parsed Date: " + date);
            System.out.println("Enter number of years");
            years = s.nextInt();
            System.out.println("Enter the loan amount");
            LoanAmount = s.nextDouble();
            System.out.println("Enter interest rate:");
            InterestRate = s.nextDouble();

            validateLoanInputs(years, LoanAmount, InterestRate);

            LoanInterestCalculator l = new LoanInterestCalculator();
            l.displayMessage();
            System.out.println("The Emi to be paid for every month is:");
            System.out.println(l.calculateEMI(LoanAmount, InterestRate, years));
        }

        catch (ParseException e) {
            System.out.println("Invalid date format. Please use DD/MM/YY.");
        }

        catch (CustomLoanInputException e) {
            System.out.println("Input Error: " + e.getMessage());
        }
    }

    public static void validateLoanInputs(int years, double loanAmount, double interestRate)
            throws CustomLoanInputException {

        if (years <= 0) {
            throw new CustomLoanInputException("Loan tenure in years must be greater than 0.");
        }

        if (loanAmount <= 0) {
            throw new CustomLoanInputException("Loan amount must be greater than 0.");
        }

        if (interestRate <= 0) {
            throw new CustomLoanInputException("Interest rate must be greater than 0.");
        }
    }

    public void displayMessage() {
        System.out.println("The interest for the loan availed is:");
        System.out.println(InterestAmount = LoanAmount * years * InterestRate / 100);
        System.out.println("Loan amount to be paid after tenure of " + years + " years is:");
        System.out.println(totalAmount = InterestAmount + LoanAmount);
    }

    public double calculateEMI(double loanAmount, double interestRate, int years) {
        double simpleInterest = (loanAmount * interestRate * years) / 100;
        double totalPayable = loanAmount + simpleInterest;
        int tenureInMonths = years * 12;
        double emi = totalPayable / tenureInMonths;

        return emi;
    }

    public static void main(String[] args) {
        System.out.println("Welcome to Loan Interest Calculation App");
        LoanInterestCalculator l = new LoanInterestCalculator();
        l.LoanInterestCalculation();
    }
}
