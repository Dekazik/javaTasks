import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.DateTimeException;
import java.util.InputMismatchException;

public class Main {

    public static final int MAX_RECORDS = 50;
    public static LocalDateTime[] recordDates = new LocalDateTime[MAX_RECORDS];
    public static String[] recordTexts = new String[MAX_RECORDS];

    public static void addRecord() {
        Scanner sc = new Scanner(System.in);
        LocalDateTime date;

        while (true) {
            try {
                System.out.println("Введіть дату для запису (рік місяць день): ");
                int year = sc.nextInt();
                int month = sc.nextInt();
                int day = sc.nextInt();
                sc.nextLine();
                date = LocalDateTime.of(year, month, day, 0, 0);
                break;
            } catch (InputMismatchException e) {
                System.out.println("Невірний формат чисел. Спробуйте ще раз.");
                sc.nextLine();
            } catch (DateTimeException e) {
                System.out.println("Неприпустима дата. Спробуйте ще раз.");
                sc.nextLine();
            }
        }

        String text = "";
        String line;
        System.out.println("Введіть текст (щоб завершити введення, натисніть Enter на порожньому рядку):");

        while (true) {
            line = sc.nextLine();
            if (line.isEmpty()) {
                break;
            }
            text = text + line + "\n";
        }

        for (int i = 0; i < MAX_RECORDS; i++) {
            if (recordDates[i] == null) {
                recordDates[i] = date;
                recordTexts[i] = text;
                System.out.println("Запис додано!");
                return;
            }
        }

        System.out.println("Щоденник заповнений. Видаліть старі записи, щоб додати нові.");
    }

    public static void deleteRecord() {
        Scanner sc = new Scanner(System.in);
        LocalDateTime date;

        while (true) {
            try {
                System.out.println("Введіть дату для видалення (рік місяць день): ");
                int year = sc.nextInt();
                int month = sc.nextInt();
                int day = sc.nextInt();
                sc.nextLine();
                date = LocalDateTime.of(year, month, day, 0, 0);
                break;
            } catch (InputMismatchException e) {
                System.out.println("Невірний формат. Спробуйте ще раз.");
                sc.nextLine();
            } catch (DateTimeException e) {
                System.out.println("Неправильна дата. Спробуйте ще раз.");
                sc.nextLine();
            }
        }

        for (int i = 0; i < MAX_RECORDS; i++) {
            if (date.equals(recordDates[i])) {
                recordDates[i] = null;
                recordTexts[i] = null;
                System.out.println("Запис видалено.");
                return;
            }
        }

        System.out.println("Запис не знайдено.");
    }

    public static void viewAllRecords() {
        boolean found = false;
        for (int i = 0; i < MAX_RECORDS; i++) {
            if (recordDates[i] != null) {
                found = true;
                System.out.println("Дата: " + recordDates[i].toLocalDate());
                System.out.println("Текст:");
                System.out.println(recordTexts[i]);
                System.out.println("------------------------");
            }
        }

        if (!found) {
            System.out.println("Немає жодного запису.");
        }
    }

    public static void mainMenu() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("---- Меню ----");
            System.out.println("1. Додати запис");
            System.out.println("2. Видалити запис");
            System.out.println("3. Переглянути всі записи");
            System.out.println("4. Вихід");
            System.out.print("Оберіть пункт: ");

            try {
                int choice = sc.nextInt();
                sc.nextLine();

                if (choice == 1) {
                    addRecord();
                } else if (choice == 2) {
                    deleteRecord();
                } else if (choice == 3) {
                    viewAllRecords();
                } else if (choice == 4) {
                    System.out.println("До побачення!");
                    return;
                } else {
                    System.out.println("Невірний вибір. Спробуйте ще раз.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Введіть номер пункту.");
                sc.nextLine();
            }
        }
    }

    public static void main(String[] args) {
        mainMenu();
    }
}
