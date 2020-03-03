package by.epamtraining.financial_accounting.controller.command.impl;

import by.epamtraining.financial_accounting.bean.Record;
import by.epamtraining.financial_accounting.controller.command.Command;
import by.epamtraining.financial_accounting.service.RecordService;
import by.epamtraining.financial_accounting.service.UserContextHolder;
import by.epamtraining.financial_accounting.service.exception.ServiceException;
import by.epamtraining.financial_accounting.service.factory.ServiceFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class GetRecords implements Command {
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");

    public String execute(String request){
        if(UserContextHolder.getInstance().getActiveUser() == null){
            return "You can't get records. Sign In or Register first.";
        }
        String response = "";

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        RecordService recordService = serviceFactory.getRecordService();
        try {
            List<Record> recordList = recordService.getUserRecords();
            if(recordList.size() > 0){
                for(Record rec : recordList){
                    String valueSign = "";
                    if(rec.getOperationValue() > 0){
                        valueSign = "+";
                    }
                    response += rec.getUserLogin() + " " + DATE_FORMAT.format(rec.getDate()) + " " + valueSign +
                            rec.getOperationValue() + " " + rec.getSpendingType() + " " + rec.getDescription()+ "\n";
                }
            } else {
                response = "You have no any records.";
            }
        } catch (ServiceException servEx){
            // write log
            response = "Error during get records procedure: " + servEx.getMessage();
        }
        return response;
    }
}
