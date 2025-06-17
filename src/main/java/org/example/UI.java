package org.example;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class UI extends JFrame implements KeyListener {
    Hero hero = new Hero();
    Zombie zombie = new Zombie();

    public UI() {
        setTitle("Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setLocationRelativeTo(null);
        add(hero);
        hero.idle();
        addKeyListener(this);

        setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {
        char c = Character.toLowerCase(e.getKeyChar());
        switch (c) {
            case ' ':
                hero.attack();
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
        switch (c) {
            case 'd', 'в':
                hero.walk();
                break;
            case 'a', 'ф':
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
}
