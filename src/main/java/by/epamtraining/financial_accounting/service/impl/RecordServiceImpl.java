package by.epamtraining.financial_accounting.service.impl;

import by.epamtraining.financial_accounting.bean.Record;
import by.epamtraining.financial_accounting.bean.Role;
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

    public void addRecord(String valueString, String dateString, String description) throws ServiceException {
        UserContextHolder userContextHolder = UserContextHolder.getInstance();
        if(userContextHolder.getActiveUser() == null){
            throw new ServiceException("You can't add record. Sign In or Register first.");
        }

        if (valueString == null || valueString.isEmpty() || dateString == null || description == null){
            throw new ServiceException("No information to create record, specify sum at least");
        }

        Record newRecord;
        String userLogin = userContextHolder.getActiveUser().getLogin();
        Date date;
        try {
            double value = Double.valueOf(valueString);
            if(value > 0){
                description = "Income";
            } else if (getBalance() + value < 0){
                throw new ServiceException("Not enough money");
            }
            if (dateString.length() > 0) {
                date = DATE_FORMAT.parse(dateString);
                if(description.length() > 0){
                    newRecord = new Record(userLogin, value, date, description);
                } else {
                    newRecord = new Record(userLogin, value, date);
                }
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
        UserContextHolder userContextHolder = UserContextHolder.getInstance();
        if(userContextHolder.getActiveUser() == null){
            throw new ServiceException("You can't get records. Sign In or Register first.");
        }

        User currentUser = userContextHolder.getActiveUser();
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
        UserContextHolder userContextHolder = UserContextHolder.getInstance();
        if(userContextHolder.getActiveUser() == null){
            throw new ServiceException("You can't get records. Sign In or Register first.");
        }

        if(date1.after(date2)){
            throw new ServiceException("End date of period can't be early than start date.");
        }

        User currentUser = userContextHolder.getActiveUser();
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
    public List<Record> getAllRecords() throws ServiceException{
        UserContextHolder userContextHolder = UserContextHolder.getInstance();
        if(userContextHolder.getActiveUser() == null){
            throw new ServiceException("You can't get records. Sign In or Register first.");
        }

        User currentUser = userContextHolder.getActiveUser();
        if(currentUser.getRole() == Role.USER){
            throw new ServiceException("Can't execute, not enough rights.");
        }
        DAOFactory daoFactory = DAOFactory.getInstance();
        RecordDAO recordDAO = daoFactory.getRecordDAO();
        try{
            return recordDAO.getAllRecords();
        } catch (DAOException daoEx){
            throw new ServiceException(daoEx);
        }
    }

    @Override
    public double getBalance() throws ServiceException {
        UserContextHolder userContextHolder = UserContextHolder.getInstance();
        if(userContextHolder.getActiveUser() == null){
            throw new ServiceException("You can't get balance. Sign In or Register first.");
        }

        User currentUser = userContextHolder.getActiveUser();
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
