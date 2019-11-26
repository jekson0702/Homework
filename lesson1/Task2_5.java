package Task1;

import java.util.Scanner;

public class Task2_5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the size of the array");
        int size = scanner.nextInt();
        int[] mas = new int[size];
        int counter = 0;
        System.out.println("Array:");
        for (int i = 0; i < mas.length; i++) {
            mas[i] = (int) (Math.random() * 10);
            if (mas[i] == 0) {
                counter++;
            }
            System.out.print(mas[i] + " ");
        }
        System.out.println();
        if (counter == 0) {
            System.out.println("There are no zero elements");
        } else {
            System.out.println("Indexes of zero elements:");
            for (int i = 0; i < mas.length; i++) {
                if (mas[i] == 0) {
                    System.out.print(i + " ");

                }
            }
        }
    }
}
