package org.example;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class UI extends JFrame implements KeyListener {
    Hero hero = new Hero();
    Zombie zombie = new Zombie();
    BackgroundPanel container;

    public UI() {
        setTitle("Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        container = new BackgroundPanel("/Backgrounds/1.png");
        container.setLayout(null);
        container.setPreferredSize(getSize());

        hero.setBounds(100, 400, 90, 150);
        zombie.setBounds(500, 400, 200, 150);

        hero.setOpaque(false);
        zombie.setOpaque(false);

        container.add(hero);
        container.add(zombie);

        setContentPane(container);

        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);

        hero.idle();
        zombie.idle();

        JButton settingsButton = new JButton("Settings");
        settingsButton.addActionListener(e -> new Settings(this));
        container.add(settingsButton);
        settingsButton.setBounds(650, 10, 100, 30);

        setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        char c = Character.toLowerCase(e.getKeyChar());
        switch (c) {
            case ' ':
                hero.attack();
                zombie.dead();
                break;
            case 'p', 'з':
                hero.protection();
                break;
            case 'k', 'л':
                hero.dialogue();
                break;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        char c = Character.toLowerCase(e.getKeyChar());
        int step = 5;

        switch (c) {
            case 'd', 'в':
                int newX = Math.min(getWidth() - hero.getWidth(), hero.getX() + step);
                hero.setLocation(newX, hero.getY());
                hero.walk();
                break;

            case 'a', 'ф':
                int newXLeft = Math.max(0, hero.getX() - step);
                hero.setLocation(newXLeft, hero.getY());
                hero.walkLeft();
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (hero.movingOnScreen) {
            hero.idle();
        }
    }

    // Класс панели с фоном
    static class BackgroundPanel extends JPanel {
        public Image backgroundImage;

        public BackgroundPanel(String path) {
            try {
                backgroundImage = ImageIO.read(getClass().getResource(path));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }
}
