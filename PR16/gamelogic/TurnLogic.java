package gamelogic;

public class TurnLogic {
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

}
