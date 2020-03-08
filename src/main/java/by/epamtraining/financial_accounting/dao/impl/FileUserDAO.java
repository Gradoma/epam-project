package by.epamtraining.financial_accounting.dao.impl;

import by.epamtraining.financial_accounting.bean.Role;
import by.epamtraining.financial_accounting.bean.User;
import by.epamtraining.financial_accounting.dao.UserDAO;
import by.epamtraining.financial_accounting.dao.exception.DAOException;

import java.io.*;
import java.net.URL;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class FileUserDAO implements UserDAO {
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");
    private String userFilePath = "classpath:\\UserFile.txt";



    public User findByUserName(String username) throws DAOException {
        if(username == null || username.isEmpty()){
            throw new DAOException();
        }
//        Date parse = new SimpleDateFormat("dd-MM-yyyy").parse("11-08-1997");
        //Double.valueOf("11.0")
        HashMap<String, String> usersMap = pullUsersMap(userFilePath);
        if (usersMap.containsKey(username)) {
//            if (usersMap.get(login).equals(password)){
//                System.out.println("Welcome!");
//            } else throw new DAOException("Incorrect password");
            User user = new User(username, usersMap.get(username));
            if(user.getLogin().equals("admin")){                                  // DON'T DELETE!!
                user.setRole(Role.ADMIN);
            }
            return user;
        } else return null;
    }

    public void saveUser(User user) throws DAOException{
        if(user == null){
            throw new DAOException();
        } else {
            HashMap<String, String> usersMap = pullUsersMap(userFilePath);
            if (usersMap.containsKey(user.getLogin())) {
                throw new DAOException("User with this login already exists");
            }
            usersMap.put(user.getLogin(), user.getPassword());
            pushUsersMap(usersMap);
        }
    }

    private HashMap<String, String> pullUsersMap(String path) throws DAOException{
        try {
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("UserFile.txt");
//            InputStream is = new FileInputStream(path);
            ObjectInput oi = new ObjectInputStream(inputStream);

            HashMap<String, String> usersMap = (HashMap<String, String>) oi.readObject();
            oi.close();
            return usersMap;
        } catch (EOFException e1){
            return new HashMap<String, String>();
        } catch (Exception e) {
            throw new DAOException(e);
        }
    }

    private void pushUsersMap(HashMap<String, String> updatedUsers) throws DAOException{
        try {
            File file = Path.of(this.getClass().getClassLoader().getResource("UserFile.txt").toURI()).toFile();
            OutputStream os = new FileOutputStream(file);
            ObjectOutput oo = new ObjectOutputStream(os);
            oo.writeObject(updatedUsers);
            oo.close();
        } catch (Exception e){
            throw new DAOException(e);
        }

    }
}
