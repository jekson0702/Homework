package Task1;

import java.util.Scanner;

public class Task2_11 {
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
        double[] newMas = new double[size];
        for (int i = 0; i < newMas.length; i++) {
            if (i == 0) {
                newMas[i] = mas[i + 1] / 2.0;
            } else if (i == mas.length - 1) {
                newMas[i] = mas[i - 1] / 2.0;
            } else {
                newMas[i] = (mas[i - 1] + mas[i + 1]) / 2.0;
            }
        }
        System.out.println();
        System.out.println("Converted array:");
        for (int i = 0; i < newMas.length; i++) {
            System.out.print(newMas[i] + " ");
        }
    }
}
