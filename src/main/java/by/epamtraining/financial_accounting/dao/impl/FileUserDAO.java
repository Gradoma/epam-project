package by.epamtraining.financial_accounting.dao.impl;

import by.epamtraining.financial_accounting.bean.User;
import by.epamtraining.financial_accounting.dao.UserDAO;
import by.epamtraining.financial_accounting.dao.exception.DAOException;
import by.epamtraining.financial_accounting.dao.exception.DAONonuniqueValueException;
import by.epamtraining.financial_accounting.dao.exception.DAOValidationException;

import java.io.*;
import java.nio.file.Path;
import java.util.HashMap;

public class FileUserDAO implements UserDAO {

    public User findByUserName(String username) throws DAOException {
        if(username == null || username.isEmpty()){
            throw new DAOValidationException("Null reference to String username");
        }
        HashMap<String, User> usersMap = pullUsersMap();
        if (usersMap.containsKey(username)) {
            User user = usersMap.get(username);
            return user;
        } else return null;
    }

    public void saveUser(User user) throws DAOException{
        if(user == null){
            throw new DAOValidationException("Null reference to User object");
        } else {
            HashMap<String, User> usersMap = pullUsersMap();
            if (usersMap.containsKey(user.getLogin())) {
                throw new DAONonuniqueValueException("User with this login already exists");
            }
            usersMap.put(user.getLogin(), user);
            pushUsersMap(usersMap);
        }
    }

    private HashMap<String, User> pullUsersMap() throws DAOException{
        try (InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("UserFile.txt")) {
            ObjectInput oi = new ObjectInputStream(inputStream);
            HashMap<String, User> usersMap = (HashMap<String, User>) oi.readObject();
            oi.close();
            return usersMap;
        } catch (EOFException e1){
            return new HashMap<String, User>();
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

    private void pushUsersMap(HashMap<String, User> updatedUsers) throws DAOException{
        File file;
        try {
            file = Path.of(this.getClass().getClassLoader().getResource("UserFile.txt").toURI()).toFile();
        } catch (Exception e){
            throw new DAOException(e);
        }
        try (OutputStream os = new FileOutputStream(file)){
            ObjectOutput oo = new ObjectOutputStream(os);
            oo.writeObject(updatedUsers);
            oo.close();
        } catch (Exception e){
            throw new DAOException(e);
        }
    }
}
