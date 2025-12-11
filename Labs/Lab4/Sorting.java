package Labs.Lab4;
import java.util.Scanner;

public class Sorting {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of elements to sort: ");
        int n = scanner.nextInt();
        int[] arr = new int[n];
        System.out.println("Enter " + n + " elements:");
        for (int i = 0; i < n; i++) {
            arr[i] = scanner.nextInt();
        }

        // Bubble Sort
        for (int i = 0; i < n - 1; i++) {   
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // swap arr[j] and arr[j+1]
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }

        System.out.println("Sorted array:");    
        for (int num : arr) {
            System.out.print(num + " ");
        }

        scanner.close();
    }
}
