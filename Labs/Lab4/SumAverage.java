package Labs.Lab4;
import java.util.Scanner;

public class SumAverage {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] numbers = new int[5];
        for(int i = 0; i < 5; i++) {
            System.out.print("Enter number " + (i + 1) + ": ");
            numbers[i] = scanner.nextInt();
        }
        int sum = 0;
        for (int num : numbers) {
            sum += num;
        }
        double average = sum / (double) numbers.length;

        System.out.println("Sum: " + sum);
        System.out.println("Average: " + average);

        scanner.close();
    }
}
