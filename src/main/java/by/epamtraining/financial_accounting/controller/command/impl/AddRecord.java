package by.epamtraining.financial_accounting.controller.command.impl;

import by.epamtraining.financial_accounting.controller.command.Command;
import by.epamtraining.financial_accounting.service.RecordService;
import by.epamtraining.financial_accounting.service.exception.ServiceException;
import by.epamtraining.financial_accounting.service.factory.ServiceFactory;

public class AddRecord implements Command {
    public String execute(String request){
        String response;
        String dateString = "";
        String valueString;
        String description = "";

        if(!request.contains(" ")){
            valueString = request;
            dateString = "";
            description = "";
        } else {
            String[] recordDetails = request.split(" ");
            switch (recordDetails.length){
                case 2:
                    dateString = recordDetails[0];
                    valueString = recordDetails[1];
                    break;
                case 3:
                    dateString = recordDetails[0];
                    valueString = recordDetails[1];
                    description = recordDetails[2];
                    break;
                default:
                    valueString = recordDetails[0];
            }
        }
        try {
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            RecordService recordService = serviceFactory.getRecordService();
            recordService.addRecord(valueString, dateString, description);

            response = "Success! New record was added!";
        } catch (ServiceException servEx){
            //write log
            response = "Error during add record procedure: " + servEx.getMessage();
        }
        return response;
    }
}
