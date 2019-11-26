package Task1;

import java.util.Scanner;

public class Task2_3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the size of the array");
        int size = scanner.nextInt();
        int[] mas = new int[size];
        System.out.println("Array:");
        for (int i = 0; i < mas.length; i++) {
            mas[i] = (int) (Math.random() * 10);
            System.out.print(mas[i] + " ");
        }
        for (int i = 2; i < mas.length; i = i + 3) {
            mas[i] = mas[i] * 3;
        }
        System.out.println("\nConverted array (first way):");
        for (int i = 0; i < mas.length; i++) {
            System.out.print(mas[i] + " ");
        }

        int counter = 2;
        while (counter < mas.length) {
            mas[counter] = mas[counter] * 3;
            counter = counter + 3;
        }
        System.out.println();
        System.out.println("Converted array (second way/ multiplied by the first way array):");
        for (int i = 0; i < mas.length; i++) {
            System.out.print(mas[i] + " ");

        }
    }
}