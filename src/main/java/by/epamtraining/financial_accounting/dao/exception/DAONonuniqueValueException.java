package by.epamtraining.financial_accounting.dao.exception;

public class DAONonuniqueValueException extends DAOException {

    public DAONonuniqueValueException(){
        super();
    }

    public DAONonuniqueValueException(String message){
        super(message);
    }

    public DAONonuniqueValueException(Exception e){
        super(e);
    }

    public DAONonuniqueValueException(String message, Exception e){
        super(message, e);
    }
}
