package com.example.useless_bot.command;

import com.example.useless_bot.service.SubscriptionService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class AddSubCommand implements Command {

    private final SubscriptionService subscriptionService;

    public AddSubCommand(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @Override
    public void execute(Update update) {
        subscriptionService.subscribe(update);
    }
}
