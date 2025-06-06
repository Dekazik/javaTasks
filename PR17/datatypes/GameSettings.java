package datatypes;

public class GameSettings {
    public int size;
    public int fieldSize;
    public  String firstPlayer;
    public  String secondPlayer;

    public GameSettings(int size, String firstPlayer, String secondPlayer) {
        this.size = size;
        this.firstPlayer = firstPlayer;
        this.secondPlayer = secondPlayer;
    }

    public GameSettings() {
        this.size = 3;
        this.fieldSize = size * 2 + 1;
        this.firstPlayer = "Гравець 1";
        this.secondPlayer = "Гравець 2";
    }


}
