package com.example.useless_bot.service;

import org.telegram.telegrambots.meta.api.objects.Update;

public interface GetCurrencyService {

    String getCurrency(Update update);
}
