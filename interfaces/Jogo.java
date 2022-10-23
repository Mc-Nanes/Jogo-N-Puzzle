package interfaces;
import javax.swing.*;

import modelos.Celula;

import java.awt.*;
import java.util.Iterator;

public class Jogo extends JFrame {
    public static boolean isMainButtonActivated = false;
    JPanel panel;
    private final ButtonGroup difficultyButtons;
    JButton startButton;
    private JButton easyButton;
    private JButton normalButton;
    private JButton hardButton;
    private JButton insaneButton;
    private JButton demonButton;

    public Jogo() {
        difficultyButtons = new ButtonGroup();
        panel = new JPanel();
        this.add(panel);

        loadPanel();
        loadPreferences();

        instantiateAllButtons();
        loadAllButtons();

        CarregarBotao.loadButton(panel, startButton, "START", true, 250, 500, 310, 50);
        CarregarBotao.loadPreferences(startButton, Color.black, Color.CYAN, new Font("Ink Free", Font.BOLD, 25));

        startButton.addActionListener(e -> {
            try {
                initiateTheGame(getSelectedButton().getText());
            } catch (NullPointerException x) {
                JOptionPane.showMessageDialog(null,
                        "PLease choose one of the difficulties...",
                        "No difficulty selected!", JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    final void loadPanel() {

        panel.setPreferredSize(new Dimension(800, 800));
        panel.setBackground(Color.BLACK);
        panel.setFocusable(true);
        panel.setLayout(null);
    }

    public void loadButton(JButton button, String text, int x, int y, int width, int height) {
        difficultyButtons.add(button);
        button.setBorder(BorderFactory.createRaisedBevelBorder());
        CarregarBotao.loadPreferences(button, Color.black, Color.yellow, new Font("Ink Free", Font.ITALIC, 25));
        CarregarBotao.loadButton(panel, button, text, true, x, y, width, height);

        button.addActionListener(e -> {
            button.setBorderPainted(true);
            button.setBackground(Color.red);
            repaintSelectedOldButtons(button);
        });

    }

    public int getSelectedButtonCount() {
        int count = 0;
        for (Iterator<AbstractButton> it = difficultyButtons.getElements().asIterator(); it.hasNext(); ) {

            AbstractButton button = it.next();
            if (button.getBackground() == Color.red)
                count++;
        }
        return count;
    }

    final void loadPreferences() {

        this.setTitle("Options Screen");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);
    }

    public AbstractButton getSelectedButton() {
        for (Iterator<AbstractButton> it = difficultyButtons.getElements().asIterator(); it.hasNext(); ) {

            AbstractButton button = it.next();
            if (button.getBackground() == Color.red)
                return button;
        }
        return null;
    }

    public static void main(String[] args) {
        newJogo();

    }

    public static void newJogo() {
        SwingUtilities.invokeLater(() -> new Jogo().setVisible(true));
    }

    private void initiateTheGame(String choice) {
        switch (choice) {
            case "FÁCIL":
                this.dispose();
                newGame(2);
                break;
            case "NORMAL":
                this.dispose();
                newGame(3);
                break;
            case "DIFICIL":
                this.dispose();
                newGame(4);
                break;
            case "INSANO":
                this.dispose();
                newGame(5);
                break;
            case "DEMÔNIO":
                this.dispose();
                newGame(6);
                break;
        }
    }

    public void newGame(int difficulty) {

        if (isMainButtonActivated) {
            adjustComponentSizes(difficulty);
        }

        MeuJogo.BOARD_SIZE = difficulty; // adjust the board size before initialization

        SwingUtilities.invokeLater(() -> new MeuJogo(difficulty).setVisible(true));
    }

    public static void adjustComponentSizes(int difficulty) {
        //change panel sizes to its default value
        JogoPainel.PANEL_WIDTH -= Celula.WIDTH;
        JogoPainel.PANEL_HEIGHT -= Celula.HEIGHT;

        MeuJogo.BOARD_SIZE = difficulty; // adjust the board size before initialization

        // re-calculate the cell sizes, by using the default panel size, according to the new difficulty
        Celula.WIDTH = JogoPainel.PANEL_WIDTH / MeuJogo.BOARD_SIZE;
        Celula.HEIGHT = JogoPainel.PANEL_HEIGHT / MeuJogo.BOARD_SIZE;

        //adjusted
        Animacao.fps = Celula.WIDTH;

        //re-adjust the panel size to its new values
        JogoPainel.PANEL_WIDTH += JogoPainel.PANEL_WIDTH / difficulty;
        JogoPainel.PANEL_HEIGHT += JogoPainel.PANEL_HEIGHT / difficulty;
    }

    private void clearOldSelections(AbstractButton exceptSelected) {
        for (Iterator<AbstractButton> it = difficultyButtons.getElements().asIterator(); it.hasNext(); ) {

            AbstractButton button = it.next();
            if (!button.equals(exceptSelected))
                button.setBackground(Color.yellow);
        }
    }

    private void repaintSelectedOldButtons(AbstractButton button) {
        if (getSelectedButtonCount() > 1)
            clearOldSelections(button);
    }

    private void loadAllButtons() {
        loadButton(easyButton, "FÁCIL", 250, 200, 150, 50);
        loadButton(normalButton, "NORMAL", 410, 200, 150, 50);
        loadButton(hardButton, "DIFICIL", 250, 300, 150, 50);
        loadButton(insaneButton, "INSANO", 410, 300, 150, 50);
        loadButton(demonButton, "DEMÔNIO", 330, 400, 150, 50);
    }

    private void instantiateAllButtons() {
        easyButton = new JButton();
        normalButton = new JButton();
        hardButton = new JButton();
        insaneButton = new JButton();
        demonButton = new JButton();
        startButton = new JButton();

    }

}

