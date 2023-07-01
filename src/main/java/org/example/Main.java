package org.example;

import org.example.model.DataBase;
import org.example.view.SignupMenu;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    private static final ServerSocket serverSocket;

    static {
        try {
            serverSocket = new ServerSocket(8080);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

//    private static Socket socket;

    public static void main(String[] args) {
        Main main = new Main();
        SignupMenu signupMenu = new SignupMenu();
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                DataBase.writeAMessageToClient("Salam Khosh Omadi", socket);

                signupMenu.run(socket);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static ServerSocket getServerSocket() {
        return serverSocket;
    }
}