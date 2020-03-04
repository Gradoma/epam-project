package by.epamtraining.financial_accounting.controller.command.impl;

import by.epamtraining.financial_accounting.controller.command.Command;
import by.epamtraining.financial_accounting.service.RecordService;
import by.epamtraining.financial_accounting.service.UserContextHolder;
import by.epamtraining.financial_accounting.service.exception.ServiceException;
import by.epamtraining.financial_accounting.service.factory.ServiceFactory;

public class GetBalance implements Command {

    @Override
    public String execute(String request) {
//        if(UserContextHolder.getInstance().getActiveUser() == null){
//            return "You can't get balance. Sign In or Register first.";
//        }
        String response;

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        RecordService recordService = serviceFactory.getRecordService();
        try {
            double balance = recordService.getBalance();
            response = "Your actual balance: " + balance;
        } catch (ServiceException servEx){
            response = "Error during get balance procedure: " + servEx.getMessage();
        }
        return response;
    }
}
