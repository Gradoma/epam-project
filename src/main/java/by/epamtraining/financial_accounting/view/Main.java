package by.epamtraining.financial_accounting.view;

import by.epamtraining.financial_accounting.bean.Role;
import by.epamtraining.financial_accounting.bean.User;
import by.epamtraining.financial_accounting.controller.Controller;
import by.epamtraining.financial_accounting.dao.exception.DAOException;
import by.epamtraining.financial_accounting.dao.impl.FileUserDAO;
import by.epamtraining.financial_accounting.view.data_access.DataScanner;

public class Main {
    public static void main(String[] args) {
//        User admin = new User("admin", "admin");
//        admin.setRole(Role.ADMIN);
//        System.out.println(admin.getRole());
//
//        FileUserDAO fileUserDAO = new FileUserDAO();
//        try{
//            fileUserDAO.saveUser(admin);
//        } catch (DAOException d){
//            d.printStackTrace();
//        }

        DataScanner dataScanner = new DataScanner();
        boolean inProgress = true;
        Controller controller = new Controller();

        while (inProgress){
            System.out.println(controller.getAvailableCommand() + "\nstop - to stop program");
            String consoleRequest = dataScanner.scanFromConcole();
            if(consoleRequest.equals("stop")){
                break;
            }
            System.out.println(controller.executeTask(consoleRequest));
        }
    }
}
