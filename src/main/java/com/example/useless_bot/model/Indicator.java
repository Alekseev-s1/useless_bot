package com.example.useless_bot.model;

import java.util.NoSuchElementException;

public enum Indicator {
    MORE("more"),
    LESS("less");

    private String name;

    Indicator(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Indicator toIndicator(String message) {
        for (Indicator indicator : Indicator.values()) {
            if (indicator.getName().equals(message)) {
                return indicator;
            }
        }
        throw new NoSuchElementException();
    }
}
