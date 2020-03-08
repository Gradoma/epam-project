package by.epamtraining.financial_accounting.dao.exception;

public class DAOValidationException extends DAOException {

    public DAOValidationException(){
        super();
    }

    public DAOValidationException(String message){
        super(message);
    }

    public DAOValidationException(Exception e){
        super(e);
    }

    public DAOValidationException(String message, Exception e){
        super(message, e);
    }
}
