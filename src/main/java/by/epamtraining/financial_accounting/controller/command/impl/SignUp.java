package by.epamtraining.financial_accounting.controller.command.impl;

import by.epamtraining.financial_accounting.controller.command.Command;
import by.epamtraining.financial_accounting.service.UserService;
import by.epamtraining.financial_accounting.service.exception.ServiceException;
import by.epamtraining.financial_accounting.service.factory.ServiceFactory;

import java.util.logging.Level;
import java.util.logging.Logger;

public class SignUp implements Command {
    private static Logger log = Logger.getLogger(SignUp.class.getName());

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
                log.log(Level.SEVERE, "Exception: ", servEx);
                response = "Error during registration procedure: " + servEx.getMessage();
            }
        } else {
            response = "Incorrect data enter.";
        }
        return response;
    }
}
