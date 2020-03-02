package by.epamtraining.financial_accounting.view.data_access;

import java.util.Scanner;

public class DataScanner {

    public String scanFromConcole(){
        Scanner scanner = new Scanner(System.in);
        while (!scanner.hasNextLine()){
            scanner.next();
        }
        return scanner.nextLine();
    }
}
