package by.epamtraining.financial_accounting.service.factory;

import by.epamtraining.financial_accounting.service.RecordService;
import by.epamtraining.financial_accounting.service.UserService;
import by.epamtraining.financial_accounting.service.impl.RecordServiceImpl;
import by.epamtraining.financial_accounting.service.impl.UserServiceImpl;

public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();

    private final UserService userService = new UserServiceImpl();
    private final RecordService recordService = new RecordServiceImpl();

    private ServiceFactory(){}

    public static ServiceFactory getInstance() {
        return instance;
    }

    public UserService getUserService() {
        return userService;
    }

    public RecordService getRecordService(){
        return recordService;
    }
}
