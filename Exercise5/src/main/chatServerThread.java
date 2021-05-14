package main;

import java.io.*;
import java.net.Socket;

public class chatServerThread extends Thread {
    private Socket socket = null;
    private main.chatServer chatServer = null;
    public DataInputStream inputStream;
    public DataOutputStream outputStream;
    private int threadID;

    public chatServerThread(main.chatServer chatServer, Socket socket) {
        this.socket = socket;
        this.chatServer = chatServer;
        this.threadID = socket.getPort();
    }

    public void run() {
        System.out.println("Server thread " + threadID + " running...");
        while(true) {
            try {
                //Read the input
                String input = inputStream.readUTF();
                System.out.println("[threadID] " + threadID + " : " + input);

                //Interrupts the loop when user hit bye
                if (input.equals("bye")) {
                    chatServer.chatServerThreadList.remove(this);
                    this.close();
                    break;
                }
                //Broadcasting message
                for (chatServerThread chatServer : chatServer.chatServerThreadList) {
                    chatServer.broadCasting(this.threadID, input);
                }
            } catch (IOException ioException) {
                ioException.printStackTrace();
                break;
            }
        }

    }
    public void broadCasting(int senderID, String message) throws IOException {
        message = "[threadID] " + senderID + ": " + message;
        this.outputStream.writeUTF(message);
        this.outputStream.flush();
    }
    public void open() throws IOException {
        inputStream = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        outputStream = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
    }

    public void close() throws IOException {
        if (socket != null) {
            socket.close();
            System.out.println("Close the connection !");
        };
    }
}