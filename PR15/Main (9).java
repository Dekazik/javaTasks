import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void printMainMenu() {
        System.out.println("\n--- Головне меню ---");
        System.out.println("1. Грати");
        System.out.println("2. Налаштування");
        System.out.println("3. Переглянути статистику");
        System.out.println("4. Вихід");
        System.out.print("Оберіть цифру варіанту: ");
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
                    playGame(size, sc, players);
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

    public static void playGame(int size, Scanner sc, String[] players){
        System.out.println("--- Гра починається! ---");

        boolean isWin = false;
        boolean isYourTurn = true;
        boolean draw = false;
        int fieldSize = size * 2 + 1;
        int correctTurnsCount = 0;
        int col = 0;
        int row = 0;
        String winner = "";
        String selectedPlayer;
        String selectedBot = "";
        String winnerSymbol = "";

        String[][] gameField = new String[fieldSize][fieldSize];

        gameFieldInicialization(fieldSize, gameField);

        printGameField(gameField);

        String[] characters = getPlayerCharacter(sc);;
        selectedPlayer = characters[0];
        selectedBot = characters[1];

        while (!isWin) {
            if (isYourTurn) {
                System.out.println(players[0] + " введіть координати " + selectedPlayer + " щоб обрати СТРОКУ: ");
                row = getRow(sc);

                System.out.println(players[0] + " введіть координати " + selectedPlayer + " щоб обрати СТОВБИЧ: ");
                col = getColumn(sc);

                makeTurn(row, col, fieldSize, gameField, selectedPlayer);
                correctTurnsCount++;
                isYourTurn = false;

                if (correctTurnsCount >= 3){
                    isWin = winSystem(selectedPlayer, gameField, fieldSize, isWin);
                    winner = players[0];
                    winnerSymbol = selectedPlayer;

                }

            } else {
                System.out.println(players[1] + " введіть координати " + selectedBot + " щоб обрати СТРОКУ: ");
                row = getRow(sc);

                System.out.println(players[1] + " введіть координати " + selectedBot + " щоб обрати СТОВБИЧ: ");
                col = getColumn(sc);

                makeTurn(row, col, fieldSize, gameField, selectedBot);
                correctTurnsCount++;
                isYourTurn = true;

                if (correctTurnsCount >= 3){
                    isWin = winSystem(selectedBot, gameField, fieldSize, isWin);
                    winner = players[1];
                    winnerSymbol = selectedBot;
                }
            }
            draw = isDraw(correctTurnsCount, size);
            printGameField(gameField);
            if (draw) break;
        }

        if (draw) {
            System.out.println("Перемогла дружба!\n");
        } else {
            System.out.println("Переміг " + winner + "\n");
        }
        saveStats(winner, size, winnerSymbol);
        return;
    }

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

    public static int printSettingsMenu(Scanner sc, int size, String[] players){
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
        SaveSettings(size, players);
        return size;
    }

    private static void SaveSettings(int size, String[] players) {
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

    public static void gameFieldInicialization(int fieldSize, String[][] gameField){
        int maxNumber = 4;
        boolean swc = true;
        int a = 0;

        if (fieldSize == 11) {
            maxNumber = 6;
        } else if (fieldSize == 15) {
            maxNumber = 8;
        } else if (fieldSize == 19) {
            maxNumber = 10;
        }

        for (int j = 0; j < fieldSize; j++) {
            if(fieldSize == 7) {
                gameField[0][j] = (j % maxNumber) + " | ";
            } else if (fieldSize == 11) {
                gameField[0][j] = (j % maxNumber) + " | ";
            }else if (fieldSize == 15) {
                gameField[0][j] = (j % maxNumber) + " | ";
            }else if (fieldSize == 19) {
                gameField[0][j] = (j % maxNumber) + " | ";
            }
            if (j > maxNumber - 1)
            {
                gameField[0][j] = "";
            }
        }

        for (int i = 1; i < gameField.length; i++) {
            if (swc) {
                for (int j = 0; j < gameField[i].length; j++) {
                    gameField[i][j] = "——";
                }
                swc = false;
            } else {
                for (int j = 0; j < gameField[i].length; j++) {
                    if (j % 2 == 0 && j != 0) {
                        gameField[i][j] = " - ";
                    } else if (j == 0) {
                        a += 1;
                        gameField[i][j] = a + " ";
                    } else {
                        gameField[i][j] = "|";
                    }
                }
                swc = true;
            }
        }
    }

    public static void printGameField(String[][] gameField){
        for (int i = 0; i < gameField.length; i++) {
            for (int j = 0; j < gameField[i].length; j++) {
                System.out.print(gameField[i][j]);
            }
            System.out.println();
        }
    }

    public static String[] getPlayerCharacter(Scanner sc) {
        String playerX = " X ";
        String player0 = " 0 ";
        String selectedPlayer = "";
        String selectedBot = "";

        while (true) {
            System.out.println("Оберіть за кого будете грати Х або 0: ");
            selectedPlayer = sc.nextLine().toUpperCase(Locale.ROOT);

            if (!(selectedPlayer.charAt(0) == 'X' || selectedPlayer.charAt(0) == '0') || selectedPlayer.charAt(0) == ' ') {
                System.out.println("Не вірно введені данні, спробуй ще раз!");
            } else {
                if (selectedPlayer.charAt(0) == 'X') {
                    selectedPlayer = playerX;
                    selectedBot = player0;
                } else {
                    selectedPlayer = player0;
                    selectedBot = playerX;
                }
                break;
            }
        }
        return new String[]{selectedPlayer, selectedBot};
    }

    public static int getColumn(Scanner sc) {
        int col;
        while (true) {
            if (sc.hasNextInt()) {
                col = sc.nextInt() + 1;

                if (col == 3) {
                    col += 1;
                } else if (col >= 5) {
                    col += (col - 2);
                } else if (col != 2) {
                    col += 2;
                }

                return col;
            } else {
                System.out.println("Не вірно введені данні, спробуй ще раз!");
                sc.next();
            }
        }
    }

    public static int getRow(Scanner sc) {
        int row;
        while (true) {
            if (sc.hasNextInt()) {
                row = sc.nextInt() + 1;

                if (row == 3) {
                    row += 1;
                } else if (row >= 5) {
                    row += (row - 2);
                } else if (row != 2) {
                    row += 2;
                }

                return row;
            } else {
                System.out.println("Не вірно введені данні, спробуй ще раз!");
                sc.next();
            }
        }
    }

    public static void makeTurn(int row, int col, int fieldSize, String[][] gameField, String currentPlayer){
        if (row >= 0 && row < fieldSize && col >= 0 && col < fieldSize) {
            if (gameField[row][col] ==" - ") {
                gameField[row][col] = currentPlayer;
            } else {
                System.out.println("Это место уже занято. Попробуйте другое.");
            }
        } else {
            System.out.println("Недопустимые координаты. Выберите из доступных полей.");
        }
        return;
    }

    public static boolean winSystem(String currentPlayer, String[][] gameField, int fieldSize, boolean isWin){
        for (int i = 0; i < fieldSize; i++) {
            if (gameField[i][2] == currentPlayer && gameField[i][4] ==currentPlayer && gameField[i][6] ==currentPlayer) {
                isWin = true;
                return isWin;
            }
        }

        for (int j = 0; j < fieldSize; j++) {
            if (gameField[2][j] == currentPlayer && gameField[4][j] == currentPlayer && gameField[6][j] == currentPlayer) {
                isWin = true;
                return isWin;
            }
        }

        if (gameField[2][2] == currentPlayer && gameField[4][4] == currentPlayer && gameField[6][6] == currentPlayer) {
            isWin = true;
            return isWin;
        }

        if (gameField[2][6] == currentPlayer && gameField[4][4] == currentPlayer && gameField[6][2] == currentPlayer) {
            isWin = true;
            return isWin;
        }
        return isWin;
    }

    public static boolean isDraw(int correctTurnsCount, int size){
        if(correctTurnsCount == (size * size)) return true;
        return false;
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


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] players = new String[2];
        players[0] = "Гравець 1";
        players[1] = "Гравець 2";

        int size = loadGameSettings(players);

        while (true) {
            printMainMenu();

            if (sc.hasNextInt()) {
                int playerOption = sc.nextInt();

                if (playerOption == 1) {
                    printStartGameMenu(sc, size, players);

                } else if (playerOption == 2) {
                    printSettingsMenu(sc, size, players);
                } else if (playerOption == 3) {
                    showStats();
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
