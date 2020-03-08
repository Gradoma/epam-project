package by.epamtraining.financial_accounting.service.exception;

public class ServiceCurrentUserStatusException extends ServiceException {

    public ServiceCurrentUserStatusException(){
        super();
    }

    public ServiceCurrentUserStatusException(String message){
        super(message);
    }

    public ServiceCurrentUserStatusException(Exception e){
        super(e);
    }

    public ServiceCurrentUserStatusException(String message, Exception e){
        super(message, e);
    }
}
