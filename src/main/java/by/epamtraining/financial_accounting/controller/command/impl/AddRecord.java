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
        String response;

        String dateString = "";
        String valueString = "";
        String description = "";
//        if(request.contains(" ")){                                    //DONT DELETE!!!!
//            String[] recordDetails = request.split(" ");
//            dateString = recordDetails[0];
//            valueString = recordDetails[1];
//        } else {
//            valueString = request;
//            dateString = "";
//        }

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
            recordService.addRecord(valueString, dateString, description);

            response = "Success! New record was added!";
        } catch (ServiceException servEx){
            // write log
            response = "Error during add record procedure: " + servEx.getMessage();
        }
        return response;
    }
}
