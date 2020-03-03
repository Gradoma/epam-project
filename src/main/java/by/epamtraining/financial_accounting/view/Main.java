package by.epamtraining.financial_accounting.view;

import by.epamtraining.financial_accounting.bean.Record;
import by.epamtraining.financial_accounting.bean.Role;
import by.epamtraining.financial_accounting.bean.User;
import by.epamtraining.financial_accounting.controller.Controller;
import by.epamtraining.financial_accounting.dao.exception.DAOException;
import by.epamtraining.financial_accounting.dao.impl.FileRecordDAO;
import by.epamtraining.financial_accounting.dao.impl.FileUserDAO;
import by.epamtraining.financial_accounting.service.UserContextHolder;
import by.epamtraining.financial_accounting.view.data_access.DataScanner;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

                                                  // DONT DELETE!!!!!
//        String testRequest = "sign_in dad 15651";
//        String result = controller.executeTask(testRequest);
//        System.out.println(result);
//        System.out.println("\ncurrent user : ");
//        System.out.println(UserContextHolder.getInstance().getActiveUser().getLogin());
//        System.out.println(UserContextHolder.getInstance().getActiveUser().getPassword());
//
//        String testRequest2 = "add_record 2hundered buks";
//        result = controller.executeTask(testRequest2);
//        System.out.println(result);
//
//        testRequest2 = "add_record 15-05-2019 -150";
//        result = controller.executeTask(testRequest2);
//        System.out.println(result);
//
//        testRequest2 = "add_record 15-05-2019 -50";
//        result = controller.executeTask(testRequest2);
//        System.out.println(result);
//
//        testRequest2 = "add_record 17-05-2019 -450";
//        result = controller.executeTask(testRequest2);
//        System.out.println(result);
//
//        testRequest2 = "add_record 16-08-2019 -320";
//        result = controller.executeTask(testRequest2);
//        System.out.println(result);

//        String testRequest3 = "get_balance ";
//        result = controller.executeTask(testRequest3);
//        System.out.println(result);
//
//        testRequest = "get_records ";
//        result = controller.executeTask(testRequest);
//        System.out.println(result);
        // DONT DELETE !!!
    }
}
