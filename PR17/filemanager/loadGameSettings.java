package filemanager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class loadGameSettings {
    public static int loadGameSettings(String[] players) {
        File file = new File("gameSettings.txt");
        if (!file.exists()) {
            System.out.println("Файл налаштувань не знайдено. Використовуються стандартні налаштування.");
            return 3;
        }
        int size = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader("gameSettings.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("size=")) {
                    String sizeStr = line.substring(5);
                    char c = sizeStr.charAt(0);
                    size = c - '0';

                } else if (line.startsWith("player1=")) {
                    players[0] = line.substring(8);
                } else if (line.startsWith("player2=")) {
                    players[1] = line.substring(8);
                }
            }
            System.out.println("Налаштування завантажено з файлу.");
        } catch (IOException e) {
            System.out.println("Помилка читання файалу: " + e.getMessage());
        }
        return size;
    }

}
