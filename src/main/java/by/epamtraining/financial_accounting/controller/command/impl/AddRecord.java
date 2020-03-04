package by.epamtraining.financial_accounting.controller.command.impl;

import by.epamtraining.financial_accounting.bean.Record;
import by.epamtraining.financial_accounting.controller.command.Command;
import by.epamtraining.financial_accounting.service.RecordService;
import by.epamtraining.financial_accounting.service.UserContextHolder;
import by.epamtraining.financial_accounting.service.UserService;
import by.epamtraining.financial_accounting.service.exception.ServiceException;
import by.epamtraining.financial_accounting.service.factory.ServiceFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AddRecord implements Command {

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");

    public String execute(String request){
//        if(UserContextHolder.getInstance().getActiveUser() == null){
//            return "You can't add record. Sign In or Register first.";
//        }
        String response;

        String dateString;
        String valueString;
        if(request.contains(" ")){
            String[] recordDetails = request.split(" ");
            dateString = recordDetails[0];
            valueString = recordDetails[1];
        } else {
            valueString = request;
            dateString = "";
        }


//        System.out.println("date String = " + dateString);                          //testing
//        System.out.println("value string = " + valueString);                         // testing

        try {
//            Date date;
//            if(dateString.length() > 0){
//                date = DATE_FORMAT.parse(dateString);
//            } else {
//                date = new Date();
//            }
//            double value = Double.valueOf(valueString);

            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            RecordService recordService = serviceFactory.getRecordService();
            recordService.addRecord(valueString, dateString);

            response = "Success! New record was added!";
        } catch (ServiceException servEx){
            // write log
            response = "Error during add record procedure: " + servEx.getMessage();
        }
        return response;
    }
}
