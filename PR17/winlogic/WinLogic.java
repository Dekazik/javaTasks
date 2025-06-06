package winlogic;

public class WinLogic {
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

}
