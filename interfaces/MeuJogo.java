package interfaces;

import javax.swing.*;

import java.awt.*;

public class MeuJogo extends JFrame {
    public static int BOARD_SIZE;

    JogoPainel JogoPainel;
    JButton resetButton;
    JButton exitButton;
    JButton mainMenuButton;

    private final int difficulty;

    public MeuJogo(int difficulty) {
        this.difficulty = difficulty;
        newGame(difficulty);
        loadPreferences();
    }
    final void loadPreferences() {

        this.setTitle("N-Puzzle game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);

    }

    public void newGame(int difficulty) {

        JogoPainel = new JogoPainel(difficulty);
        this.add(JogoPainel);

        resetButton = new JButton();
        exitButton = new JButton();
        mainMenuButton = new JButton();


        loadInGameButton(resetButton, "RESTART", interfaces.JogoPainel.PANEL_WIDTH - 330, interfaces.JogoPainel.PANEL_HEIGHT- 50, 190, false);
        loadInGameButton(exitButton, "EXIT", interfaces.JogoPainel.PANEL_WIDTH - 130, interfaces.JogoPainel.PANEL_HEIGHT - 50, 120, false);
        loadInGameButton(mainMenuButton, "MAIN MENU", 0,interfaces.JogoPainel.PANEL_WIDTH - 50, 200, true);

        resetButton.addActionListener(e -> restart());
        exitButton.addActionListener(e -> System.exit(0));
        mainMenuButton.addActionListener(e -> {
            Jogo.isMainButtonActivated = true;
            this.dispose();
            Jogo.newJogo();
        });

    }

    public void restart() {
        this.remove(JogoPainel);
        newGame(difficulty);
        SwingUtilities.updateComponentTreeUI(this);
    }

    private void loadInGameButton(JButton button, String text, int x, int y, int width, boolean isMain) {
        Font inGameButtonFont = isMain ? new Font("MV Boli", Font.PLAIN, 25) : new Font("Magneto Kalın", Font.PLAIN, 25);
        CarregarBotao.loadPreferences(button, Color.blue, Color.red, inGameButtonFont);
        CarregarBotao.loadButton(JogoPainel, button, text, true, x, y, width, 50);

    }


}



