package org.example;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

// Клас MessagePanel: поле вводу, кнопка, область повідомлень
class MessagePanel extends JPanel implements Client.MessageListener{

    private JTextField inputField;
    private JButton sendButton;
    private JTextArea chatArea;
    Client  client = new Client();

    public MessagePanel() {

        try {
            client.connect();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        client.setMessageListener(this);


        JFrame frame = new JFrame("Чат");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());




        setLayout(new BorderLayout());


        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setLineWrap(true);
        chatArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(chatArea);


        JPanel inputPanel = new JPanel(new BorderLayout());
        inputField = new JTextField();
        sendButton = new JButton("Відправити");

        inputPanel.add(inputField, BorderLayout.CENTER);
        inputPanel.add(sendButton, BorderLayout.EAST);

        add(scrollPane, BorderLayout.CENTER);
        add(inputPanel, BorderLayout.SOUTH);

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });


        frame.add(this, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null); // Центр екрану
        frame.setVisible(true);
    }

    // Метод надсилання
    public void sendMessage() {
        chatArea.append("Ya: " + inputField.getText()+ "\n");
        client.sendMessage(inputField.getText());
    }

    // Метод отримання
    @Override
    public void onMessageReceived(String message) {
        chatArea.append("Інший: " + message + "\n");
    }
}
