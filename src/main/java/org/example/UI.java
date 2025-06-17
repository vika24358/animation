package org.example;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class UI extends JFrame implements KeyListener {
    Hero hero = new Hero();

    public UI() {
        setTitle("Centered Hero");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 600);
        setLocationRelativeTo(null);

        add(hero);
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
            case 'p':
                hero.protection();
                break;
            case 'k':
                hero.dialogue();
                break;
        }
    }


    @Override
    public void keyPressed(KeyEvent e) {
        char c = Character.toLowerCase(e.getKeyChar());
        switch (c) {
            case 'd':
                hero.walk();
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
