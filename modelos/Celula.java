package modelos;


import javax.swing.*;

import interfaces.CarregarBotao;
import interfaces.JogoPainel;
import interfaces.MeuJogo;

import java.awt.*;

public class Celula  extends JButton{
    public static int WIDTH = JogoPainel.PANEL_WIDTH / MeuJogo.BOARD_SIZE;
    public static int HEIGHT = JogoPainel.PANEL_HEIGHT / MeuJogo.BOARD_SIZE;

    private int x, y;
    private final boolean emptyCell;
    private final String value;


    public Celula(int x, int y, String value, Boolean emptyCell) {
        super();
        this.x = x;
        this.y = y;
        this.value = value;
        this.emptyCell = emptyCell;
        initCell();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getValue() {
        return value;
    }

    public boolean isEmptyCell() {
        return emptyCell;
    }

    private void initCell() {
        CarregarBotao.loadPreferences(this,
                Color.green,
                Color.blue,
                new Font("Ink Free", Font.BOLD, Celula.WIDTH / 4));
    }


}
