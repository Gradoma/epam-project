package by.epamtraining.financial_accounting.controller;

import by.epamtraining.financial_accounting.controller.command.Command;
import by.epamtraining.financial_accounting.service.UserContextHolder;

public class Controller {
    private final CommandProvider commandProvider = new CommandProvider();

    private final char paramDelimeter = ' ';

    public String executeTask(String request){
        String commandName;
        Command executionCommand;

        if(request == null){
            return "Request can't be null";
        }
        if(request.contains(" ")){
            commandName = request.substring(0, request.indexOf(paramDelimeter));
        } else {
            commandName = request;
        }
//        System.out.println("controller parse result: " + commandName);                                // testing
        executionCommand = commandProvider.getCommand(commandName);
//        System.out.println(executionCommand == null);                                               // testing

        String commandParameters = request.substring(request.indexOf(paramDelimeter)+1).trim();
//        System.out.println("comand param parsing result = " + commandParameters + ".");                   // testing
//        System.out.println("comand param trimmed = " + commandParameters.trim() + ".");                    // testing
//        String response = executionCommand.execute(commandParameters);
//
//        return response;
        return executionCommand.execute(commandParameters);
    }

    public String getAvailableCommand(){
        String availableCommand = "\nList of available commands:";
        if(UserContextHolder.getInstance().getActiveUser() == null){
            availableCommand += "\nsign_in - to sign in (sign_in login password)";
            availableCommand += "\nsign_up - to create new account (sign_up login password)";
        } else {
            availableCommand += "\nadd_record - add new record (add_record dd-mm-yyyy sum ; OR: add_record sum (current date)";
            availableCommand += "\nget_records - get all your records";
            availableCommand += "\nget_balance - get current balance";
            availableCommand += "\nget_records_in_period - get your records between 2 dates(get_records_in_period dd-mm-yyyy dd-mm-yyyy)";
            availableCommand += "\nlogout - to leave your account";
        }
        return availableCommand;
    }
}
