package by.epamtraining.financial_accounting.controller.command.impl;

import by.epamtraining.financial_accounting.controller.command.Command;
import by.epamtraining.financial_accounting.service.UserService;
import by.epamtraining.financial_accounting.service.exception.ServiceException;
import by.epamtraining.financial_accounting.service.factory.ServiceFactory;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Logout implements Command {
    private static Logger log = Logger.getLogger(Logout.class.getName());

    public String execute(String request){
        String response;

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UserService userService = serviceFactory.getUserService();
        try{
            userService.logout();
        } catch (ServiceException servEx){
            log.log(Level.SEVERE, "Exception: ", servEx);
            response = servEx.getMessage();
        }
        response = "Buy!";
        return response;
    }
}
