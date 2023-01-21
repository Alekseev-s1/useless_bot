package com.example.useless_bot.client;

import com.example.useless_bot.mapper.CurrencyMapper;
import com.example.useless_bot.model.Currency;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CryptoClient {

    @Value("${api.path}")
    private String apiPath;

    public Currency getCurrency(String symbol) {
        JsonNode response1h = Unirest.get(apiPath)
                .queryString("symbol", String.format("%sUSDT", symbol).toUpperCase())
                .queryString("windowSize", "1h")
                .asJson()
                .getBody();

        JsonNode response24h = Unirest.get(apiPath)
                .queryString("symbol", String.format("%sUSDT", symbol).toUpperCase())
                .queryString("windowSize", "1d")
                .asJson()
                .getBody();

        return CurrencyMapper.toCurrency(response1h, response24h);
    }

    public double getSimplePrice(String symbol) {
        JsonNode response = Unirest.get(apiPath + "/price")
                .queryString("symbol", String.format("%sUSDT", symbol).toUpperCase())
                .asJson()
                .getBody();

        return response.getObject().getDouble("price");
    }
}
