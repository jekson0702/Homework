package Task1;


public class Task3_2 {
    public static void main(String[] args) {
        int size = 10000;
        int[] mas = new int[size];
        System.out.println("Array:");
        for (int i = 0; i < mas.length; i++) {
            mas[i] = (int) (Math.random() * 10000);
            System.out.print(mas[i] + " ");
        }

        quickSort(mas, 0, mas.length - 1);
        System.out.println();
        System.out.println("Array sorted with QuickSort:");
        for (int i : mas) {
            System.out.print(i + " ");
        }
        shellSort(mas);
        System.out.println();
        System.out.println("Array sorted with ShellSort:");
        for (int ma : mas) {
            System.out.print(ma + " ");
        }
    }

    private static void quickSort(int[] array, int low, int high) {
        if (array.length <= 1)
            return;
        if (low >= high)
            return;
        int middle = low + (high - low) / 2;
        int opora = array[middle];

        int i = low, j = high;
        while (i <= j) {
            while (array[i] < opora) {
                i++;
            }

            while (array[j] > opora) {
                j--;
            }
            if (i <= j) {
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
            }
        }
        if (low < j)
            quickSort(array, low, j);

        if (high > i)
            quickSort(array, i, high);
    }

    private static void shellSort(int[] array) {
        int i, j, step;
        int tmp;
        for (step = array.length / 2; step > 0; step = step / 2)
            for (i = step; i < array.length; i++) {
                tmp = array[i];
                for (j = i; j >= step; j = j - step) {
                    if (tmp < array[j - step])
                        array[j] = array[j - step];
                    else
                        break;
                }
                array[j] = tmp;
            }
    }
}

