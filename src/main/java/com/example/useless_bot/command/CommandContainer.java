package com.example.useless_bot.command;

import com.example.useless_bot.service.GetCurrencyService;
import com.example.useless_bot.service.SendBotMessageService;
import com.example.useless_bot.service.SubscriptionService;

import java.util.HashMap;
import java.util.Map;

import static com.example.useless_bot.command.CommandName.*;

public class CommandContainer {

    private final Map<String, Command> commandMap;
    private final CurrencyCommand currencyCommand;

    public CommandContainer(SendBotMessageService sendBotMessageService,
                            GetCurrencyService getCurrencyService,
                            SubscriptionService subscriptionService) {
        commandMap = new HashMap<>();

        commandMap.put(START.getCommandName(), new StartCommand(sendBotMessageService));
        commandMap.put(HELP.getCommandName(), new HelpCommand(sendBotMessageService));
        commandMap.put(NO.getCommandName(), new NoCommand(sendBotMessageService));
        commandMap.put(SUBSCRIBE.getCommandName(), new AddSubCommand(subscriptionService));
        commandMap.put(REMOVE.getCommandName(), new RemoveSubCommand(subscriptionService));
        commandMap.put(SUBLIST.getCommandName(), new ListSubCommand(sendBotMessageService, subscriptionService));

        currencyCommand = new CurrencyCommand(sendBotMessageService, getCurrencyService);
    }

    public Command findCommand(String commandName) {
        return commandMap.getOrDefault(commandName, currencyCommand);
    }
}
