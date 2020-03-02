package by.epamtraining.financial_accounting.service.impl;

import by.epamtraining.financial_accounting.bean.Record;
import by.epamtraining.financial_accounting.bean.User;
import by.epamtraining.financial_accounting.dao.RecordDAO;
import by.epamtraining.financial_accounting.dao.exception.DAOException;
import by.epamtraining.financial_accounting.dao.factory.DAOFactory;
import by.epamtraining.financial_accounting.service.RecordService;
import by.epamtraining.financial_accounting.service.UserContextHolder;
import by.epamtraining.financial_accounting.service.exception.ServiceException;

import java.util.List;

public class RecordServiceImpl implements RecordService {

    public void addRecord(Record newRecord) throws ServiceException {
        User currentUser = UserContextHolder.getInstance().getActiveUser();
        newRecord.setUserLogin(currentUser.getLogin());

        DAOFactory daoFactory = DAOFactory.getInstance();
        RecordDAO recordDAO = daoFactory.getRecordDAO();
        try{
            recordDAO.addRecord(newRecord);
        } catch (DAOException daoEx){
            throw new ServiceException(daoEx);
        }
    }

    public List<Record> getUserRecords() throws ServiceException{
        User currentUser = UserContextHolder.getInstance().getActiveUser();

        DAOFactory daoFactory = DAOFactory.getInstance();
        RecordDAO recordDAO = daoFactory.getRecordDAO();
        try {
            List<Record> userRecords = recordDAO.getUserRecords(currentUser);
            return userRecords;
        } catch (DAOException daoEx){
            throw new ServiceException(daoEx);
        }
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
