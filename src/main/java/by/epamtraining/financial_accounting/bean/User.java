package by.epamtraining.financial_accounting.bean;

import java.io.Serializable;

public class User implements Serializable {
    private String login;
    private String password;
    private Role role;

    public User(){}

    public User(String login, String password){
        this.login = login;
        this.password = password;
        this.role = Role.USER;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole(){
        return role;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Role role){
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if(this == o){
            return true;
        }
        if(o == null){
            return false;
        }
        if(getClass() != o.getClass()){
            return false;
        }
        User user = (User)o;
        if(login != null) {
            if (!login.equals(user.login)) {
                return false;
            }
        } else {
            if(user.login != null){
                return false;
            }
        }
        if(password != null) {
            if (!password.equals(user.password)) {
                return false;
            }
        } else {
            if(user.password != null){
                return false;
            }
        }
        if(role != null){
            return role == user.role;
        } else {
            return user.role == null;
        }
    }

    @Override
    public int hashCode() {
        int prime = 31;
        int result = login != null ? login.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return getClass().getName() + "{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
