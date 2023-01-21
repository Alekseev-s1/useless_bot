package com.example.useless_bot.command;

import com.example.useless_bot.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class HelpCommand implements Command {

    private final SendBotMessageService sendBotMessageService;

    private final String helpMessage = "Список моих команд: ...";

    public HelpCommand(SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
    }

    @Override
    public void execute(Update update) {
        long chatId = update.getMessage().getChatId();
        sendBotMessageService.sendMessage(chatId, helpMessage);
    }
}
