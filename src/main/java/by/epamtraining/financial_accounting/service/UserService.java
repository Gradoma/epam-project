package by.epamtraining.financial_accounting.service;

import by.epamtraining.financial_accounting.service.exception.ServiceException;

public interface UserService {
    void signUp(String login, String password) throws ServiceException;
    void signIn(String login, String password) throws ServiceException;
    void logout();
}
