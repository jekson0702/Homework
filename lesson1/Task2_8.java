package Task1;

import java.util.Scanner;

public class Task2_8 {
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
        int min = mas[0];
        int max = mas[0];
        for (int i = 0; i < mas.length; i++) {
            if (mas[i] < min) {
                min = mas[i];
            }
            if (mas[i] > max) {
                max = mas[i];
            }
        }
        System.out.println();
        System.out.println("Min element is: " + min);
        System.out.println("Max element is: " + max);
    }
}
