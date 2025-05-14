import utils.FileUtils;

import java.time.LocalDateTime;
import java.time.DateTimeException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MyDiary {
    private static final int MAX_RECORDS = 50;
    private final LocalDateTime[] recordDates = new LocalDateTime[MAX_RECORDS];
    private final String[] recordTexts = new String[MAX_RECORDS];
    private boolean isNewFile;
    private String path;

    public void start() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Вітаю!\n1. Створити новий файл\n2. Відредагувати існуючий");
            if (sc.hasNextInt()) {
                int choice = sc.nextInt();
                sc.nextLine();
                if (choice == 1) {
                    isNewFile = true;
                    break;
                } else if (choice == 2) {
                    isNewFile = false;
                    break;
                } else {
                    System.out.println("Будь ласка, введіть 1 або 2.");
                }
            } else {
                System.out.println("Це не число. Спробуйте ще раз.");
                sc.nextLine();
            }
        }

        System.out.println("Введіть шлях до файлу для збереження (наприклад, aboba.txt): ");
        path = sc.nextLine();

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

                switch (choice) {
                    case 1 -> addRecord(sc);
                    case 2 -> deleteRecord(sc);
                    case 3 -> viewAllRecords();
                    case 4 -> {
                        fileSaver(sc);
                        return;
                    }
                    default -> System.out.println("Невірний вибір. Спробуйте ще раз.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Введіть номер пункту.");
                sc.nextLine();
            }
        }
    }

    private void addRecord(Scanner sc) {
        LocalDateTime date;

        while (true) {
            try {
                System.out.println("Введіть дату для запису (рік місяць день): ");
                int year = sc.nextInt(), month = sc.nextInt(), day = sc.nextInt();
                sc.nextLine();
                date = LocalDateTime.of(year, month, day, 0, 0);
                break;
            } catch (InputMismatchException | DateTimeException e) {
                System.out.println("Невірна дата. Спробуйте ще раз.");
                sc.nextLine();
            }
        }

        System.out.println("Введіть текст (Enter на порожньому рядку для завершення):");
        StringBuilder text = new StringBuilder();
        while (true) {
            String line = sc.nextLine();
            if (line.isEmpty()) break;
            text.append(line).append("\n");
        }

        for (int i = 0; i < MAX_RECORDS; i++) {
            if (recordDates[i] == null) {
                recordDates[i] = date;
                recordTexts[i] = text.toString();
                System.out.println("Запис додано!");
                return;
            }
        }

        System.out.println("Щоденник заповнений.");
    }

    private void deleteRecord(Scanner sc) {
        LocalDateTime date;

        while (true) {
            try {
                System.out.println("Введіть дату для видалення (рік місяць день): ");
                int year = sc.nextInt(), month = sc.nextInt(), day = sc.nextInt();
                sc.nextLine();
                date = LocalDateTime.of(year, month, day, 0, 0);
                break;
            } catch (InputMismatchException | DateTimeException e) {
                System.out.println("Невірна дата. Спробуйте ще раз.");
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

    private void viewAllRecords() {
        if (!isNewFile) {
            FileUtils.readFile(path);
        }

        boolean found = false;
        for (int i = 0; i < MAX_RECORDS; i++) {
            if (recordDates[i] != null) {
                found = true;
                System.out.println("Дата: " + recordDates[i].toLocalDate());
                System.out.println("Текст:\n" + recordTexts[i]);
                System.out.println("------------------------");
            }
        }

        if (!found) {
            System.out.println("Немає жодного запису.");
        }
    }

    private void fileSaver(Scanner sc) {
        while (true) {
            System.out.println("Сейвить те що написали?\n1. Так\n2. Ні");
            try {
                int choice = sc.nextInt();
                sc.nextLine();
                if (choice == 1) {
                    FileUtils.saveToFile(path, recordDates, recordTexts);
                    return;
                } else if (choice == 2) {
                    return;
                } else {
                    System.out.println("Невірний вибір.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Введіть число.");
                sc.nextLine();
            }
        }
    }
}
