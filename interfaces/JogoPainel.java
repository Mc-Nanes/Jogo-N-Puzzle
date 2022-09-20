package interfaces;

import javax.swing.*;

import modelos.*;
import java.awt.*;

public class JogoPainel extends JPanel {
   
    public static int PANEL_WIDTH = 600;
    public static int PANEL_HEIGHT = 600;
    private final Tabuleiro tabuleiro;
    static {
        PANEL_WIDTH += Celula.WIDTH;
        PANEL_HEIGHT += Celula.HEIGHT;
    }

    public JogoPainel(int dificuldade) {
        tabuleiro = new Tabuleiro(dificuldade);
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
        super.paintComponent(g); 
        drawGrid(g);
        loadCelulas();
    }

    private void drawGrid(Graphics g) {

        g.setColor(Color.black);
        Graphics2D g2 = (Graphics2D) g;

        for (int i = 0; i <= tabuleiro.getN(); i++) {
            g2.drawLine(Celula.WIDTH / 2 + i * Celula.WIDTH, Celula.HEIGHT / 2, Celula.WIDTH / 2 + i * Celula.WIDTH, PANEL_HEIGHT - Celula.HEIGHT / 2);
        }

        for (int i = 0; i <= tabuleiro.getN(); i++) {
            g2.drawLine(Celula.WIDTH / 2, Celula.HEIGHT / 2 + Celula.HEIGHT * i, PANEL_WIDTH - Celula.WIDTH / 2, Celula.WIDTH / 2 + Celula.WIDTH * i);
        }
    }

    private void addActionListeners() {
        for (Celula[] Celulas : tabuleiro.getBoard()) {
            for (Celula Celula : Celulas) {
                Celula.addActionListener(e -> {
                    if (moveCelula(Celula))
                        repaint();
                    if (checkWinCondition()){
                        JOptionPane.showMessageDialog(null, "WIN", "Parabéns!", JOptionPane.INFORMATION_MESSAGE);
                    }
                });
            }
        }
    }

    private void loadCelulas() {

        for (int i = 0; i < tabuleiro.getN(); i++) {
            for (int j = 0; j < tabuleiro.getN(); j++) {
                Celula celula = tabuleiro.getBoard()[i][j];
                CarregarBotao.loadButton(this,
                        celula,
                        celula.getvalor(),
                        !celula.celulaVazia(),
                        celula.getX(),
                        celula.getY(),
                        Celula.WIDTH * 9 / 10,
                        Celula.HEIGHT * 9 / 10);
            }
        }
    }

    public void debug(Celula celula) {

        int x1 = (Celula.HEIGHT / 2 + celula.getY()) / Celula.HEIGHT - 1;
        int y1 = (celula.getX() + Celula.WIDTH / 2) / Celula.WIDTH - 1;

        System.out.println("[" + x1 + "][" + y1 + "]" + " com valor " + tabuleiro.getBoard()[x1][y1].getvalor() + " ==? " +
                "Celula na posicao " + "(" + celula.getX() + "," + celula.getY() + ")" + " com valor " + celula.getvalor());

    }

    public boolean moveCelula(Celula Celula) {

        if (!hasNeighbourEmptyCelula(Celula)) {
            System.out.println(Celula.getvalor() + " não tem uma celula vizinha vazia");
            return false;
        }

        Celula emptyCelula = determineEmptyCelula();

        Animacao.animateCelula(Celula, emptyCelula, this);

        swapWith(Celula);

        return true;
    }

    public Celula determineEmptyCelula() {
        for (Celula[] Celulas : tabuleiro.getBoard()) {
            for (Celula Celula : Celulas) {
                if (Celula.celulaVazia())
                    return Celula;
            }
        }
        return null;
    }

    public void swapWith(Celula clickedCelula) {
        Celula emptyCelula = determineEmptyCelula();

        int x1 = (Celula.HEIGHT / 2 + clickedCelula.getY()) / Celula.HEIGHT - 1;
        int y1 = (clickedCelula.getX() + Celula.WIDTH / 2) / Celula.HEIGHT - 1;


        int x2 = (Celula.HEIGHT / 2 + emptyCelula.getY()) / Celula.HEIGHT - 1;
        int y2 = (emptyCelula.getX() + Celula.WIDTH / 2) / Celula.HEIGHT - 1;

        Celula temp = tabuleiro.getBoard()[x1][y1];
        tabuleiro.getBoard()[x1][y1] = tabuleiro.getBoard()[x2][y2];
        tabuleiro.getBoard()[x2][y2] = temp;

    }

    public boolean hasNeighbourEmptyCelula(Celula celula) {

        int x1 = (Celula.HEIGHT / 2 + celula.getY()) / Celula.HEIGHT - 1;
        int y1 = (celula.getX() + Celula.WIDTH / 2) / Celula.WIDTH - 1;


        Celula[][] board = tabuleiro.getBoard();

        try {
            if (board[x1][y1 + 1].celulaVazia()) 
                return true;
        } catch (ArrayIndexOutOfBoundsException ignored) {
        }

        try {
            if (board[x1][y1 - 1].celulaVazia())
                return true;
        } catch (ArrayIndexOutOfBoundsException ignored) {

        }
        try {
            if (board[x1 - 1][y1].celulaVazia()) 
                return true;

        } catch (ArrayIndexOutOfBoundsException ignored) {

        }
        try {
            if (board[x1 + 1][y1].celulaVazia()) 
                return true;

        } catch (ArrayIndexOutOfBoundsException ignored) {
        }

        return false;
    }


    public boolean checkWinCondition() {
        StringBuilder currentPattern = new StringBuilder();
        for (Celula[] Celulas : tabuleiro.getBoard()) {
            for (Celula Celula : Celulas) {
                currentPattern.append(Celula.getvalor());
            }
        }
        return currentPattern.toString().equals(generateWinPattern());
    }

    private String generateWinPattern() {
        StringBuilder pattern = new StringBuilder();
        for (int i = 0; i < tabuleiro.getN() * tabuleiro.getN(); i++) {
            pattern.append(i + 1);
        }
        return pattern.toString();
    }

}