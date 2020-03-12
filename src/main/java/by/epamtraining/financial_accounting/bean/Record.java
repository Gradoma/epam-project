package by.epamtraining.financial_accounting.bean;

import java.io.Serializable;
import java.util.Date;

public class Record implements Serializable {
    private String userLogin;
    private double operationValue;
    private Date date;
    private String description;

    public Record() {}

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

    public String getUserLogin() {
        return userLogin;
    }

    public double getOperationValue() {
        return operationValue;
    }

    public Date getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public void setUserLogin(String userLogin){
        this.userLogin = userLogin;
    }

    public void setOperationValue(double operationValue) {
        this.operationValue = operationValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null){
            return false;
        }
        if(getClass() != o.getClass()){
            return false;
        }
        Record record = (Record) o;
        if(Double.compare(record.operationValue, operationValue) != 0){
            return false;
        }
        if(userLogin != null) {
            if (!date.equals(record.date)) {
                return false;
            }
        } else {
            if(record.userLogin != null){
                return false;
            }
        }
        if(date != null){
            if(!date.equals(record.date)){
                return false;
            }
        }else {
            if(record.date != null){
                return false;
            }
        }
        if(description != null){
            return description.equals(record.description);
        } else{
            return record.description == null;
        }
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        int prime = 31;
        result = userLogin != null ? userLogin.hashCode() : 0;
        temp = Double.doubleToLongBits(operationValue);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + (date != null ? date.hashCode() : 0);
        result = prime * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return getClass().getName() + "{" +
                "userLogin='" + userLogin + '\'' +
                ", operationValue=" + operationValue +
                ", date=" + date +
                ", description='" + description + '\'' +
                '}';
    }
}
