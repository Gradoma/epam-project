package by.epamtraining.financial_accounting.dao;

import by.epamtraining.financial_accounting.bean.User;
import by.epamtraining.financial_accounting.dao.exception.DAOException;

public interface UserDAO {
    User findByUserName(String username) throws DAOException;
    void saveUser(User user) throws DAOException;
}
