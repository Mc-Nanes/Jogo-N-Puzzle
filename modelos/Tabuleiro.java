package modelos;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public  class Tabuleiro {

    private final int n;
    private final Celula[][] board;
    private final ArrayList<Integer> valuePool;

    public Tabuleiro(int n) {
        this.n = n;
        board = new Celula[n][n];
        valuePool = new ArrayList<>();
        fillValuePool();
        fillBoard();
    }

    private void fillValuePool() {
        for (int i = 0; i < n * n; i++) valuePool.add(i + 1);
    }

    private void fillBoard() {

        int randomX = rollIndex(n);
        int randomY = rollIndex(n);
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                board[i][j] = new Celula(j * Celula.WIDTH + (Celula.WIDTH / 2),
                        i * Celula.HEIGHT + (Celula.HEIGHT / 2),
                        rollValue(),
                        i == randomX && j == randomY);

    }

    private int rollIndex(int bound) {
        return ThreadLocalRandom.current().nextInt(bound);
    }

    private String rollValue() {
        int index = rollIndex(valuePool.size());
        String value = String.valueOf(valuePool.get(index));
        valuePool.remove(Integer.valueOf(value));

        return value;
    }

    public void printBoard() {
        for (Celula[] cells : board) {
            for (int j = 0; j < board.length; j++) {
                System.out.print(cells[j].getValue() + "\t");
            }
        }
        System.out.println("---------------------------");
    }

    public int getN() {
        return n;
    }

    public Celula[][] getBoard() {
        return board;
    }
}

