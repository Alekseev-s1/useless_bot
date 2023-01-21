package com.example.useless_bot.mapper;

import com.example.useless_bot.model.Currency;
import kong.unirest.JsonNode;
import kong.unirest.json.JSONObject;

public class CurrencyMapper {

    public static Currency toCurrency(JsonNode json1h, JsonNode json24h) {
        Currency currency = new Currency();

        JSONObject json1hObject = json1h.getObject();
        JSONObject json24hObject = json24h.getObject();

        currency.setSymbol(json1hObject.getString("symbol").replace("USDT", ""));
        currency.setPrice(json1hObject.getDouble("lastPrice"));
        currency.setChange1h(json1hObject.getDouble("priceChangePercent"));
        currency.setChange24h(json24hObject.getDouble("priceChangePercent"));

        return currency;
    }
}
