package org.example;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Hero extends JPanel {
    private String path = "/Girl_2/Idle.png";
    private Thread animationThread;
    public volatile boolean running = false;
    public boolean movingOnScreen = false;
    private int posX = 100;
    public int frameCount;

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
        frameCount ++;
        if (x > limit) {
            x = 25;
        }
        repaint();
    }

    public void walk() {
        movingOnScreen = true;
        if (running && path.equals("/Girl_2/Walk.png")) return;
        stopAnimation();
        path = "/Girl_2/Walk.png";
        x = 25;
        running = true;

        animationThread = new Thread(() -> {
            while (running) {
                moveRight(1450);
                moveOnScreenRight(600);
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

    public void moveOnScreenLeft (){
        posX -= 10;
        if (posX < 0) posX = 0;
        repaint();
    }

    public void walkLeft (){
        movingOnScreen = true;
        if (running && path.equals("/Girl_2/WalkLeft.png")) return;
        stopAnimation();
        path = "/Girl_2/WalkLeft.png";
        x = 1435;
        running = true;

        animationThread = new Thread(() -> {
            while (running) {
                moveLeft();
                moveOnScreenLeft();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    break;
                }
            }
        });
        animationThread.start();
    }

    private void moveLeft() {
        x -= xStep;
        frameCount++;
        if (x < 0) {
            x = 1435;
        }
        repaint();
    }


    public void attack() {
        frameCount = 0;
        movingOnScreen = false;
        stopAnimation();
        path = "/Girl_2/Attack.png";
        x = 25;
        running = true;

        animationThread = new Thread(() -> {
            while (running) {
                moveRight(1070);
                if (frameCount > 9){
                    stopAnimation();
                }
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
        frameCount = 0;
        stopAnimation();
        path = "/Girl_2/Protection.png";
        x = 25;
        running = true;

        animationThread = new Thread(() -> {
            while (running) {
                moveRight(175);
                if (frameCount > 2){
                    stopAnimation();
                }
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
        frameCount = 0;
        path = "/Girl_2/Dialogue.png";
        x = 25;
        running = true;

        animationThread = new Thread(() -> {
            while (running) {
                moveRight(675);
                if (frameCount > 12){
                    stopAnimation();
                }
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
        Image croppedImage;
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

        int drawY = (getHeight() - croppedImage.getHeight(null)) / 2;

        g.drawImage(croppedImage, 0, drawY, null);
    }

}
