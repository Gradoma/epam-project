package by.epamtraining.financial_accounting.controller.command.impl;

import by.epamtraining.financial_accounting.controller.command.Command;
import by.epamtraining.financial_accounting.service.UserService;
import by.epamtraining.financial_accounting.service.exception.ServiceException;
import by.epamtraining.financial_accounting.service.factory.ServiceFactory;

public class Logout implements Command {
    public String execute(String request){
        String response;

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UserService userService = serviceFactory.getUserService();
        try{
            userService.logout();
        } catch (ServiceException servEx){
            // write log
            response = servEx.getMessage();
        }
        response = "Buy!";
        return response;
    }
}
