package Day1Task;

import java.util.Scanner;

public class ReversePascalTriangle {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter number of rows: ");
        int rows = scanner.nextInt();

        for (int i =rows-1; i >=0; i--) {

            for(int j = 0; j <rows-i-1; j++) {
                System.out.print(" ");
            }
            int number=1;
            for (int k = 0; k <= i; k++) {
                System.out.print(number + " ");
                number = number * (i - k) / (k + 1);
            }
            System.out.println();
        }
    }
}
