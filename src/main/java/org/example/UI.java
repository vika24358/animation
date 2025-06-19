package org.example;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class UI extends JFrame implements KeyListener {
    Hero hero = new Hero();
    Zombie zombie = new Zombie();
    JPanel container = new JPanel(null);
    public UI() {
        setTitle("Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);


        container.setPreferredSize(getSize());

        // Размеры компонентов должны быть достаточными для рисования
        hero.setBounds(100, 400, 90, 150);
        zombie.setBounds(500, 400, 200, 150);

        // Важное: чтобы панели не перекрывались белым фоном
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
        if (hero.movingOnScreen){
            hero.idle();
        }

    }

    private void moveHeroRight() {
        int newX = hero.getX() + 10;
        if (newX + hero.getWidth() <= getWidth()) {
            hero.setLocation(newX, hero.getY());
        }
        hero.walk();
    }

    private void moveHeroLeft() {
        int newX = hero.getX() - 10;
        if (newX >= 0) {
            hero.setLocation(newX, hero.getY());
        }
        hero.walkLeft();
    }

}
