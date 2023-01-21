package com.example.useless_bot.command;

import com.example.useless_bot.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class StartCommand implements Command {

    private final SendBotMessageService sendBotMessageService;

    private final String startMessage = "Привет! Я очередной бесполезный бот. Мои команды можно узнать через /help";

    public StartCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        String chatId = update.getMessage().getChatId().toString();
        sendBotMessageService.sendImage(chatId, startMessage, "images/123.png");
    }
}
