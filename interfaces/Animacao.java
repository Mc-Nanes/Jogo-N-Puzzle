package interfaces;
import javax.swing.*;

import modelos.Celula;

public class Animacao {
    public static final int animationSpeed = 1;
    public static int fps = Celula.WIDTH;

    public static void animateCelula(Celula celula, Celula celulaVazia, JPanel panel) {
        String direcao = detectMovementdirecao(celula, celulaVazia);

        int prevPosX = celula.getX();
        int prevPosY = celula.getY();

        switch (direcao) {
            case "ESQUERDA":
                new Thread(() -> {
                    for (int i = 0; i < fps; i++) {
                        try {
                            Thread.sleep(animationSpeed);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        celula.setX(celula.getX() - 1);
                        movecelulaVazia(celulaVazia, prevPosX, prevPosY);
                        panel.repaint();
                    }
                }).start();
                break;
            case "DIREITA":
                new Thread(() -> {
                    for (int i = 0; i < fps; i++) {
                        try {
                            Thread.sleep(animationSpeed);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        celula.setX(celula.getX() + Celula.WIDTH / fps);
                        movecelulaVazia(celulaVazia, prevPosX, prevPosY);
                        panel.repaint();
                    }
                }).start();
                break;
            case "CIMA":
                new Thread(() -> {
                    for (int i = 0; i < fps; i++) {
                        try {
                            Thread.sleep(animationSpeed);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        movecelulaVazia(celulaVazia, prevPosX, prevPosY);
                        celula.setY(celula.getY() - Celula.HEIGHT / fps);
                        panel.repaint();
                    }
                }).start();
                break;
            case "BAIXO":
                new Thread(() -> {
                    for (int i = 0; i < fps; i++) {
                        try {
                            Thread.sleep(animationSpeed);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        celula.setY(celula.getY() + Celula.HEIGHT / fps);
                        movecelulaVazia(celulaVazia, prevPosX, prevPosY);
                        panel.repaint();
                    }
                }).start();
                break;
        }
    }

    private static void movecelulaVazia(Celula celulaVazia, int prevPosX, int prevPosY) {
        celulaVazia.setX(prevPosX);
        celulaVazia.setY(prevPosY);
    }

    public static String detectMovementdirecao(Celula celula, Celula celulaVazia) {

        if (celula.getX() > celulaVazia.getX() && celula.getY() == celulaVazia.getY())
            return "ESQUERDA";

        if (celula.getX() < celulaVazia.getX() && celula.getY() == celulaVazia.getY())
            return "DIREITA";

        if (celula.getY() > celulaVazia.getY() && celula.getX() == celulaVazia.getX())
            return "CIMA";

        if (celula.getY() < celulaVazia.getY() && celula.getX() == celulaVazia.getX())
            return "BAIXO";

        return "UNKNOWN";
    }

} 