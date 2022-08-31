package interfaces;

import javax.swing.*;

public class MeuJogo extends JFrame {
    JogoPainel painel;

    public MeuJogo() {
        painel = new JogoPainel();
        loadPreferences();
    }

    final void loadPreferences() {

        this.add(painel);
        this.setTitle(" jogo N-Puzzle ");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);
    }
     public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MeuJogo().setVisible(true));
    }
}
    

   



