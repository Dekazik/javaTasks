import java.util.Scanner;
//Варіант 3. Напишіть програму, яка виводить запитує користувача ввести початковий та кінцевий диапазон.
//Після введення диапазону, запитайте ввести просту цифру (простое число від 1 до 9).
//Виведіть всі значення у наведеному диапазоні крім чисел, які кратні введеному простому числу.
//Використовуйте цикл for з оператором continue.

public class Tasc3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введіть початковий діапазон:");
        int startRange = scanner.nextInt();

        System.out.println("Введіть кінцевий діапазон:");
        int endRange = scanner.nextInt();

        System.out.println("Введіть просте число від 1 до 9:");
        int intInput = scanner.nextInt();

        System.out.println("Числа в діапазоні від " + startRange + " до " + endRange + ", які не кратні " + intInput + ":");

        for (int i = startRange; i <= endRange; i++) {
            if (i % intInput == 0) {
                continue;
            }
            System.out.println(i);
        }

        scanner.close();
    }
}
