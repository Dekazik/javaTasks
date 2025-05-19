public class NBishopsSolver extends BacktrackingSolver {
    private int size;
    private int[] board;
    public int numberOfSolutions = 0;

    public NBishopsSolver(int size) {
        this.size = size;
        board = new int[size];
    }

    @Override
    public void solve() {
        placeBishops(0);
        System.out.println("Кількість рішень " + numberOfSolutions);
    }

    private void placeBishops(int row) {
        if (row == size) {
            printBoard();
            return;
        }

        for (int col = 0; col < size; col++) {
            if (isSafe(row, col)) {
                board[row] = col;
                placeBishops(row + 1);
            }
        }
    }

    private boolean isSafe(int row, int col) {
        for (int i = 0; i < row; i++) {
            if (Math.abs(board[i] - col) == Math.abs(i - row)) {
                return false;
            }
        }
        return true;
    }

    private void printBoard() {
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                System.out.print(board[row] == col ? "B\t" : ".\t");
            }
            System.out.println();
        }
        System.out.println();
        numberOfSolutions++;
    }
}
