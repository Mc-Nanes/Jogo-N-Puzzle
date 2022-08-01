public class Celula {
    private int x, y;
    private final boolean celulaVazia;
    private final String valor;
    
    public Celula(int x, int y, String valor, Boolean celulaVazia) {
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
    public void setEmpty(boolean emptyCell) {
        this.emptyCell = emptyCell;
    }
   
}
