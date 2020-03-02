package by.epamtraining.financial_accounting.service;

import by.epamtraining.financial_accounting.bean.User;

public class UserContextHolder {
    private static final UserContextHolder instance = new UserContextHolder();

    private User activeUser;

    private UserContextHolder(){}

    public static UserContextHolder getInstance(){
        return instance;
    }

    public void setActiveUser(User user){
        activeUser = user;
    }

    public User getActiveUser(){
        return activeUser;
    }

    public void clearActiveUser(){
        activeUser = null;
    }
}
