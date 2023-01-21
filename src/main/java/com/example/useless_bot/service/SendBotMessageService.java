package com.example.useless_bot.service;

public interface SendBotMessageService {

    void sendMessage(long chatId, String message);

    void sendImage(String chatId, String message, String imagePath);
}
