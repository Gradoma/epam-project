package by.epamtraining.financial_accounting.dao.factory;

import by.epamtraining.financial_accounting.bean.Record;
import by.epamtraining.financial_accounting.bean.User;
import by.epamtraining.financial_accounting.dao.RecordDAO;
import by.epamtraining.financial_accounting.dao.UserDAO;
import by.epamtraining.financial_accounting.dao.exception.DAOException;
import by.epamtraining.financial_accounting.dao.impl.FileRecordDAO;
import by.epamtraining.financial_accounting.dao.impl.FileUserDAO;

import java.util.List;

public class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();

    private final UserDAO fileUserDAO = new FileUserDAO();
    private final RecordDAO fileRecordDAO = new FileRecordDAO();

    private DAOFactory(){}

    public static DAOFactory getInstance(){
        return instance;
    }

    public RecordDAO getRecordDAO() {
        return fileRecordDAO;
    }

    public UserDAO getUserDAO() {
        return fileUserDAO;
    }
}
