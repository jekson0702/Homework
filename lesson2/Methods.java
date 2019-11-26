package homework;

public class Methods {
    public static void main(String[] args) {
        int a = 5;
        int b = 7;
        System.out.println(findMin(a, b));
        System.out.println(evenOdd(a));
        System.out.println(square(a));
        System.out.println(cube(a));
    }

    public static int findMin(int a, int b) {
        if (a <= b) {
            return a;
        } else
            return b;
    }

    public static boolean evenOdd(int a) {
        if (a % 2 == 0) {
            return true;
        } else
            return false;
    }

    public static int square(int a) {
        a = a * a;
        return a;
    }

    public static double cube(int a) {
        double b = Math.pow(a, 3);
        return b;
    }
}
