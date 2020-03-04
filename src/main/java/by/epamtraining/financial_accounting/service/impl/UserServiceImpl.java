package by.epamtraining.financial_accounting.service.impl;

import by.epamtraining.financial_accounting.bean.User;
import by.epamtraining.financial_accounting.dao.UserDAO;
import by.epamtraining.financial_accounting.dao.exception.DAOException;
import by.epamtraining.financial_accounting.dao.factory.DAOFactory;
import by.epamtraining.financial_accounting.service.UserContextHolder;
import by.epamtraining.financial_accounting.service.UserService;
import by.epamtraining.financial_accounting.service.exception.ServiceException;

public class UserServiceImpl implements UserService {

    public void signUp(String login, String password) throws ServiceException{
        UserContextHolder userContextHolder = UserContextHolder.getInstance();
        if(userContextHolder.getActiveUser() != null){
            throw new ServiceException("You already Signed In");
        }
//        System.out.println("login and pasw on the service enter:" + login + "; " + password);               // testing
        if(login == null || login.isEmpty() || password == null || password.isEmpty()){
            throw new ServiceException("Login and password can't be null or empty.");
        } else {
            User newUser = new User(login, password);
            DAOFactory daoFactory = DAOFactory.getInstance();
            UserDAO userDAO = daoFactory.getUserDAO();
            try {
                userDAO.saveUser(newUser);
//                UserContextHolder userContextHolder = UserContextHolder.getInstance();
                userContextHolder.setActiveUser(newUser);
            } catch (DAOException daoEx){
                throw new ServiceException(daoEx);
            }
        }
    }

    public void signIn(String login, String password) throws ServiceException{
        UserContextHolder userContextHolder = UserContextHolder.getInstance();
        if(userContextHolder.getActiveUser() != null){
            throw new ServiceException("You already Signed In");
        }

        if(login == null || login.isEmpty() || password == null || password.isEmpty()){
            throw new ServiceException("Login and password can't be null or empty.");
        } else {
            DAOFactory daoFactory = DAOFactory.getInstance();
            UserDAO userDAO = daoFactory.getUserDAO();
            try{
                User user = userDAO.findByUserName(login);
                if(user == null){
                    throw new ServiceException("Incorrect login");
                } else if(!user.getPassword().equals(password)){
                    throw new ServiceException("Incorrect password");
                } else {
//                    UserContextHolder userContextHolder = UserContextHolder.getInstance();
                    userContextHolder.setActiveUser(user);
                }
            } catch (DAOException daoEx){
                throw new ServiceException(daoEx.getMessage(), daoEx);
            }
        }
    }

    public void logout() throws ServiceException{
        UserContextHolder userContextHolder = UserContextHolder.getInstance();
        if(userContextHolder.getActiveUser() == null){
            throw new ServiceException("You can't logout. Sign In or Register first.");
        }
        userContextHolder.clearActiveUser();
    }
}
