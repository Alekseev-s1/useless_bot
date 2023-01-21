package com.example.useless_bot.bot;

import com.example.useless_bot.command.CommandContainer;
import com.example.useless_bot.service.GetCurrencyService;
import com.example.useless_bot.service.SendBotMessageServiceImpl;
import com.example.useless_bot.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import static com.example.useless_bot.command.CommandName.NO;

@Component
public class UselessBot extends TelegramLongPollingBot {

    public final static String COMMAND_PREFIX = "/";
    private final CommandContainer commandContainer;

    public UselessBot(GetCurrencyService getCurrencyService,
                      SubscriptionService subscriptionService) {
        commandContainer = new CommandContainer(new SendBotMessageServiceImpl(this), getCurrencyService, subscriptionService);
    }

    @Value("${bot.token}")
    private String token;

    @Value("${bot.username}")
    private String username;

    @Override
    public String getBotUsername() {
        return username;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String message = update.getMessage().getText();
            if (message.startsWith(COMMAND_PREFIX)) {
                String commandName = message.split(" ")[0].toLowerCase();
                commandContainer.findCommand(commandName).execute(update);
            } else {
                commandContainer.findCommand(NO.getCommandName()).execute(update);
            }
        }
    }
}
