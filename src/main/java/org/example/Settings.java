package org.example;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Settings extends JFrame {
    public Settings(UI mainUI) {
        setTitle("Settings");
        setSize(400, 350);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        JButton resizeButton = new JButton("Збільшити розмір вікна");
        resizeButton.addActionListener(e -> {
            mainUI.setSize(1000, 700);
            mainUI.requestFocusInWindow();
        });
        panel.add(resizeButton);
        panel.add(Box.createVerticalStrut(10));

        JButton resizeBackButton = new JButton("Повернути розмір назад");
        resizeBackButton.addActionListener(e -> {
            mainUI.setSize(800, 600);
            mainUI.requestFocusInWindow();
        });
        panel.add(resizeBackButton);
        panel.add(Box.createVerticalStrut(10));

        JButton backgroundButton = new JButton("Змінити фон на рожевий");
        backgroundButton.addActionListener(e -> {
            try {
                mainUI.container.backgroundImage = ImageIO.read(getClass().getResource("/Backgrounds/2.png"));
                mainUI.container.repaint();
                mainUI.requestFocusInWindow();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        panel.add(backgroundButton);
        panel.add(Box.createVerticalStrut(10));

        JButton backgroundBackButton = new JButton("Змінити фон на фіолетовий");
        backgroundBackButton.addActionListener(e -> {
            try {
                mainUI.container.backgroundImage = ImageIO.read(getClass().getResource("/Backgrounds/3.png"));
                mainUI.container.repaint();
                mainUI.requestFocusInWindow();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        panel.add(backgroundBackButton);
        panel.add(Box.createVerticalStrut(10));

        JButton backgroundBlueButton = new JButton("Змінити фон на синій");
        backgroundBlueButton.addActionListener(e -> {
            try {
                mainUI.container.backgroundImage = ImageIO.read(getClass().getResource("/Backgrounds/1.png"));
                mainUI.container.repaint();
                mainUI.requestFocusInWindow();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        panel.add(backgroundBlueButton);
        panel.add(Box.createVerticalStrut(20));

        JButton closeButton = new JButton("Закрити");
        closeButton.addActionListener(e ->{
            dispose();
            mainUI.requestFocusInWindow();
        } );
        panel.add(closeButton);

        add(panel);
        setVisible(true);
    }
}
