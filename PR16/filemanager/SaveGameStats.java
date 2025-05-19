package filemanager;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SaveGameStats {
    public static void saveStats(String winner, int size, String winnerSymbol) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        String timestamp = now.format(formatter);
        String line = timestamp + " | Переможець: " + winner + " | Гравець: " + winnerSymbol + " | Розмір поля: " + size;

        try (BufferedWriter wr = new BufferedWriter(new FileWriter("gameStats.txt", true))) {
            wr.write(line);
            wr.newLine();
            System.out.println("Статистика збережена!");
        } catch (IOException e) {
            System.out.println("Помилка запису у файл: " + e.getMessage());
        }
    }

}
