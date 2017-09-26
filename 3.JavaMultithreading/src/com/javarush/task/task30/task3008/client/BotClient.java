package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.Message;
import com.javarush.task.task30.task3008.MessageType;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by user on 26.09.2017.
 */
public class BotClient extends Client {

    public static void main(String[] args) {
        BotClient client = new BotClient();
        client.run();
    }

    @Override
    protected boolean shouldSendTextFromConsole() {
        return false;
    }

    @Override
    protected SocketThread getSocketThread() {
        return new BotSocketThread();
    }

    @Override
    protected String getUserName() {
        return "date_bot_" + (int)(Math.random() * 100);
    }

    public class BotSocketThread extends SocketThread {

        @Override
        protected void clientMainLoop() throws IOException, ClassNotFoundException {
            sendTextMessage("Привет чатику. Я бот. Понимаю команды: дата, день, месяц, год, время, час, минуты, секунды.");
            super.clientMainLoop();
        }

        @Override
        protected void processIncomingMessage(String message) {
            super.processIncomingMessage(message);
            if (!message.contains(":")) return;

            String[] token = message.split(":");
            String userName = token[0];
            String text = token[1].trim();
            SimpleDateFormat dateFormat = null;
            String newMessage = "Информация для " + userName + ": ";
            boolean sendAnswer  = false;
            switch (text) {
                case "дата":
                    dateFormat = new SimpleDateFormat("d.MM.YYYY");
                    sendAnswer = true;
                    break;
                case "день":
                    dateFormat = new SimpleDateFormat("d");
                    sendAnswer = true;
                    break;
                case "месяц":
                    dateFormat = new SimpleDateFormat("MMMM");
                    sendAnswer = true;
                    break;
                case "год":
                    dateFormat = new SimpleDateFormat("YYYY");
                    sendAnswer = true;
                    break;
                case "время":
                    dateFormat = new SimpleDateFormat("H:mm:ss");
                    sendAnswer = true;
                    break;
                case "час":
                    dateFormat = new SimpleDateFormat("H");
                    sendAnswer = true;
                    break;
                case "минуты":
                    dateFormat = new SimpleDateFormat("m");
                    sendAnswer = true;
                    break;
                case "секунды":
                    dateFormat = new SimpleDateFormat("s");
                    sendAnswer = true;
                    break;
            }
            if(sendAnswer) {
                sendTextMessage(newMessage + dateFormat.format(Calendar.getInstance().getTime()));
            }
        }
    }
}
