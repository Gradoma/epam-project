package by.epamtraining.financial_accounting.service;

import by.epamtraining.financial_accounting.bean.Record;
import by.epamtraining.financial_accounting.bean.User;
import by.epamtraining.financial_accounting.service.exception.ServiceException;

import java.util.List;

public interface RecordService {
    void addRecord(Record record) throws ServiceException;
    List<Record> getUserRecords() throws ServiceException;
    double getBalance() throws ServiceException;
}
