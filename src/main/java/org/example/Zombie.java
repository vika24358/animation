package org.example;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Zombie extends JPanel {
    private String path = "/Zombie_1/Idle.png";
    private int xStep = 128;
    private int x = 25;
    int y = 50;
    int width = 150;
    int height = 100;
    private Thread animationThread;
    public volatile boolean running = false;
    int frameCount = 0;

    public void moveRight(int limit){
        x += xStep;
        frameCount ++;
        if (x > limit) {
            x = 25;
        }
        repaint();
    }

    private void moveLeft() {
        x -= xStep;
        frameCount++;
        if (x < 0) {
            x = 535;
        }
        repaint();
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
    public void idle() {
        path = "/Zombie_1/Idle.png";
        x = 25;
        running = true;

        animationThread = new Thread(() -> {
            while (running) {
                moveRight(750);
                try {
                    Thread.sleep(250);
                } catch (InterruptedException e) {
                    break;
                }
            }
        });
        animationThread.start();
    }

    public void dead(){
        frameCount = 0;
        xStep = 140;
        stopAnimation();
        path = "/Zombie_1/Dead.png";
        x = 535;
        running = true;

        animationThread = new Thread(() -> {
            while (running) {
                moveLeft();
                if (frameCount>2){
                    stopAnimation();
                }
                try {
                    Thread.sleep(250);
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
