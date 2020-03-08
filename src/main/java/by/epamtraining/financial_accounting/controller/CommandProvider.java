package by.epamtraining.financial_accounting.controller;

import by.epamtraining.financial_accounting.controller.command.Command;
import by.epamtraining.financial_accounting.controller.command.CommandName;
import by.epamtraining.financial_accounting.controller.command.impl.*;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CommandProvider {
    private final Map<CommandName, Command> repository = new HashMap<>();
    private static Logger log = Logger.getLogger(CommandProvider.class.getName());

    CommandProvider(){
        repository.put(CommandName.SIGN_IN, new SignIn());
        repository.put(CommandName.SIGN_UP, new SignUp());
        repository.put(CommandName.LOGOUT, new Logout());
        repository.put(CommandName.ADD_RECORD, new AddRecord());
        repository.put(CommandName.GET_RECORDS, new GetRecords());
        repository.put(CommandName.GET_BALANCE, new GetBalance());
        repository.put(CommandName.GET_RECORDS_IN_PERIOD, new GetRecInPeriod());
        repository.put(CommandName.GET_ALL_REC, new GetAllRecords());
        repository.put(CommandName.WRONG_REQUEST, new WrongRequest());
    }

    Command getCommand (String name) {
        CommandName commandName = null;
        Command command = null;

        try {
        commandName = CommandName.valueOf(name.toUpperCase());
        command = repository.get(commandName);
        } catch (IllegalArgumentException | NullPointerException e){
            log.log(Level.SEVERE, "Exception: ", e);
            command = repository.get(CommandName.WRONG_REQUEST);
        }
        return command;
    }
}
