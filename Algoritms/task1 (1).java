/// Задача1. Відсортувати в масиві цілих чисел
/// тільки парні елементи за зростанням.
/// Непарні елементи залишаємо на своїх місцях.

public class task1 {
    public static void main(String[] args) {
        int[] arr = {4, 7, 3, 2, 9, 6};
        selectionSortEvensDesc(arr);
        printArray(arr);
    }

    public static void selectionSortEvensDesc(int[] array) {
        int count = 0;
        for (int value : array) {
            if (value % 2 == 0) {
                count++;
            }
        }

        int[] evenIndexes = new int[count];
        int idx = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] % 2 == 0) {
                evenIndexes[idx++] = i;
            }
        }

        for (int i = 0; i < count - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < count; j++) {
                if (array[evenIndexes[j]] < array[evenIndexes[minIdx]]) {
                    minIdx = j;
                }
            }
            swap(array, evenIndexes[i], evenIndexes[minIdx]);
        }
    }

    public static void swap(int[] array, int i, int j) {
        if (i == j) return;
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void printArray(int[] array) {
        for (int el : array) {
            System.out.print(el + " ");
        }
    }
}
