package com.example.useless_bot.command;

import com.example.useless_bot.service.SubscriptionService;
import org.telegram.telegrambots.meta.api.objects.Update;

public class RemoveSubCommand implements Command {

    private final SubscriptionService subscriptionService;

    public RemoveSubCommand(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @Override
    public void execute(Update update) {
        long subId = Long.parseLong(update.getMessage().getText().split(" ")[1]);
        subscriptionService.removeSubscription(subId);
    }
}
