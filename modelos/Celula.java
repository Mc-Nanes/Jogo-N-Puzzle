package modelos;


import javax.swing.*;

import interfaces.CarregarBotao;
import interfaces.JogoPainel;
import interfaces.MeuJogo;

import java.awt.*;

public class Celula  extends JButton{
    public static  int WIDTH = JogoPainel.PANEL_WIDTH  / MeuJogo.BOARD_SIZE ;
    public static  int HEIGHT = JogoPainel.PANEL_HEIGHT / MeuJogo.BOARD_SIZE;
    private int x, y;
    private final boolean celulaVazia;
    private final String valor;
    
    public Celula(int x, int y, String valor, Boolean celulaVazia) {
        super();
        this.x = x;
        this.y = y;
        this.valor = valor;
        this.celulaVazia = celulaVazia;
        initCelula();
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
    
    public String getvalor() {
        return valor;
    }
    public boolean celulaVazia() {
        return celulaVazia;
    }
    
    private void initCelula() {
        CarregarBotao.loadPreferences(this,Color.red,Color.blue,
        new Font("Ink Free", Font.BOLD, Celula.WIDTH / 4));
    }
    /*
    public void embaralhar (int valor)
    {
        Random rand = new Random();
        for (int i = 0; i <= valor; i ++)
        {
           valor = rand.nextInt();
           
        }
    }
 */
}
