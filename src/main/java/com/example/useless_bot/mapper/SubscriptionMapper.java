package com.example.useless_bot.mapper;

import com.example.useless_bot.model.Indicator;
import com.example.useless_bot.model.Subscription;

public class SubscriptionMapper {

    public static Subscription toSubscription(String message, long chatId) {
        String[] subElements = message.split(" ");

        Subscription subscription = new Subscription();

        subscription.setChatId(chatId);
        subscription.setSymbol(subElements[1].toUpperCase());
        subscription.setIndicator(Indicator.toIndicator(subElements[2]));
        subscription.setPrice(Double.parseDouble(subElements[3]));

        return subscription;
    }
}
