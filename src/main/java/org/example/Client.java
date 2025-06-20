package org.example;

import java.io.*;
import java.net.Socket;

public class Client {
    private final String serverAddress;
    private final int port;
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private Thread receiveThread;
    private MessageListener listener;//null

    public interface MessageListener {
        void onMessageReceived(String message);
    }
    Client() {
        this.serverAddress = "localhost";
        this.port = 12345;
    }

    public void setMessageListener(MessageListener listener) {
        this.listener = listener;
    }

    public void connect() throws IOException {
        socket = new Socket(serverAddress, port);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);

        receiveThread = new Thread(() -> {
            try {
                String message;
                while ((message = in.readLine()) != null) {
                    if (listener != null) {
                        listener.onMessageReceived(message);
                        System.out.println("Отримано від сервера: " + message);
                    }
                }
            } catch (IOException e) {
                System.err.println("Помилка при читанні: " + e.getMessage());
            }
        });
        receiveThread.start();
    }

    public void sendMessage(String message) {
        if (out != null) {
            out.println(message);
        }
    }

    public void disconnect() {
        try {
            if (receiveThread != null) receiveThread.interrupt();
            if (socket != null) socket.close();
        } catch (IOException e) {
            System.err.println("Помилка при закритті з’єднання: " + e.getMessage());
        }
    }
}
