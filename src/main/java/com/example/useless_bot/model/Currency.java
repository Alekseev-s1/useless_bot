package com.example.useless_bot.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Currency {
    private String symbol;
    private double price;
    private double change1h;
    private double change24h;
}
