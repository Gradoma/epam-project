package by.epamtraining.financial_accounting.controller.command.impl;

import by.epamtraining.financial_accounting.controller.command.Command;

public class WrongRequest implements Command {

    public String execute(String request){
        return "Wrong request";
    }
}
