package com.javarush.task.task30.task3008;

import java.io.Serializable;

/**
 * Created by user on 23.09.2017.
 * отвечает за пересылаемые сообщения
 */
public class Message implements Serializable{
    private final MessageType type;
    private final String data;

    public Message(MessageType type) {
        this(type, null);
    }

    public Message(MessageType type, String data) {
        this.type = type;
        this.data = data;
    }

    public MessageType getType() {
        return type;
    }

    public String getData() {
        return data;
    }
}
