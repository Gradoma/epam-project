package by.epamtraining.financial_accounting.controller.command.impl;

import by.epamtraining.financial_accounting.controller.command.Command;
import by.epamtraining.financial_accounting.service.UserService;
import by.epamtraining.financial_accounting.service.exception.ServiceException;
import by.epamtraining.financial_accounting.service.factory.ServiceFactory;

public class SignUp implements Command {
    public String execute(String request){
        String response;

        if(request.contains(" ")){
            String[] commandDetails = request.split(" ");
            String login = commandDetails[0];
            String password = commandDetails[1];

            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            UserService userService = serviceFactory.getUserService();
            try{
                userService.signUp(login, password);
                response = "You have been registered! Welcome!";
            } catch (ServiceException servEx){
                // write log
                response = "Error during registration procedure: " + servEx.getMessage();
            }
        } else {
            response = "Error during registration procedure: Incorrect data enter.";
        }
        return response;
    }
}
