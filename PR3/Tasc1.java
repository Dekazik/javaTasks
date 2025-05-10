import java.util.Scanner;
//Варіант 3. Створити програму, яка при введенні двох дат у форматі день.місяць.рік
// (дні, місяці і роки — цілочисельні дані) визначає і виводить на екран інформацію
// про те, яка дата раніша, а яка пізніша, чи дати однакові.

public class Tasc1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введіть день для 1 дати:");
        int day1 = scanner.nextInt();
        System.out.println("Введіть місяць для 1 дати:");
        int month1 = scanner.nextInt();
        System.out.println("Введіть рік для 1 дати:");
        int year1 = scanner.nextInt();

        System.out.println("Введіть день для 2 дати:");
        int day2 = scanner.nextInt();
        System.out.println("Введіть місяць для 2 дати:");
        int month2 = scanner.nextInt();
        System.out.println("Введіть рік для 2 дати:");
        int year2 = scanner.nextInt();

        if (year1 < year2) {
            System.out.println("Перша дата раніша.");
        } else if (year1 > year2) {
            System.out.println("Друга дата раніша.");
        } else {
            if (month1 < month2) {
                System.out.println("Перша дата раніша.");
            } else if (month1 > month2) {
                System.out.println("Друга дата раніша.");
            } else {
                if (day1 < day2) {
                    System.out.println("Перша дата раніша.");
                } else if (day1 > day2) {
                    System.out.println("Друга дата раніша.");
                } else {
                    System.out.println("Ви ввели однакові дати.");
                }
            }
        }

        scanner.close();
    }
}