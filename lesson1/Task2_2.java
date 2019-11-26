package Task1;

import java.util.Scanner;

public class Task2_2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the size of the array");
        int size = scanner.nextInt();
        int[] mas = new int[size];
        int mult = 1;
        System.out.println("Array:");
        for (int i = 0; i < mas.length; i++) {
            mas[i] = (int) (Math.random() * 10);
            mult = mult * mas[i];
            System.out.print(mas[i] + " ");
        }
        System.out.println();
        System.out.println("Product of numbers is " + mult);
    }
}