package com.example.useless_bot.repository;

import com.example.useless_bot.model.Subscription;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Component
public class SubscriptionStorage {

    public static long SUB_ID = 1;

    private final Map<Long, Subscription> subscriptionMap = new HashMap<>();

    public void save(Subscription subscription) {
        subscriptionMap.put(subscription.getId(), subscription);
    }

    public Collection<Subscription> getSubscriptionList() {
        return subscriptionMap.values();
    }

    public void delete(long id) {
        subscriptionMap.remove(id);
    }
}
