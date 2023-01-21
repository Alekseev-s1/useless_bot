package com.example.useless_bot.command;

import com.example.useless_bot.service.SendBotMessageService;
import com.example.useless_bot.service.SubscriptionService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class ListSubCommand implements Command {

    private final SendBotMessageService sendBotMessageService;
    private final SubscriptionService subscriptionService;

    public ListSubCommand(SendBotMessageService sendBotMessageService,
                          SubscriptionService subscriptionService) {
        this.sendBotMessageService = sendBotMessageService;
        this.subscriptionService = subscriptionService;
    }

    @Override
    public void execute(Update update) {
        String message = subscriptionService.getSubscriptions();
        sendBotMessageService.sendMessage(update.getMessage().getChatId(), message);
    }
}
