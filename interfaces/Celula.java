package interfaces;
import java.util.Random;




public class Celula {
    public static final int WIDTH = JogoPainel.PANEL_WIDTH  / JogoPainel.BOARD_SIZE ;
    public static final int HEIGHT = JogoPainel.PANEL_HEIGHT / JogoPainel.BOARD_SIZE;
    private int x, y;
    private final boolean celulaVazia;
    private final String valor;
    
    public Celula(int x, int y, String valor, Boolean celulaVazia) {
        super();
        this.x = x;
        this.y = y;
        this.valor = valor;
        this.celulaVazia = celulaVazia;
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
    

    public void embaralhar (int valor)
    {
        Random rand = new Random();
        for (int i = 0; i <= valor; i ++)
        {
           valor = rand.nextInt();
           
        }
    }


   
}
