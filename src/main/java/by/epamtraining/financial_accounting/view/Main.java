package by.epamtraining.financial_accounting.view;

import by.epamtraining.financial_accounting.controller.Controller;
import by.epamtraining.financial_accounting.view.data_access.DataScanner;

public class Main {
    public static void main(String[] args) {
        DataScanner dataScanner = new DataScanner();
        Controller controller = new Controller();

        while (true){
            System.out.println(controller.getAvailableCommand() + "\nstop - to stop program");
            String consoleRequest = dataScanner.scanFromConcole();
            if(consoleRequest.equals("stop")){
                break;
            }
            System.out.println(controller.executeTask(consoleRequest));
        }
    }
}
