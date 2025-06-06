package menumanager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import filemanager.SaveGameSettings;
import gamelogic.GameLogic;

public class MenuManager {
    public static void printMainMenu() {
        System.out.println("\n--- Головне меню ---");
        System.out.println("1. Грати");
        System.out.println("2. Налаштування");
        System.out.println("3. Переглянути статистику");
        System.out.println("4. Вихід");
        System.out.print("Оберіть цифру варіанту: ");
    }

    public static void printSettingsMenu(Scanner sc, int size, String[] players){
        while (true) {
            System.out.println("\n--- Меню налаштувань ---");
            System.out.println("1. Змінити розмір поля\n2. Змінити ім'я гравців\n3. Повернутися до головного меню");
            System.out.print("Оберіть цифру варіанту: ");

            if (sc.hasNextInt()) {
                int settingsOption = sc.nextInt();
                sc.nextLine();

                if (settingsOption == 1) {
                    size = chooseFieldSize(sc, size);
                }else if (settingsOption == 2) {
                    System.out.print("Введіть ім'я першого користувача: ");
                    players[0] = sc.nextLine();
                    System.out.print("Введіть ім'я другого користувача: ");
                    players[1] = sc.nextLine();

                    System.out.print("Іменна користувачів змінено!");
                }
                else if (settingsOption == 3) {
                    System.out.println("Повернення до головного меню...");
                    break;
                } else {
                    System.out.println("Невірно введена команда. Спробуйте ще раз.");
                }
            } else {
                System.out.println("Невірно введена команда. Спробуйте ще раз.");
                sc.next();
            }
        }
        SaveGameSettings.SaveSettings(size, players);
        return;
    }

    public static int chooseFieldSize(Scanner sc, int size){
        System.out.println("Розмір поля: \n1. 3 х 3\n2. 5 х 5\n3. 7 х 7 \n4. 9 х 9");
        if (sc.hasNextInt()) {
            int fieldeSizeSetting = sc.nextInt();

            if (fieldeSizeSetting == 1) {
                size = 3;
                System.out.println("Розмір поля змінено на 3 х 3\n");
            } else if (fieldeSizeSetting == 2) {
                size = 5;
                System.out.println("Розмір поля змінено на 5 х 5\n");
            } else if (fieldeSizeSetting == 3) {
                size = 7;
                System.out.println("Розмір поля змінено на 7 х 7\n");
            } else if (fieldeSizeSetting == 4) {
                size = 9;
                System.out.println("Розмір поля змінено на 9 х 9\n");
            } else {
                System.out.println("Невірно введена команда. Спробуйте ще раз.");
            }
        } else {
            System.out.println("Невірно введена команда. Спробуйте ще раз.");
            sc.next();
        }
        return size;
    }

    public static void printStartGameMenu(Scanner sc, int size, String[] players){
        while (true) {
            System.out.println("\n1. Почати нову гру");
            System.out.println("2. Повернутися до головного меню");
            System.out.print("Оберіть цифру варіанту: ");

            if (sc.hasNextInt()) {
                int gameOption = sc.nextInt();
                sc.nextLine();

                if (gameOption == 1) {
                    GameLogic.playGame(size, sc, players);
                } else if (gameOption == 2) {
                    System.out.println("Повернення до головного меню...");
                    break;
                } else {
                    System.out.println("Невірно введена команда. Спробуйте ще раз.");
                }
            } else {
                System.out.println("Невірно введена команда. Спробуйте ще раз.");
                sc.next();
            }
        }

    }

    public static void showStats() {
        File file = new File("gameStats.txt");
        if (!file.exists()) {
            System.out.println("Файл статистики не знайдено. Зіграйте хочаб 1 раз.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            boolean isEmpty = true;

            System.out.println("--- ІГРОВА СТАТИСТИКА ---");
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    System.out.println(line);
                    isEmpty = false;
                }
            }

            if (isEmpty) {
                System.out.println("Файл статистики порожній.");
            }

        } catch (IOException e) {
            System.out.println("Помилка читання файлу: " + e.getMessage());
        }
    }


}
