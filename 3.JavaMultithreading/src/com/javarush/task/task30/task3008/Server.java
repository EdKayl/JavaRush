package com.javarush.task.task30.task3008;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by user on 23.09.2017.
 */
public class Server {
    private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();

    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(ConsoleHelper.readInt())) {
            ConsoleHelper.writeMessage("Сервер запущен");
            while(true) {
                new Handler(serverSocket.accept()).start();
            }
        } catch (IOException e) {
            ConsoleHelper.writeMessage(e.getMessage());
        }
    }

    private static class Handler extends Thread {
        private Socket socket;

        public Handler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                ConsoleHelper.writeMessage("установлено новое соединение c " + socket.getRemoteSocketAddress());
                Connection connection = new Connection(socket);
                String userName = serverHandshake(connection);
                sendBroadcastMessage(new Message(MessageType.USER_ADDED, userName));
                sendListOfUsers(connection, userName);
                serverMainLoop(connection, userName);
                connectionMap.remove(userName);
                sendBroadcastMessage(new Message(MessageType.USER_REMOVED, userName));
                ConsoleHelper.writeMessage("соединение закрыто");
            } catch (IOException e) {
                ConsoleHelper.writeMessage("Ошибка");
            } catch (ClassNotFoundException e) {
                ConsoleHelper.writeMessage("Ошибка");
            }
        }

        private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException {
            while (true) {
                connection.send(new Message(MessageType.NAME_REQUEST));
                Message reply = connection.receive();
                if (reply.getType() == MessageType.USER_NAME && !reply.getData().isEmpty() && !connectionMap.keySet().contains(reply.getData())) {
                    connectionMap.put(reply.getData(), connection);
                    connection.send(new Message(MessageType.NAME_ACCEPTED));
                    return reply.getData();
                }
            }
        }

        private void sendListOfUsers(Connection connection, String userName) throws IOException {
            for(Map.Entry<String, Connection> entry : connectionMap.entrySet()) {
                if(!userName.equals(entry.getKey())) {
                    connection.send(new Message(MessageType.USER_ADDED, entry.getKey()));
                }
            }
        }

        private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException {
            while(true) {
                Message message = connection.receive();
                if(message.getType() == MessageType.TEXT) {
                    String mess = userName + ": " + message.getData();
                    sendBroadcastMessage(new Message(MessageType.TEXT, mess));
                } else {
                    ConsoleHelper.writeMessage("Ошибка");
                }
            }
        }
    }


    public static void sendBroadcastMessage(Message message) {
        for(Map.Entry<String, Connection> entry : connectionMap.entrySet()) {
            try {
                entry.getValue().send(message);
            } catch (IOException e) {
                ConsoleHelper.writeMessage(e.getMessage());
            }
        }
    }
}
