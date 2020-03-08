package by.epamtraining.financial_accounting.controller.command.impl;

import by.epamtraining.financial_accounting.bean.Record;
import by.epamtraining.financial_accounting.controller.command.Command;
import by.epamtraining.financial_accounting.service.RecordService;
import by.epamtraining.financial_accounting.service.exception.ServiceException;
import by.epamtraining.financial_accounting.service.factory.ServiceFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class GetAllRecords implements Command {
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");

    @Override
    public String execute(String request) {
        String response = "";

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        RecordService recordService = serviceFactory.getRecordService();

        try {
            List<Record> allRecordsList = recordService.getAllRecords();
            if(allRecordsList.size() > 0){
                for(Record rec : allRecordsList){
                    String valueSign = "";
                    if(rec.getOperationValue() > 0){
                        valueSign = "+";
                    }
                    response += rec.getUserLogin() + " " + DATE_FORMAT.format(rec.getDate()) + " " + valueSign +
                            rec.getOperationValue() + " " + rec.getDescription()+ "\n";
                }
            } else {
                response = "No any records.";
            }
        } catch (ServiceException servEx){
            // write log
            response = "Error during get records procedure: " + servEx.getMessage();
        }
        return response;
    }
}
