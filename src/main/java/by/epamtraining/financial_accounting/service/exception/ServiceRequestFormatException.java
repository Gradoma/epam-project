package by.epamtraining.financial_accounting.service.exception;

public class ServiceRequestFormatException extends ServiceException {

    public ServiceRequestFormatException(){
        super();
    }

    public ServiceRequestFormatException(String message){
        super(message);
    }

    public ServiceRequestFormatException(Exception e){
        super(e);
    }

    public ServiceRequestFormatException(String message, Exception e){
        super(message, e);
    }
}
