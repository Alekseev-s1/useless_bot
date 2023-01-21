package com.example.useless_bot.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "subscription")
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sub_id")
    private long id;

    @Column(name = "chat_id")
    private long chatId;
    private String symbol;
    private double price;

    @Enumerated(EnumType.STRING)
    private Indicator indicator;
}
