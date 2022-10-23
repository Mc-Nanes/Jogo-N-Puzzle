package interfaces;

import javax.swing.*;

import modelos.*;
import java.awt.*;

public class JogoPainel extends JPanel {
   
    public static int PANEL_WIDTH = 600;
    public static int PANEL_HEIGHT = 600;
    
    private final Tabuleiro gameBoard;

    static {
        PANEL_WIDTH += Celula.WIDTH;
        PANEL_HEIGHT += Celula.HEIGHT;
    }

    public JogoPainel(int dificuldade) {
        gameBoard = new Tabuleiro(dificuldade);
        loadPreferences();
        addActionListeners();

    }

    final void loadPreferences() {
        this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.setLayout(null);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        drawGrid(g);
        loadCells();
    }

    private void drawGrid(Graphics g) {

        g.setColor(Color.gray);
        Graphics2D g2 = (Graphics2D) g;

        for (int i = 0; i <= gameBoard.getN(); i++) {
            g2.drawLine(Celula.WIDTH / 2 + i * Celula.WIDTH, Celula.HEIGHT / 2, Celula.WIDTH / 2 + i * Celula.WIDTH, PANEL_HEIGHT - Celula.HEIGHT / 2);
        }

        for (int i = 0; i <= gameBoard.getN(); i++) {
            g2.drawLine(Celula.WIDTH / 2, Celula.HEIGHT / 2 + Celula.HEIGHT * i, PANEL_WIDTH - Celula.WIDTH / 2, Celula.WIDTH / 2 + Celula.WIDTH * i);
        }
    }

    private void addActionListeners() {
        for (Celula[] cells : gameBoard.getBoard()) {
            for (Celula cell : cells) {
                cell.addActionListener(e -> {
                    if (moveCell(cell))
                        repaint();
                    if (checkWinCondition()){
                        //Animacao.victoryAnimation(gameBoard.getBoard(),this);
                        JOptionPane.showMessageDialog(null, "WIN", "!Congrats", JOptionPane.INFORMATION_MESSAGE);
                    }
                });
            }
        }
    }

    private void loadCells() {

        for (int i = 0; i < gameBoard.getN(); i++) {
            for (int j = 0; j < gameBoard.getN(); j++) {
                Celula cell = gameBoard.getBoard()[i][j];
                CarregarBotao.loadButton(this,
                        cell,
                        cell.getValue(),
                        !cell.isEmptyCell(),
                        cell.getX(),
                        cell.getY(),
                        Celula.WIDTH * 9 / 10,
                        Celula.HEIGHT * 9 / 10);
            }
        }
    }

    public void debug(Celula cell) {

        int x1 = (Celula.HEIGHT / 2 + cell.getY()) / Celula.HEIGHT - 1;
        int y1 = (cell.getX() + Celula.WIDTH / 2) / Celula.WIDTH - 1;

        System.out.println("[" + x1 + "][" + y1 + "]" + " with value " + gameBoard.getBoard()[x1][y1].getValue() + " ==? " +
                "cell at position " + "(" + cell.getX() + "," + cell.getY() + ")" + " with value " + cell.getValue());

    }

    public boolean moveCell(Celula cell) {

        if (!hasNeighbourEmptyCell(cell)) {
            System.out.println(cell.getValue() + " has not an neighbour empty cell");
            return false;
        }

        Celula emptyCell = determineEmptyCell();

        Animacao.animateCell(cell, emptyCell, this);

        swapWith(cell);

        return true;
    }

    public Celula determineEmptyCell() {
        for (Celula[] cells : gameBoard.getBoard()) {
            for (Celula cell : cells) {
                if (cell.isEmptyCell())
                    return cell;
            }
        }
        return null;
    }

    public void swapWith(Celula clickedCell) {
        Celula emptyCell = determineEmptyCell();

        int x1 = (Celula.HEIGHT / 2 + clickedCell.getY()) / Celula.HEIGHT - 1;
        int y1 = (clickedCell.getX() + Celula.WIDTH / 2) / Celula.HEIGHT - 1;


        int x2 = (Celula.HEIGHT / 2 + emptyCell.getY()) / Celula.HEIGHT - 1;
        int y2 = (emptyCell.getX() + Celula.WIDTH / 2) / Celula.HEIGHT - 1;

        Celula temp = gameBoard.getBoard()[x1][y1];
        gameBoard.getBoard()[x1][y1] = gameBoard.getBoard()[x2][y2];
        gameBoard.getBoard()[x2][y2] = temp;

    }

    public boolean hasNeighbourEmptyCell(Celula cell) {

        int x1 = (Celula.HEIGHT / 2 + cell.getY()) / Celula.HEIGHT - 1;
        int y1 = (cell.getX() + Celula.WIDTH / 2) / Celula.WIDTH - 1;


        Celula[][] board = gameBoard.getBoard();

        try {
            if (board[x1][y1 + 1].isEmptyCell()) // cell below is empty cell
                return true;
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }

        try {
            if (board[x1][y1 - 1].isEmptyCell()) // cell above is empty cell
                return true;
        } catch (ArrayIndexOutOfBoundsException ignored) {

        }
        try {
            if (board[x1 - 1][y1].isEmptyCell()) // left cell is empty cell
                return true;

        } catch (ArrayIndexOutOfBoundsException ignored) {

        }
        try {
            if (board[x1 + 1][y1].isEmptyCell()) // right below is empty cell
                return true;

        } catch (ArrayIndexOutOfBoundsException ignored) {
        }

        return false;
    }


    public boolean checkWinCondition() {
        StringBuilder currentPattern = new StringBuilder();
        for (Celula[] cells : gameBoard.getBoard()) {
            for (Celula cell : cells) {
                currentPattern.append(cell.getValue());
            }
        }
        return currentPattern.toString().equals(generateWinPattern());
    }

    private String generateWinPattern() {
        StringBuilder pattern = new StringBuilder();
        for (int i = 0; i < gameBoard.getN() * gameBoard.getN(); i++) {
            pattern.append(i + 1);
        }
        return pattern.toString();
    }

}