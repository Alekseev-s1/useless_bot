package com.example.useless_bot.service;

import com.example.useless_bot.model.Notification;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface SubscriptionService {
    void subscribe(Update update);

    void removeSubscription(long id);

    String getSubscriptions();

    Notification checkPrice();
}
