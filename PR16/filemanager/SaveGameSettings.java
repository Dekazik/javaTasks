package filemanager;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class SaveGameSettings {
    public static void SaveSettings(int size, String[] players) {
        try (BufferedWriter wr = new BufferedWriter(new FileWriter("gameSettings.txt"))) {
            wr.write("size=" + size);
            wr.newLine();
            wr.write("player1=" + players[0]);
            wr.newLine();
            wr.write("player2=" + players[1]);
            wr.newLine();
            System.out.println("Налаштування збережено у файл!");
        } catch (IOException e) {
            System.out.println("Помилка запису у файл: " + e.getMessage());
        }
    }

}
