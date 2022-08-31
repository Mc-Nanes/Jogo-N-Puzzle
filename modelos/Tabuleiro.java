package modelos;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import interfaces.Celula;

public class Tabuleiro {

    private final int n;
    private final Celula[][]  board;
    private final ArrayList<Integer> Valores;


    public Tabuleiro(int n) {
        this.n = n;
        board = new Celula[n][n];
        Valores = new ArrayList<>();
        
        fillValores();
        fillBoard();
    }

    private void fillValores() {
        for (int i = 0; i < n * n; i++) {
            Valores.add(i + 1);
        }
    }

    private void fillBoard() {
        int randomX = rollIndex(n);
        int randomY = rollIndex(n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                board[i][j] = new Celula(i * Celula.WIDTH + Celula.WIDTH / 2, j * Celula.HEIGHT + Celula.HEIGHT / 2, rollValue(), i == randomX && j == randomY);
            }
        }
    }

    private int rollIndex(int bound) {
        return ThreadLocalRandom.current().nextInt(bound);
    }

    private String rollValue() {
        int index = rollIndex(Valores.size());
        String value = String.valueOf(Valores.get(index));
        Valores.remove(Integer.valueOf(value));

        return value;
    }

    public void printBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                System.out.print(board[i][j].getvalor() + "\t");
            }
            System.out.println("");
        }
    }

    public int getN() {
        return n;
    }

    public Celula[][] getBoard() {
        return board;
    }
}

