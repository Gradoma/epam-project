package by.epamtraining.financial_accounting.service.exception;

public class ServiceValidationException extends ServiceException {

    public ServiceValidationException(){
        super();
    }

    public ServiceValidationException(String message){
        super(message);
    }

    public ServiceValidationException(Exception e){
        super(e);
    }

    public ServiceValidationException(String message, Exception e){
        super(message, e);
    }
}
