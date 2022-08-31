package interfaces;

import javax.swing.*;
import modelos.*;
import java.awt.*;

public class JogoPainel extends JPanel {
   
    static int PANEL_WIDTH = 600;
    static int PANEL_HEIGHT = 600;
    static final int BOARD_SIZE = 3;

    private final Tabuleiro tabuleiro;


    public JogoPainel() {
        tabuleiro = new Tabuleiro(BOARD_SIZE);
        loadPreferences();
    }

    final void loadPreferences() {
        this.setPreferredSize(new Dimension(PANEL_WIDTH += Celula.WIDTH, PANEL_HEIGHT += Celula.HEIGHT));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.setLayout(null);
        System.out.println("painel: " + this.getSize());
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g); //To change body of generated methods, choose Tools | Templates.
        drawGrid(g);
        paintCelulaValues(g);
        g.setColor(Color.red);

    }

    private void drawGrid(Graphics g) {

        g.setColor(Color.red);
        Graphics2D g2 = (Graphics2D) g;

        for (int i = 0; i <= tabuleiro.getN(); i++) {
            g2.drawLine(Celula.WIDTH / 2 + i * Celula.WIDTH, Celula.HEIGHT / 2, Celula.WIDTH / 2 + i * Celula.WIDTH, PANEL_HEIGHT - Celula.HEIGHT / 2);
        }

        for (int i = 0; i <= tabuleiro.getN(); i++) {
            g2.drawLine(Celula.WIDTH / 2, Celula.HEIGHT / 2 + Celula.HEIGHT * i, PANEL_WIDTH - Celula.WIDTH / 2, Celula.WIDTH / 2 + Celula.WIDTH * i);
        }

    }

    private void paintCelulaValues(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        int fontSize = Celula.WIDTH / 4;
        tabuleiro.printBoard();
        g2.setFont(new Font("Ink Free", Font.BOLD, fontSize));
        for (Celula[] Celulas : tabuleiro.getBoard()) {
            for (Celula Celula : Celulas) {

                g2.setColor(Color.red);

                if (Celula.celulaVazia()) {
                    g2.setColor(new Color(255, 0, 0, 40));
                }
                g2.drawString(Celula.getvalor(), Celula.getX() + Celula.WIDTH / 2 - fontSize / 3, Celula.getY() + Celula.HEIGHT / 2);
            }
        }
    }
}

