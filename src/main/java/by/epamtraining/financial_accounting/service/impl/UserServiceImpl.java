package by.epamtraining.financial_accounting.service.impl;

import by.epamtraining.financial_accounting.bean.User;
import by.epamtraining.financial_accounting.dao.UserDAO;
import by.epamtraining.financial_accounting.dao.exception.DAOException;
import by.epamtraining.financial_accounting.dao.factory.DAOFactory;
import by.epamtraining.financial_accounting.service.UserContextHolder;
import by.epamtraining.financial_accounting.service.UserService;
import by.epamtraining.financial_accounting.service.exception.ServiceCurrentUserStatusException;
import by.epamtraining.financial_accounting.service.exception.ServiceException;
import by.epamtraining.financial_accounting.service.exception.ServiceUserAuthorizationException;
import by.epamtraining.financial_accounting.service.exception.ServiceValidationException;

public class UserServiceImpl implements UserService {

    public void signUp(String login, String password) throws ServiceException{
        UserContextHolder userContextHolder = UserContextHolder.getInstance();
        if(userContextHolder.getActiveUser() != null){
            throw new ServiceCurrentUserStatusException("Unavailable command Sign Up for current user status");
        }
        if(login == null || login.isEmpty() || password == null || password.isEmpty()){
            throw new ServiceValidationException("Null or empty String parameters (login, password)");
        } else {
            User newUser = new User(login, password);
            DAOFactory daoFactory = DAOFactory.getInstance();
            UserDAO userDAO = daoFactory.getUserDAO();
            try {
                userDAO.saveUser(newUser);
                userContextHolder.setActiveUser(newUser);
            } catch (DAOException daoEx){
                throw new ServiceException(daoEx.getMessage());
            }
        }
    }

    public void signIn(String login, String password) throws ServiceException{
        UserContextHolder userContextHolder = UserContextHolder.getInstance();
        if(userContextHolder.getActiveUser() != null){
            throw new ServiceCurrentUserStatusException("Unavailable command Sign In for current user status");
        }

        if(login == null || login.isEmpty() || password == null || password.isEmpty()){
            throw new ServiceValidationException("Null or empty String parameters (login, password)");
        } else {
            DAOFactory daoFactory = DAOFactory.getInstance();
            UserDAO userDAO = daoFactory.getUserDAO();
            try{
                User user = userDAO.findByUserName(login);
                if(user == null){
                    throw new ServiceUserAuthorizationException("Incorrect login");
                } else if(!user.getPassword().equals(password)){
                    throw new ServiceUserAuthorizationException("Incorrect password");
                } else {
                    userContextHolder.setActiveUser(user);
                }
            } catch (DAOException daoEx){
                throw new ServiceException(daoEx.getMessage());
            }
        }
    }

    public void logout() throws ServiceException{
        UserContextHolder userContextHolder = UserContextHolder.getInstance();
        if(userContextHolder.getActiveUser() == null){
            throw new ServiceCurrentUserStatusException("Unavailable command Log Out for current user status");
        }
        userContextHolder.clearActiveUser();
    }
}
