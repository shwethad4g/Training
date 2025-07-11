package Day5Task;

import java.time.LocalDate;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class DayCalculator {
    private static final DateTimeFormatter DATE_FMT = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a date (dd-MM-yyyy): ");
        String input = scanner.nextLine();
        try {
            LocalDate date = LocalDate.parse(input, DATE_FMT);
            LocalDate threeDaysLater = date.plusDays(3);
            System.out.println("Date after 3 days: " + threeDaysLater.format(DATE_FMT));
        }
        catch (DateTimeParseException ex) {
            System.out.println("Invalid date format. Please use yyyy-MM-dd (eg 11-07-2025).");
        }
        finally {
            scanner.close();
        }
    }
}