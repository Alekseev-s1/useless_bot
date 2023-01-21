package com.example.useless_bot.service;

import com.example.useless_bot.client.CryptoClient;
import com.example.useless_bot.model.Currency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
public class GetCurrencyServiceImpl implements GetCurrencyService {

    private final CryptoClient cryptoClient;

    @Autowired
    public GetCurrencyServiceImpl(CryptoClient cryptoClient) {
        this.cryptoClient = cryptoClient;
    }

    @Override
    public String getCurrency(Update update) {
        String symbol = retrieveSymbol(update);
        Currency currency = cryptoClient.getCurrency(symbol);
        return formatMessage(currency);
    }

    private String retrieveSymbol(Update update) {
        return update.getMessage().getText().replace("/", "");
    }

    private String formatMessage(Currency currency) {
        return String.format("%s:\n" +
                "%s$ | %.2f%% 1h | %.2f%% 24h",
                currency.getSymbol(),
                currency.getPrice(),
                currency.getChange1h(),
                currency.getChange24h());
    }
}
