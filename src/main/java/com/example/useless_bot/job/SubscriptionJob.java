package com.example.useless_bot.job;

import com.example.useless_bot.model.Notification;
import com.example.useless_bot.service.SendBotMessageService;
import com.example.useless_bot.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionJob {

    private final SendBotMessageService sendBotMessageService;
    private final SubscriptionService subscriptionService;

    @Autowired
    public SubscriptionJob(SubscriptionService subscriptionService,
                           SendBotMessageService sendBotMessageService) {
        this.sendBotMessageService = sendBotMessageService;
        this.subscriptionService = subscriptionService;
    }

    @Scheduled(fixedRateString = "${bot.checktime}")
    public void checkPrice() {
        Notification notification = subscriptionService.checkPrice();

        if (notification != null) {
            sendBotMessageService.sendMessage(notification.getChatId(), notification.getMessage());
        }
    }
}
