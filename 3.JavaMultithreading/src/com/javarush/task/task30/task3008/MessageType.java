package com.javarush.task.task30.task3008;

/**
 * Created by user on 23.09.2017.
 * отвечает за тип сообщений пересылаемых между клиентом и сервером
 */
public enum MessageType {
    NAME_REQUEST,
    USER_NAME,
    NAME_ACCEPTED,
    TEXT,
    USER_ADDED,
    USER_REMOVED
}
