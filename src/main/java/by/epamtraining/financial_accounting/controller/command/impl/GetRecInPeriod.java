package by.epamtraining.financial_accounting.controller.command.impl;

import by.epamtraining.financial_accounting.bean.Record;
import by.epamtraining.financial_accounting.controller.command.Command;
import by.epamtraining.financial_accounting.service.RecordService;
import by.epamtraining.financial_accounting.service.exception.ServiceException;
import by.epamtraining.financial_accounting.service.factory.ServiceFactory;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class GetRecInPeriod implements Command {
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");

    public String execute(String request){
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
                List<Record> recordList = recordService.getUserRecordsInPeriod(date1, date2);
                if(recordList.size() > 0){
                    StringBuilder responseBuilder = new StringBuilder();
                    String spaceDelim = " ";
                    for(Record rec : recordList){
                        String valueSign = "";
                        if(rec.getOperationValue() > 0){
                            valueSign = "+";
                        }
                        responseBuilder.append(rec.getUserLogin()).append(spaceDelim).append(DATE_FORMAT.format(rec.getDate()));
                        responseBuilder.append(spaceDelim).append(valueSign).append(rec.getOperationValue());
                        responseBuilder.append(spaceDelim).append(rec.getDescription()).append("\n");
                    }
                    response = responseBuilder.toString();
                } else {
                    response = "No records during specified period.";
                }
            } catch (ParseException ex){
                //write log
                response = "Incorrect date format.";
            } catch (ServiceException servEx){
                //write log
                response = "Error during get balance procedure: " + servEx.getMessage();
            }
        } else return "Incorrect request format";
        return response;
    }
}
