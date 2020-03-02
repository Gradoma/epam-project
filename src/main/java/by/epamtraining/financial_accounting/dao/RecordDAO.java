package by.epamtraining.financial_accounting.dao;

import by.epamtraining.financial_accounting.bean.Record;
import by.epamtraining.financial_accounting.bean.User;
import by.epamtraining.financial_accounting.dao.exception.DAOException;

import java.util.List;

public interface RecordDAO {

    void addRecord(Record newRecord) throws DAOException;
    List<Record> getUserRecords(User currentUser) throws DAOException;
}
