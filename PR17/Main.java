import java.util.Scanner;

import filemanager.loadGameSettings;
import menumanager.MenuManager;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] players = new String[2];
        players[0] = "Гравець 1";
        players[1] = "Гравець 2";

        int size = loadGameSettings.loadGameSettings(players);

        while (true) {
            MenuManager.printMainMenu();

            if (sc.hasNextInt()) {
                int playerOption = sc.nextInt();

                if (playerOption == 1) {
                    MenuManager.printStartGameMenu(sc, size, players);

                } else if (playerOption == 2) {
                    MenuManager.printSettingsMenu(sc, size, players);
                } else if (playerOption == 3) {
                    MenuManager.showStats();
                } else if (playerOption == 4) {
                    System.out.println("Дякуємо за гру! До побачення!");
                    break;
                } else {
                    System.out.println("Невірно введена команда. Спробуйте ще раз.");
                }
            } else {
                System.out.println("Невірно введена команда. Спробуйте ще раз.");
                sc.next();
            }
        }
        sc.close();
    }
}