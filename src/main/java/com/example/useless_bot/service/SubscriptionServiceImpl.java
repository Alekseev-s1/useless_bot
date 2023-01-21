package com.example.useless_bot.service;

import com.example.useless_bot.client.CryptoClient;
import com.example.useless_bot.mapper.SubscriptionMapper;
import com.example.useless_bot.model.Indicator;
import com.example.useless_bot.model.Notification;
import com.example.useless_bot.model.Subscription;
import com.example.useless_bot.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Collection;
import java.util.NoSuchElementException;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final CryptoClient cryptoClient;

    @Autowired
    public SubscriptionServiceImpl(SubscriptionRepository subscriptionRepository,
                                   CryptoClient cryptoClient) {
        this.subscriptionRepository = subscriptionRepository;
        this.cryptoClient = cryptoClient;
    }

    @Override
    public void subscribe(Update update) {
        String message = update.getMessage().getText();
        long chatId = update.getMessage().getChatId();
        Subscription subscription = SubscriptionMapper.toSubscription(message, chatId);
        subscriptionRepository.save(subscription);
    }

    @Override
    public void removeSubscription(long id) {
        Subscription subscription = getSubscription(id);
        subscriptionRepository.delete(subscription);
    }

    @Override
    public String getSubscriptions() {
        Collection<Subscription> subscriptions = subscriptionRepository.findAll();
        return formatListMessage(subscriptions);
    }

    @Override
    public Notification checkPrice() {
        for (Subscription subscription : subscriptionRepository.findAll()) {
            double currentPrice = cryptoClient.getSimplePrice(subscription.getSymbol());
            double subscriptionPrice = subscription.getPrice();
            Indicator indicator = subscription.getIndicator();

            if (indicator.equals(Indicator.LESS) && currentPrice < subscriptionPrice) {
                removeSubscription(subscription.getId());
                return notifyPriceChange(subscription);
            }
            if (indicator.equals(Indicator.MORE) && currentPrice > subscriptionPrice) {
                removeSubscription(subscription.getId());
                return notifyPriceChange(subscription);
            }
        }
        return null;
    }

    private Notification notifyPriceChange(Subscription subscription) {
        String message;
        long chatId = subscription.getChatId();

        if (subscription.getIndicator().equals(Indicator.LESS)) {
            message = String.format("\uD83D\uDD34 %s меньше %s$!", subscription.getSymbol(), subscription.getPrice());
        } else {
            message = String.format("\uD83D\uDFE2 %s больше %s$!", subscription.getSymbol(), subscription.getPrice());
        }

        return new Notification(chatId, message);
    }

    private String formatListMessage(Collection<Subscription> subscriptions) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Текущий список отслеживаемых цен:\n");

        for (Subscription subscription : subscriptions) {
            stringBuilder.append(String.format("%s) %s %s %s$\n",
                    subscription.getId(),
                    subscription.getSymbol(),
                    subscription.getIndicator().toString().toLowerCase(),
                    subscription.getPrice()));
        }

        return stringBuilder.toString();
    }

    private Subscription getSubscription(long id) {
        return subscriptionRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }
}
