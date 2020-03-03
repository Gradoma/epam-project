package by.epamtraining.financial_accounting.service.impl;

import by.epamtraining.financial_accounting.bean.Record;
import by.epamtraining.financial_accounting.bean.User;
import by.epamtraining.financial_accounting.dao.RecordDAO;
import by.epamtraining.financial_accounting.dao.exception.DAOException;
import by.epamtraining.financial_accounting.dao.factory.DAOFactory;
import by.epamtraining.financial_accounting.service.RecordService;
import by.epamtraining.financial_accounting.service.UserContextHolder;
import by.epamtraining.financial_accounting.service.exception.ServiceException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RecordServiceImpl implements RecordService {

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");

    public void addRecord(String valueString, String dateString) throws ServiceException {

        if (valueString == null || valueString.isEmpty() || dateString == null){
            throw new ServiceException("No information to create record, specify sum at least");
        }

        Record newRecord;
        String userLogin = UserContextHolder.getInstance().getActiveUser().getLogin();
        Date date;
        try {
            double value = Double.valueOf(valueString);
            if (dateString.length() > 0) {
                date = DATE_FORMAT.parse(dateString);
                newRecord = new Record(userLogin, value, date);
            } else {
                newRecord = new Record(userLogin, value);
            }
        } catch (NumberFormatException | ParseException e){
            throw new ServiceException("Incorrect format.", e);
        }

        DAOFactory daoFactory = DAOFactory.getInstance();
        RecordDAO recordDAO = daoFactory.getRecordDAO();
        try {
            recordDAO.addRecord(newRecord);
        } catch (DAOException daoEx) {
            throw new ServiceException(daoEx);
        }
    }

    public List<Record> getUserRecords() throws ServiceException{
        User currentUser = UserContextHolder.getInstance().getActiveUser();

        DAOFactory daoFactory = DAOFactory.getInstance();
        RecordDAO recordDAO = daoFactory.getRecordDAO();
        try {
            return recordDAO.getUserRecords(currentUser);
        } catch (DAOException daoEx){
            throw new ServiceException(daoEx);
        }
    }

    @Override
    public List<Record> getUserRecordsInPeriod(Date date1, Date date2) throws ServiceException {

        if(date1.after(date2)){
            throw new ServiceException("End date of period can't be early than start date.");
        }

        User currentUser = UserContextHolder.getInstance().getActiveUser();
        DAOFactory daoFactory = DAOFactory.getInstance();
        RecordDAO recordDAO = daoFactory.getRecordDAO();
        List<Record> userRecords;

        try {
            userRecords = recordDAO.getUserRecords(currentUser);
        } catch (DAOException daoEx){
            throw new ServiceException(daoEx);
        }
        List<Record> recordsInPeriod = new ArrayList<>();

        for(Record rec : userRecords){
            if(rec.getDate().after(date1) && rec.getDate().before(date2)){
                recordsInPeriod.add(rec);
            }
        }
        return recordsInPeriod;
    }

    @Override
    public double getBalance() throws ServiceException {
        User currentUser = UserContextHolder.getInstance().getActiveUser();

        DAOFactory daoFactory = DAOFactory.getInstance();
        RecordDAO recordDAO = daoFactory.getRecordDAO();

        try {
            List<Record> userRecords = recordDAO.getUserRecords(currentUser);
            double balance = 0;
            for(Record rec : userRecords){
                balance += rec.getOperationValue();
            }
            return balance;
        } catch (DAOException daoEx){
            throw new ServiceException(daoEx);
        }

    }
}
