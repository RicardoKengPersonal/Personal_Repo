package eapli.shodrone.app.customer.console.application;

import java.io.*;
import java.net.Socket;

public class ClientSession {
    private static ClientSession instance;
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    private ClientSession() {}

    public static synchronized ClientSession getInstance() {
        if (instance == null) {
            instance = new ClientSession();
        }
        return instance;
    }

    public boolean connect(String host, int port) throws IOException {
        if (socket == null || socket.isClosed()) {
            this.socket = new Socket(host, port);
            this.out = new PrintWriter(socket.getOutputStream(), true);
            this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }
        return socket.isConnected();
    }

    public void disconnect() {
        if (socket != null && !socket.isClosed()) {
            try {
                out.println("EXIT");
                socket.close();
            } catch (IOException e) {
                // ignorar
            }
        }
        socket = null;
        out = null;
        in = null;
    }

    public PrintWriter getWriter() {
        return out;
    }

    public BufferedReader getReader() {
        return in;
    }

    public boolean isAuthenticated() {
        return socket != null && !socket.isClosed();
    }
}