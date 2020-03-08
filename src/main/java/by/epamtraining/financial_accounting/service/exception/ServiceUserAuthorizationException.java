package by.epamtraining.financial_accounting.service.exception;

public class ServiceUserAuthorizationException extends ServiceException {

    public ServiceUserAuthorizationException(){
        super();
    }

    public ServiceUserAuthorizationException(String message){
        super(message);
    }

    public ServiceUserAuthorizationException(Exception e){
        super(e);
    }

    public ServiceUserAuthorizationException(String message, Exception e){
        super(message, e);
    }
}
