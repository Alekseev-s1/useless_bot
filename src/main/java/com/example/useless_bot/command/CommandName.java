package com.example.useless_bot.command;

public enum CommandName {
    START("/start"),
    HELP("/help"),
    SUBSCRIBE("/addsub"),
    REMOVE("/removesub"),
    SUBLIST("/listsub"),
    NO("nocommand");

    private final String commandName;

    CommandName(String commandName) {
        this.commandName = commandName;
    }

    public String getCommandName() {
        return commandName;
    }
}