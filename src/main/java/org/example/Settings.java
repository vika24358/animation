package org.example;

import javax.swing.*;
import java.awt.*;

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
        resizeButton.addActionListener(e -> mainUI.setSize(1000, 700));
        panel.add(resizeButton);
        panel.add(Box.createVerticalStrut(10));

        JButton resizeBackButton = new JButton("Повернути розмір назад");
        resizeBackButton.addActionListener(e -> mainUI.setSize(800, 600));
        panel.add(resizeBackButton);
        panel.add(Box.createVerticalStrut(10));

        JButton backgroundButton = new JButton("Змінити фон на світло-синій");
        backgroundButton.addActionListener(e -> mainUI.getContentPane().setBackground(new Color(173, 216, 230)));
        panel.add(backgroundButton);
        panel.add(Box.createVerticalStrut(10));

        JButton backgroundBackButton = new JButton("Змінити фон на білий");
        backgroundBackButton.addActionListener(e -> mainUI.getContentPane().setBackground(Color.WHITE));
        panel.add(backgroundBackButton);
        panel.add(Box.createVerticalStrut(20));

        JButton closeButton = new JButton("Закрити");
        closeButton.addActionListener(e -> dispose());
        panel.add(closeButton);

        add(panel);
        setVisible(true);
    }
}
