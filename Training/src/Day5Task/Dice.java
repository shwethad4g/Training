package Day5Task;

import java.util.Scanner;

public class Dice {
    public int Dice() {
        return (int) (Math.random() * 6) + 1;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Dice d = new Dice();
        int choice;

        while (true) {
            System.out.println("\nEnter your choice:");
            System.out.println("Choose 1 to roll the dice");
            System.out.println("Choose 2 to exit");
            choice = scanner.nextInt();

            if (choice == 1) {
                System.out.println("You have rolled the number: " + d.Dice());
                System.out.println("Keep playing...");
            }

            else if (choice == 2) {
                System.out.println("Thanks for playing. Sorry to see you go. Closing the application.");
                break;
            }

            else {
                System.out.println("Invalid choice. Please choose 1 to play or 2 to exit.");
            }
        }
        scanner.close();
    }
}
