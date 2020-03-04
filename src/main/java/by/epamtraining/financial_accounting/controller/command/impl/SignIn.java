package by.epamtraining.financial_accounting.controller.command.impl;

import by.epamtraining.financial_accounting.controller.command.Command;
import by.epamtraining.financial_accounting.service.UserContextHolder;
import by.epamtraining.financial_accounting.service.UserService;
import by.epamtraining.financial_accounting.service.exception.ServiceException;
import by.epamtraining.financial_accounting.service.factory.ServiceFactory;

public class SignIn implements Command {

    public String execute(String request){
//        if(UserContextHolder.getInstance().getActiveUser() != null){
//            return "You already Signed In";
//        }
        String response;

        if(request.contains(" ")){
            String[] commandDetails = request.split(" ");
            String login = commandDetails[0];
            String password = commandDetails[1];

            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            UserService userService = serviceFactory.getUserService();
            try {
                userService.signIn(login, password);
                response = "Welcome!";
            } catch (ServiceException servEx){
                // write to log
                response = "Error during login procedure: " + servEx.getMessage();
            }
        } else {
            response = "Incorrect data enter.";
        }
        return response;
    }
}
