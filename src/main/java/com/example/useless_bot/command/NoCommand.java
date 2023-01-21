package com.example.useless_bot.command;

import com.example.useless_bot.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class NoCommand implements Command {

    private final SendBotMessageService sendBotMessageService;

    private final String noCommandMessage = "Я поддерживаю команды, начинающиеся со слеша(/).\n"
            + "Чтобы посмотреть список комманд введи /help";

    public NoCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        long chatId = update.getMessage().getChatId();
        sendBotMessageService.sendMessage(chatId, noCommandMessage);
    }
}
