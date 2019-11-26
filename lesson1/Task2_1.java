package Task1;

import java.util.Scanner;

public class Task2_1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the size of the array");
        int size = scanner.nextInt();
        System.out.println("Original array:");
        int[] mas = new int[size];
        for (int i = 0; i < mas.length; i++) {
            mas[i] = (int) (Math.random() * 10);
            System.out.print(mas[i] + " ");
        }
        System.out.println();
        System.out.println("Converted array:");
        for (int i = mas.length - 1; i >= 0; i--) {
            System.out.print(mas[i] + " ");
        }
    }
}
