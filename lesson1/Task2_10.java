package Task1;

import java.util.Scanner;

public class Task2_10 {
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
        boolean counter = true;
        for (int i = 1; i < mas.length; i++) {
            if (mas[i] <= mas[i - 1]) {
                counter = false;
                i = mas.length;
            }
        }
        System.out.println();
        if (counter) {
            System.out.println("The sequence is incremental");
        } else {
            System.out.println("The sequence is not incremental");
        }
    }
}



