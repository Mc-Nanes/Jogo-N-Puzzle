package interfaces;
import javax.swing.*;

import modelos.Celula;

public class Animacao {
    public static final int animationSpeed = 1;
    public static int fps = Celula.WIDTH;

    public static void animateCell(Celula cell, Celula emptyCell, JPanel panel) {
        String direction = detectMovementDirection(cell, emptyCell);

        int prevPosX = cell.getX();
        int prevPosY = cell.getY();

        switch (direction) {
            case "LEFT":
                new Thread(() -> {
                    for (int i = 0; i < fps; i++) {
                        try {
                            Thread.sleep(animationSpeed);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        cell.setX(cell.getX() - 1);
                        moveEmptyCell(emptyCell, prevPosX, prevPosY);
                        panel.repaint();
                    }
                }).start();
                break;
            case "RIGHT":
                new Thread(() -> {
                    for (int i = 0; i < fps; i++) {
                        try {
                            Thread.sleep(animationSpeed);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        cell.setX(cell.getX() + Celula.WIDTH / fps);
                        moveEmptyCell(emptyCell, prevPosX, prevPosY);
                        panel.repaint();
                    }
                }).start();
                break;
            case "UP":
                new Thread(() -> {
                    for (int i = 0; i < fps; i++) {
                        try {
                            Thread.sleep(animationSpeed);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        moveEmptyCell(emptyCell, prevPosX, prevPosY);
                        cell.setY(cell.getY() - Celula.HEIGHT / fps);
                        panel.repaint();
                    }
                }).start();
                break;
            case "DOWN":
                new Thread(() -> {
                    for (int i = 0; i < fps; i++) {
                        try {
                            Thread.sleep(animationSpeed);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        cell.setY(cell.getY() + Celula.HEIGHT / fps);
                        moveEmptyCell(emptyCell, prevPosX, prevPosY);
                        panel.repaint();
                    }
                }).start();
                break;
        }
    }

    private static void moveEmptyCell(Celula emptyCell, int prevPosX, int prevPosY) {
        emptyCell.setX(prevPosX);
        emptyCell.setY(prevPosY);
    }

    public static String detectMovementDirection(Celula cell, Celula emptyCell) {

        if (cell.getX() > emptyCell.getX() && cell.getY() == emptyCell.getY())
            return "LEFT";

        if (cell.getX() < emptyCell.getX() && cell.getY() == emptyCell.getY())
            return "RIGHT";

        if (cell.getY() > emptyCell.getY() && cell.getX() == emptyCell.getX())
            return "UP";

        if (cell.getY() < emptyCell.getY() && cell.getX() == emptyCell.getX())
            return "DOWN";

        return "UNKNOWN";
    }

}