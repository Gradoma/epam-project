package by.epamtraining.financial_accounting.controller.command.impl;

import by.epamtraining.financial_accounting.bean.Record;
import by.epamtraining.financial_accounting.controller.command.Command;
import by.epamtraining.financial_accounting.service.RecordService;
import by.epamtraining.financial_accounting.service.UserContextHolder;
import by.epamtraining.financial_accounting.service.exception.ServiceException;
import by.epamtraining.financial_accounting.service.factory.ServiceFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GetRecInPeriod implements Command {
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");

    public String execute(String request){
        if(UserContextHolder.getInstance().getActiveUser() == null){
            return "You can't get records. Sign In or Register first.";
        }
        String response = "";

        Date date1;
        Date date2;
        if(request.contains(" ")){
            String[] datesString = request.split(" ");
            try {
                date1 = DATE_FORMAT.parse(datesString[0]);
                date2 = DATE_FORMAT.parse(datesString[1]);

                ServiceFactory serviceFactory = ServiceFactory.getInstance();
                RecordService recordService = serviceFactory.getRecordService();
                List<Record> recordList = recordService.getUserRecords();

                for(Record rec : recordList){
                    if(rec.getDate().after(date1) && rec.getDate().before(date2)){
                        String valueSign = "";
                        if(rec.getOperationValue() > 0){
                            valueSign = "+";
                        }
                        response += rec.getUserLogin() + " " + DATE_FORMAT.format(rec.getDate()) + " " + valueSign +
                                rec.getOperationValue() + " " + rec.getSpendingType() + " " + rec.getDescription()+ "\n";
                    }
                }
            } catch (ParseException ex){
                // write log
                response = "Incorrect format.";
            } catch (ServiceException servEx){
                // write log
                response = servEx.getMessage();
            }
        } else return "Incorrect date format";
        return response;
    }
}
