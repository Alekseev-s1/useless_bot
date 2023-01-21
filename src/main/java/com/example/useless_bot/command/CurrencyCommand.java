package com.example.useless_bot.command;

import com.example.useless_bot.service.GetCurrencyService;
import com.example.useless_bot.service.SendBotMessageService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class CurrencyCommand implements Command {

    private final SendBotMessageService sendBotMessageService;
    private final GetCurrencyService getCurrencyService;

    public CurrencyCommand(SendBotMessageService sendBotMessageService,
                           GetCurrencyService getCurrencyService) {
        this.sendBotMessageService = sendBotMessageService;
        this.getCurrencyService = getCurrencyService;
    }

    @Override
    public void execute(Update update) {
        long chatId = update.getMessage().getChatId();
        String message = getCurrencyService.getCurrency(update);
        sendBotMessageService.sendMessage(chatId, message);
    }
}
