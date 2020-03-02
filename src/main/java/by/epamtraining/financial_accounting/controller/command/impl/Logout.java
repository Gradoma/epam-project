package by.epamtraining.financial_accounting.controller.command.impl;

import by.epamtraining.financial_accounting.controller.command.Command;
import by.epamtraining.financial_accounting.service.UserContextHolder;
import by.epamtraining.financial_accounting.service.UserService;
import by.epamtraining.financial_accounting.service.factory.ServiceFactory;

public class Logout implements Command {

    public String execute(String request){
        if(UserContextHolder.getInstance().getActiveUser() == null){
            return "You can't logout. Sign In or Register first.";
        }
        String response = null;

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UserService userService = serviceFactory.getUserService();
        userService.logout();

        response = "Buy!";
        return response;
    }
}
