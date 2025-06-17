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
    int width = 90;
    int height = 100;

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
        int drawX = 400;

        g.drawImage(croppedImage, drawX, drawY, null);

    }
}
