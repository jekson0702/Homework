package Task1;

import java.util.Scanner;

public class Task2_7 {
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
        int j = 0;
        int counter = mas.length - 1;
        while (j < counter) {
            int a = mas[j];
            mas[j] = mas[counter];
            mas[counter] = a;
            j++;
            counter--;
        }

        System.out.println("\nConverted array:");
        for (int i = 0; i < mas.length; i++) {
            System.out.print(mas[i] + " ");
        }

    }

}
