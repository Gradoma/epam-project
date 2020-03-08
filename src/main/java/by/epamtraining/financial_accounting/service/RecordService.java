package by.epamtraining.financial_accounting.service;

import by.epamtraining.financial_accounting.bean.Record;
import by.epamtraining.financial_accounting.bean.User;
import by.epamtraining.financial_accounting.service.exception.ServiceException;

import java.util.Date;
import java.util.List;

public interface RecordService {
    void addRecord(String value, String date, String description) throws ServiceException;
    List<Record> getUserRecords() throws ServiceException;
    List<Record> getUserRecordsInPeriod(Date date1, Date date2) throws ServiceException;
    List<Record> getAllRecords() throws ServiceException;
    double getBalance() throws ServiceException;
}
