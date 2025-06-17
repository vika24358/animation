package org.example;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Hero extends JPanel {
    private Image croppedImage;
    private String path = "/Girl_2/Idle.png";
    private Thread animationThread;
    public volatile boolean running = false;
    public boolean movingOnScreen = false;
    private int posX = 100;

    private int xStep = 128;
    private int x = 25;
    int y = 50;
    int width = 90;
    int height = 100;

    public Hero() {
        setBackground(Color.WHITE);
    }

    public void stopAnimation() {
        running = false;
        if (animationThread != null) {
            animationThread.interrupt();
            try {
                animationThread.join();
            } catch (InterruptedException ignored) {}
            animationThread = null;
        }
    }

    public void moveRight(int limit){
        x += xStep;
        if (x > limit) {
            x = 25;
        }
        repaint();
    }

    public void walk() {
        // если уже запущена walk-анимация, не запускаем снова
        movingOnScreen = true;
        if (running && path.equals("/Girl_2/Walk.png")) return;
        stopAnimation();
        path = "/Girl_2/Walk.png";
        x = 25;
        running = true;

        animationThread = new Thread(() -> {
            while (running) {
                moveRight(1450);           // анимация (спрайт)
                moveOnScreenRight(600);    // движение по экрану
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    break;
                }
            }
        });
        animationThread.start();
    }

    public void moveOnScreenRight(int screenLimit) {
        posX += 10;
        if (posX > screenLimit) posX = screenLimit;
        repaint();
    }


    public void attack() {
        movingOnScreen = false;
        stopAnimation();
        path = "/Girl_2/Attack.png";
        x = 25;
        running = true;

        animationThread = new Thread(() -> {
            while (running) {
                moveRight(1070);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    break;
                }
            }
        });
        animationThread.start();
    }

    public void idle() {
        movingOnScreen = false;
        stopAnimation();
        path = "/Girl_2/Idle.png";
        x = 25;
        running = true;

        animationThread = new Thread(() -> {
            while (running) {
                moveRight(800);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    break;
                }
            }
        });
        animationThread.start();
    }

    public void protection(){
        movingOnScreen = false;
        stopAnimation();
        path = "/Girl_2/Protection.png";
        x = 25;
        running = true;

        animationThread = new Thread(() -> {
            while (running) {
                moveRight(175);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    break;
                }
            }
        });
        animationThread.start();
    }

    public void dialogue(){
        movingOnScreen = false;
        stopAnimation();
        path = "/Girl_2/Dialogue.png";
        x = 25;
        running = true;

        animationThread = new Thread(() -> {
            while (running) {
                moveRight(675);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    break;
                }
            }
        });
        animationThread.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        BufferedImage fullImage;

        try {
            fullImage = ImageIO.read(Objects.requireNonNull(
                    getClass().getResource(path)
            ));

            width = Math.min(width, fullImage.getWidth() - x);
            height = Math.min(height, fullImage.getHeight() - y);

            croppedImage = fullImage.getSubimage(x, y, width, height);
        } catch (IOException | IllegalArgumentException e) {
            throw new RuntimeException("❌ Ошибка загрузки/обрезки изображения: " + e.getMessage());
        }

        int drawX = (getWidth() - croppedImage.getWidth(null)) / 2;
        int drawY = (getHeight() - croppedImage.getHeight(null)) / 2;

            g.drawImage(croppedImage, posX, drawY, null);

    }
}
