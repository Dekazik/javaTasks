package utils;

import java.io.*;
import java.time.LocalDateTime;

public class FileUtils {
    public static void saveToFile(String path, LocalDateTime[] dates, String[] texts) {
        try (BufferedWriter wr = new BufferedWriter(new FileWriter(path))) {
            boolean found = false;
            for (int i = 0; i < dates.length; i++) {
                if (dates[i] != null) {
                    found = true;
                    wr.write("Дата: " + dates[i].toLocalDate());
                    wr.newLine();
                    wr.write("Текст:");
                    wr.newLine();
                    wr.write(texts[i]);
                    wr.newLine();
                    wr.write("------------------------");
                    wr.newLine();
                }
            }
            System.out.println(found ? "Записи збережено!" : "Немає записів для збереження.");
        } catch (IOException e) {
            System.out.println("Помилка запису: " + e.getMessage());
        }
    }

    public static void readFile(String path) {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            System.out.println("\n===== Вміст файлу =====");
            String s;
            while ((s = br.readLine()) != null) {
                System.out.println(s);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл не знайдено: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Помилка читання: " + e.getMessage());
        }
    }
}
