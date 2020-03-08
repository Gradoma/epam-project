package by.epamtraining.financial_accounting.service.exception;

public class ServiceIncorrectRequestConditionException extends ServiceException {

    public ServiceIncorrectRequestConditionException(){
        super();
    }

    public ServiceIncorrectRequestConditionException(String message){
        super(message);
    }

    public ServiceIncorrectRequestConditionException(Exception e){
        super(e);
    }

    public ServiceIncorrectRequestConditionException(String message, Exception e){
        super(message, e);
    }
}
