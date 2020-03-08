package by.epamtraining.financial_accounting.bean;

import java.io.Serializable;
import java.util.Date;

public class Record implements Serializable {
    private String userLogin;
    private double operationValue;
    private Date date;
    private String description;

    public Record() {
    }

    public Record(String userLogin, double operationValue, Date date) {
        this(userLogin, operationValue, date, "Non-specified");
    }

    public Record(String userLogin, double operationValue) {
        this(userLogin, operationValue, new Date());
    }

    public Record(String userLogin, double operationValue, Date date, String description) {
        this.userLogin = userLogin;
        this.operationValue = operationValue;
        this.date = date;
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Record record = (Record) o;

        if (Double.compare(record.operationValue, operationValue) != 0) return false;
        return userLogin != null ? userLogin.equals(record.userLogin) : record.userLogin == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = userLogin != null ? userLogin.hashCode() : 0;
        temp = Double.doubleToLongBits(operationValue);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin){
        this.userLogin = userLogin;
    }
    public double getOperationValue() {
        return operationValue;
    }

    public void setOperationValue(double operationValue) {
        this.operationValue = operationValue;
    }

    public Date getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }
}
